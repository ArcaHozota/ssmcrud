package jp.co.toshiba.ppok.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;

import jp.co.toshiba.ppok.entity.CityInfo;
import jp.co.toshiba.ppok.repository.CityInfoDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.*;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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

import jakarta.validation.Valid;
import jp.co.toshiba.ppok.utils.RestMsg;

/**
 * Center Terminal Controller Handle the retrieve and update requests.
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/public/grssmcrud")
public class CentreController {

    @Resource
    private CityInfoDao cityInfoDao;

    /**
     * Retrieve the city data.
     *
     * @return page(JSON)
     */
    @GetMapping(value = "/city")
    public RestMsg getCities(@RequestParam(value = "pageNum", defaultValue = "1") final Integer pageNum,
                             @RequestParam(value = "name", defaultValue = "") final String name) {
        final PageRequest pageRequest = PageRequest.of(pageNum - 1, 17);
        Page<CityInfo> dtoPage;
        if (StringUtils.isNotEmpty(name)) {
            final CityInfo cityInfo = new CityInfo();
            cityInfo.setName(name);
            final ExampleMatcher matcher = ExampleMatcher.matching()
                    .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING).withIgnoreCase(true)
                    .withMatcher(name, ExampleMatcher.GenericPropertyMatchers.contains())
                    .withIgnorePaths("id", "continent", "nation", "district", "population");
            final Example<CityInfo> example = Example.of(cityInfo, matcher);
            dtoPage = this.cityInfoDao.findAll(example, pageRequest);
        } else {
            dtoPage = this.cityInfoDao.findAll(pageRequest);
        }
        // 設置總頁數；
        final int totalPage = dtoPage.getTotalPages();
//		dtoPage.setTotalPages(totalPage);
//		// 設置分頁導航條頁碼數量；
//		dtoPage.calcByNaviPages(5);
        return RestMsg.success().add("pageInfo", dtoPage);
    }

    /**
     * Search the selected city's name.
     *
     * @param id the ID of city
     * @return RestMsg.success().add(data)
     */
    @GetMapping(value = "/city/{id}")
    public RestMsg getCityInfo(@PathVariable("id") final Long id) {
        final CityInfo cityInfo = this.cityInfoDao.getById(id);
        return RestMsg.success().add("citySelected", cityInfo);
    }

    /**
     * Save the input messages.
     *
     * @param cityInfo the input message of cities
     * @return RestMsg.success()
     */
    @PostMapping(value = "/city")
    public RestMsg saveCityInfos(@Valid final CityInfo cityInfo, final BindingResult result) {
        final Map<String, Object> map = new HashMap<>(5);
        if (result.hasErrors()) {
            final List<FieldError> fieldErrors = result.getFieldErrors();
            for (final FieldError fieldError : fieldErrors) {
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return RestMsg.failure().add("errorFields", map);
        } else {
            this.cityInfoDao.save(cityInfo);
            return RestMsg.success();
        }
    }

    /**
     * Update city info.
     *
     * @param cityView the input message of cities
     * @return RestMsg.success()
     */
    @PutMapping(value = "/city/{id}")
    public RestMsg updateCityInfo(@RequestBody final CityView cityView) {
        this.cityViewService.updateCityInfo(cityView);
        return RestMsg.success();
    }

    /**
     * Delete the selected city info.
     *
     * @param id the ID of city
     * @return RestMsg.success()
     */
    @DeleteMapping(value = "/city/{id}")
    public RestMsg deleteCityInfo(@PathVariable("id") final Long id) {
        this.cityViewService.deleteCityInfo(id);
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
        final String regex = "^[a-zA-Z_-]{4,17}$";
        if (cityName.matches(regex)) {
            final CityInfo cityInfo = new CityInfo();
            cityInfo.setName(cityName);
            final ExampleMatcher matcher = ExampleMatcher.matching()
                    .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING).withIgnoreCase(true)
                    .withMatcher(cityName, ExampleMatcher.GenericPropertyMatchers.exact())
                    .withIgnorePaths("id", "continent", "nation", "district", "population");
            final Example<CityInfo> example = Example.of(cityInfo, matcher);
            final Optional<CityInfo> duplicated = this.cityInfoDao.findOne(example);
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
        final List<CityInfo> cnlist = this.cityInfoDao.findAll();
        return RestMsg.success().add("continents", cnlist);
    }

    /**
     * Get list of nations.
     *
     * @return RestMsg.success().add(data)
     */
    @GetMapping(value = "/nations")
    public RestMsg getListOfNations(@RequestParam("continentVal") final String continent) {
        final List<CityInfo> list = this.getNations(continent);
        return RestMsg.success().add("nations", list);
    }

    /**
     * Get list of nations.
     *
     * @return RestMsg.success().add(data)
     */
    @GetMapping(value = "/nations/{id}")
    public RestMsg getListOfNationsById(@PathVariable("id") final Long id) {
        final List<String> list = Lists.newArrayList();
        final CityInfo cityInfo = this.cityInfoDao.getById(id);
        final String nationName = cityInfo.getNation();
        list.add(nationName);
        final String continent = cityInfo.getContinent();
        final List<CityInfo> nations = this.getNations(continent);
        nations.forEach(item -> {
            if (!nationName.equals(item.getNation())) {
                list.add(item.getNation());
            }
        });
        return RestMsg.success().add("nationsWithName", list);
    }

    private List<CityInfo> getNations(final String continent) {
        final CityInfo cityInfo = new CityInfo();
        cityInfo.setContinent(continent);
        final ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING).withIgnoreCase(true)
                .withMatcher(continent, ExampleMatcher.GenericPropertyMatchers.exact())
                .withIgnorePaths("id", "name", "nation", "district", "population");
        final Example<CityInfo> example = Example.of(cityInfo, matcher);
        return this.cityInfoDao.findAll(example);
    }
}