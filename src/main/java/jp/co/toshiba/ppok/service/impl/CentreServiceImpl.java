package jp.co.toshiba.ppok.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import jp.co.toshiba.ppok.dto.CityDto;
import jp.co.toshiba.ppok.entity.City;
import jp.co.toshiba.ppok.mapper.CityMapper;
import jp.co.toshiba.ppok.mapper.CountryMapper;
import jp.co.toshiba.ppok.service.CentreService;
import jp.co.toshiba.ppok.utils.Pagination;
import jp.co.toshiba.ppok.utils.StringUtils;

/**
 * Implementation class of central service interface
 *
 * @author Administrator
 */
@Service
public class CentreServiceImpl implements CentreService {

	/**
	 * city mapper
	 */
	@Resource
	private CityMapper cityMapper;

	/**
	 * country mapper
	 */
	@Resource
	private CountryMapper countryMapper;

	/**
	 * retrieve selected city info by id provided
	 *
	 * @param id cityID
	 * @return entity of city
	 */
	@Override
	public CityDto getCityInfo(final Integer id) {
		return this.cityMapper.getCityInfoById(id);
	}

	/**
	 * save inputted city info
	 *
	 * @param cityDto dto of city.
	 */
	@Override
	public void save(final CityDto cityDto) {
		final City city = new City();
		BeanUtils.copyProperties(cityDto, city, "continent", "nation");
		final String nationCode = this.countryMapper.getNationCode(cityDto.getNation());
		city.setCountryCode(nationCode);
		city.setLogicDeleteFlg("visible");
		this.cityMapper.saveById(city);
	}

	/**
	 * update inputted city info
	 *
	 * @param cityDto dto of city.
	 */
	@Override
	public void update(final CityDto cityDto) {
		final City city = new City();
		BeanUtils.copyProperties(cityDto, city, "continent", "nation");
		final String nationCode = this.countryMapper.getNationCode(cityDto.getNation());
		city.setCountryCode(nationCode);
		this.cityMapper.updateById(city);
	}

	/**
	 * remove city info by id provided
	 *
	 * @param id cityID
	 */
	@Override
	public void removeById(final Integer id) {
		this.cityMapper.removeById(id);
	}

	/**
	 * check the duplication of city name
	 *
	 * @param cityName name of city
	 * @return true: duplicated, false: can be inserted;
	 */
	@Override
	public Boolean checkDuplicated(final String cityName) {
		return 1 <= this.cityMapper.checkName(cityName);
	}

	/**
	 * get all continents' names
	 *
	 * @return list of name
	 */
	@Override
	public List<String> findAllContinents() {
		return this.countryMapper.getAllContinents();
	}

	/**
	 * get all nations' names in the selected continent
	 *
	 * @param continent selected continent
	 * @return list of names
	 */
	@Override
	public List<String> findNationsByCnt(final String continent) {
		return this.countryMapper.getNationsByCnt(continent);
	}

	/**
	 * get all nations' names in the selected city's continent
	 *
	 * @param id cityID
	 * @return list of names
	 */
	@Override
	public List<String> findNationsByCityId(final Integer id) {
		final List<String> nationList = new ArrayList<>();
		final CityDto cityDto = this.cityMapper.getCityInfoById(id);
		final String firstName = cityDto.getNation();
		nationList.add(firstName);
		final List<String> countries = this.countryMapper.getNationsByCnt(cityDto.getContinent()).stream()
				.filter(item -> StringUtils.isNotEqual(item, firstName)).collect(Collectors.toList());
		nationList.addAll(countries);
		return nationList;
	}

	/**
	 * get all cities by keyword.
	 *
	 * @param pageNum  pageNum
	 * @param pageSize pageSize
	 * @param keyword  name of nation
	 * @return pageList of cities
	 */
	@Override
	public Pagination<CityDto> findByKeywords(final Integer pageNum, final Integer pageSize, final String keyword) {
		final Integer pageMin = (pageNum - 1) * pageSize;
		final Integer pageMax = pageNum * pageSize;
		Pagination<CityDto> pages;
		if (StringUtils.isNotEmpty(keyword)) {
			if (StringUtils.isEqual("max(pop)", keyword)) {
				final List<CityDto> maximumRanks = this.cityMapper.getMaximumRanks();
				pages = Pagination.of(maximumRanks, 15, 1);
			} else if (StringUtils.isEqual("min(pop)", keyword)) {
				final List<CityDto> minimumRanks = this.cityMapper.getMinimumRanks();
				pages = Pagination.of(minimumRanks, 15, 1);
			} else {
				final Integer keyNationsCnt = this.cityMapper.getByNationsCnt(keyword);
				if (keyNationsCnt > 0) {
					final List<CityDto> keyNations = this.cityMapper.getByNations(keyword, pageMax, pageMin);
					pages = Pagination.of(keyNations, keyNationsCnt, pageNum);
				} else {
					final Integer keyNamesCnt = this.cityMapper.getByNamesCnt(keyword);
					final List<CityDto> keyNames = this.cityMapper.getByNames(keyword, pageMax, pageMin);
					pages = Pagination.of(keyNames, keyNamesCnt, pageNum);
				}
			}
		} else {
			final Integer cityInfosCnt = this.cityMapper.getCityInfosCnt();
			final List<CityDto> cityInfos = this.cityMapper.getCityInfos(pageMax, pageMin);
			pages = Pagination.of(cityInfos, cityInfosCnt, pageNum);
		}
		return pages;
	}
}
