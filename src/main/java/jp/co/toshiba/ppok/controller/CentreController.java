package jp.co.toshiba.ppok.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import jp.co.toshiba.ppok.entity.City;
import jp.co.toshiba.ppok.entity.CityInfo;
import jp.co.toshiba.ppok.entity.Nation;
import jp.co.toshiba.ppok.repository.CityDao;
import jp.co.toshiba.ppok.repository.CityInfoDao;
import jp.co.toshiba.ppok.repository.NationDao;
import jp.co.toshiba.ppok.utils.ComparisonUtils;
import jp.co.toshiba.ppok.utils.PaginationImpl;
import jp.co.toshiba.ppok.utils.RestMsg;

/**
 * Center Terminal Controller handle the retrieve and update requests.
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/public/grssmcrud")
public class CentreController {

	@Resource
	private CityDao cityDao;

	@Resource
	private CityInfoDao cityInfoDao;

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
		final PageRequest pageRequest01 = PageRequest.of(pageNum - 1, 17);
		Page<CityInfo> dtoPage;
		if (ComparisonUtils.isNotEmpty(keyword)) {
			final CityInfo cityInfo1 = new CityInfo();
			cityInfo1.setNation(keyword);
			final ExampleMatcher matcher1 = ExampleMatcher.matching()
					.withStringMatcher(ExampleMatcher.StringMatcher.EXACT)
					.withMatcher(keyword, ExampleMatcher.GenericPropertyMatchers.exact());
			final Example<CityInfo> example1 = Example.of(cityInfo1, matcher1);
			final List<CityInfo> findAll = this.cityInfoDao.findAll(example1);
			if (findAll.size() != 0) {
				dtoPage = this.cityInfoDao.findAll(example1, pageRequest01);
			} else if (ComparisonUtils.isEqual("min(pop)", keyword)) {
				final PageRequest pageRequest02 = PageRequest.of(pageNum - 1, 17,
						Sort.by(Sort.Direction.ASC, "population"));
				final List<CityInfo> minPopList = this.cityInfoDao.findAll(pageRequest02).getContent().subList(0, 10);
				dtoPage = new PageImpl<>(minPopList);
			} else if (ComparisonUtils.isEqual("max(pop)", keyword)) {
				final PageRequest pageRequest03 = PageRequest.of(pageNum - 1, 17,
						Sort.by(Sort.Direction.DESC, "population"));
				final List<CityInfo> maxPopList = this.cityInfoDao.findAll(pageRequest03).getContent().subList(0, 10);
				dtoPage = new PageImpl<>(maxPopList);
			} else {
				final CityInfo cityInfo2 = new CityInfo();
				cityInfo2.setName(keyword);
				final ExampleMatcher matcher2 = ExampleMatcher.matching()
						.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING).withIgnoreCase(true)
						.withMatcher(keyword, ExampleMatcher.GenericPropertyMatchers.contains())
						.withIgnorePaths("id", "continent", "nation", "district", "population");
				final Example<CityInfo> example2 = Example.of(cityInfo2, matcher2);
				dtoPage = this.cityInfoDao.findAll(example2, pageRequest01);
			}
		} else {
			dtoPage = this.cityInfoDao.findAll(pageRequest01);
		}
		// 設置分頁；
		final PaginationImpl<CityInfo> pageInfo = new PaginationImpl<>(dtoPage.getContent());
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
	public RestMsg getCityInfo(@PathVariable("id") final Long id) {
		final CityInfo cityInfo = this.cityInfoDao.getById(id);
		return RestMsg.success().add("citySelected", cityInfo);
	}

	/**
	 * Update city info.
	 *
	 * @param cityInfo the input message of cities
	 * @return RestMsg.success()
	 */
	@PutMapping(value = "/city/{id}")
	public RestMsg updateCityInfo(@RequestBody final CityInfo cityInfo) {
		final City city = new City();
		BeanUtils.copyProperties(cityInfo, city, "continent", "nation");
		final String nationName = cityInfo.getNation();
		final Nation nation = new Nation();
		nation.setName(nationName);
		final ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.EXACT)
				.withMatcher(nationName, ExampleMatcher.GenericPropertyMatchers.exact()).withIgnoreCase(false);
		final Example<Nation> example = Example.of(nation, matcher);
		final List<Nation> nations = this.nationDao.findAll(example);
		final String nationCode = nations.get(0).getCode();
		city.setCountryCode(nationCode);
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
	public RestMsg deleteCityInfo(@PathVariable("id") final Long id) {
		this.cityDao.deleteById(id);
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
			final CityInfo cityInfo = new CityInfo();
			cityInfo.setName(cityName);
			final ExampleMatcher matcher = ExampleMatcher.matching()
					.withStringMatcher(ExampleMatcher.StringMatcher.EXACT).withIgnoreCase(true)
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
		final Set<String> cnSet = Sets.newHashSet();
		final List<CityInfo> list = this.cityInfoDao.findAll();
		list.forEach(item -> {
			cnSet.add(item.getContinent());
		});
		return RestMsg.success().add("continents", cnSet);
	}

	/**
	 * Get list of nations.
	 *
	 * @return RestMsg.success().add(data)
	 */
	@GetMapping(value = "/nations")
	public RestMsg getListOfNations(@RequestParam("continentVal") final String continent) {
		final List<String> nationList = Lists.newArrayList();
		final List<CityInfo> list = this.cityInfoDao.getNations(continent);
		list.forEach(item -> {
			nationList.add(item.getNation());
		});
		return RestMsg.success().add("nations", nationList);
	}

	/**
	 * Get list of nations.
	 *
	 * @return RestMsg.success().add(data)
	 */
	@GetMapping(value = "/nations/{id}")
	public RestMsg getListOfNationsById(@PathVariable("id") final Long id) {
		final List<String> nationList = Lists.newArrayList();
		final CityInfo cityInfo = this.cityInfoDao.getById(id);
		final String nationName = cityInfo.getNation();
		nationList.add(nationName);
		final String continent = cityInfo.getContinent();
		final List<CityInfo> nations = this.cityInfoDao.getNations(continent);
		nations.forEach(item -> {
			if (!nationName.equals(item.getNation())) {
				nationList.add(item.getNation());
			}
		});
		return RestMsg.success().add("nationsByName", nationList);
	}
}