package jp.co.toshiba.ppok.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;

import jp.co.toshiba.ppok.dto.CityDto;
import jp.co.toshiba.ppok.entity.City;
import jp.co.toshiba.ppok.entity.Country;
import jp.co.toshiba.ppok.repository.CityDao;
import jp.co.toshiba.ppok.repository.NationDao;
import jp.co.toshiba.ppok.utils.PaginationImpl;
import jp.co.toshiba.ppok.utils.RestMsg;
import jp.co.toshiba.ppok.utils.StringUtils;

/**
 * Center Terminal Controller handle the retrieve and update requests.
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/public/grssmcrud")
public class CentreController {

    private static final String CONTINENT = "continent";

    private static final String NATION = "nation";

    @Resource
    private CityDao cityDao;

    @Resource
    private NationDao nationDao;

    /**
     * Retrieve the city data.
     *
     * @return page(JSON)
     */
    @GetMapping(value = "/city")
    public RestMsg getCities(@RequestParam(value = "pageNum", defaultValue = "1") final Integer pageNum,
                             @RequestParam(value = "keyword", defaultValue = "") final String keyword) {
        final Page<City> dtoPage;
        final PageRequest pageRequest = PageRequest.of(pageNum - 1, 18);
        if (StringUtils.isNotEmpty(keyword)) {
            final List<City> keyNations = this.cityDao.findByNations(this.nationDao.findNationCode(keyword).getCode());
            if (keyNations.isEmpty()) {
                dtoPage = this.cityDao.getByNations(this.nationDao.findNationCode(keyword).getCode(), pageRequest);
            } else if (StringUtils.isEqual("min(pop)", keyword)) {
                final List<City> minimumRanks = this.cityDao.findMinimumRanks();
                dtoPage = new PageImpl<>(minimumRanks);
            } else if (StringUtils.isEqual("max(pop)", keyword)) {
                final List<City> maximumRanks = this.cityDao.findMaximumRanks();
                dtoPage = new PageImpl<>(maximumRanks);
            } else {
                dtoPage = this.cityDao.getByNames(keyword, pageRequest);
            }
        } else {
            dtoPage = this.cityDao.findAll(pageRequest);
        }
        final List<CityDto> cities = dtoPage.stream().map(item -> {
            final CityDto cityDto = new CityDto();
            BeanUtils.copyProperties(item, cityDto);
            final Country country = this.nationDao.getById(item.getCountryCode());
            cityDto.setNation(country.getName());
            cityDto.setContinent(country.getContinent());
            return cityDto;
        }).collect(Collectors.toList());
        // 設置分頁；
        final PaginationImpl<CityDto> pageInfo = new PaginationImpl<>(cities);
        // 設置當前頁；
        final int current = dtoPage.getNumber() + 1;
        pageInfo.setCurrent(current);
        // 設置總頁數；
        final int totalPage = dtoPage.getTotalPages();
        pageInfo.setTotalPg(totalPage);
        // 設置總記錄數；
        final long totalRecords = dtoPage.getTotalElements();
        pageInfo.setTotalRecords(totalRecords);
        // 設置是否有前後頁；
        final boolean hasPrevious = dtoPage.hasPrevious();
        pageInfo.setHasPrevious(hasPrevious);
        final boolean hasNext = dtoPage.hasNext();
        pageInfo.setHasNext(hasNext);
        // 設置分頁導航條頁碼數量；
        pageInfo.calcByNaviPages(5);
        return RestMsg.success().add("pageInfo", pageInfo);
    }

    /**
     * Search the selected city's name.
     *
     * @param id the ID of city
     * @return RestMsg.success().add(data)
     */
    @GetMapping(value = "/city/{id}")
    public RestMsg getCityDto(@PathVariable("id") final Integer id) {
        final City city = this.cityDao.getById(id);
        final Country country = this.nationDao.getById(city.getCountryCode());
        final CityDto cityDto = new CityDto();
        BeanUtils.copyProperties(city, cityDto);
        cityDto.setContinent(country.getContinent());
        cityDto.setName(country.getName());
        return RestMsg.success().add("citySelected", cityDto);
    }

    /**
     * Save inputted city info.
     *
     * @param cityDto the input message of cities
     * @return RestMsg.success()
     */
    @PostMapping(value = "/city")
    public RestMsg saveCityDto(@RequestBody final CityDto cityDto) {
        final City city = new City();
        BeanUtils.copyProperties(cityDto, city, CONTINENT, NATION);
        final String nationName = cityDto.getNation();
        final Country country = this.nationDao.findNationCode(nationName);
        final String nationCode = country.getCode();
        city.setCountryCode(nationCode);
        city.setIsDeleted(0);
        this.cityDao.save(city);
        return RestMsg.success();
    }

    /**
     * Update city info.
     *
     * @param cityDto the input message of cities
     * @return RestMsg.success()
     */
    @PutMapping(value = "/city/{id}")
    public RestMsg updateCityDto(@RequestBody final CityDto cityDto) {
        final City city = new City();
        BeanUtils.copyProperties(cityDto, city, CONTINENT, NATION);
        final String nationName = cityDto.getNation();
        final Country country = this.nationDao.findNationCode(nationName);
        final String nationCode = country.getCode();
        city.setCountryCode(nationCode);
        city.setIsDeleted(0);
        this.cityDao.saveAndFlush(city);
        return RestMsg.success();
    }

    /**
     * Delete the selected city info.
     *
     * @param id the ID of city
     * @return RestMsg.success()
     */
    @DeleteMapping(value = "/city/{id}")
    public RestMsg deleteCityDto(@PathVariable("id") final Integer id) {
        this.cityDao.removeById(id);
        return RestMsg.success();
    }

    /**
     * Check the input city name already existed or not.
     *
     * @param cityName the input name
     * @return RestMsg.success()
     */
    @GetMapping(value = "/checklist")
    public RestMsg checkCityName(@RequestParam("cityName") final String cityName) {
        final String regex = "^[a-zA-Z-\\p{IsWhiteSpace}]{4,17}$";
        if (cityName.matches(regex)) {
            final City city = new City();
            city.setName(cityName);
            final ExampleMatcher matcher = ExampleMatcher.matching()
                    .withStringMatcher(ExampleMatcher.StringMatcher.EXACT).withIgnoreCase(true)
                    .withMatcher(cityName, ExampleMatcher.GenericPropertyMatchers.exact())
                    .withIgnorePaths("id", "country_code", "district", "population", "is_deleted");
            final Example<City> example = Example.of(city, matcher);
            final Optional<City> duplicated = this.cityDao.findOne(example);
            if (duplicated.isPresent()) {
                return RestMsg.failure().add("validatedMsg", "City name is duplicate.");
            } else {
                return RestMsg.success();
            }
        } else {
            return RestMsg.failure().add("validatedMsg", "Name of cities should be in 4~17 Latin alphabets.");
        }
    }

    /**
     * Get list of continents.
     *
     * @return RestMsg.success().add(data)
     */
    @GetMapping(value = "/continents")
    public RestMsg getListOfContinents() {
        final List<String> cnList = this.nationDao.findAllContinents();
        return RestMsg.success().add("continents", cnList);
    }

    /**
     * Get list of nations.
     *
     * @return RestMsg.success().add(data)
     */
    @GetMapping(value = "/nations")
    public RestMsg getListOfNations(@RequestParam("continentVal") final String continent) {
        final List<String> nationList = Lists.newArrayList();
        final List<Country> list = this.nationDao.findNationsByCnt(continent);
        list.forEach(item -> nationList.add(item.getName()));
        return RestMsg.success().add("nations", nationList);
    }

    /**
     * Get list of nations.
     *
     * @return RestMsg.success().add(data)
     */
    @GetMapping(value = "/nations/{id}")
    public RestMsg getListOfNationsById(@PathVariable("id") final Integer id) {
        final List<String> nationList = Lists.newArrayList();
        final City city = this.cityDao.getById(id);
        final Country country = this.nationDao.getById(city.getCountryCode());
        nationList.add(country.getName());
        final List<Country> countries = this.nationDao.findNationsByCnt(country.getContinent());
        countries.forEach(item -> {
            if (StringUtils.isNotEqual(country.getName(), item.getName())) {
                nationList.add(item.getName());
            }
        });
        return RestMsg.success().add("nationsByName", nationList);
    }
}