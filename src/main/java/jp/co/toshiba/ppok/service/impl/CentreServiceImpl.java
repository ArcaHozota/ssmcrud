package jp.co.toshiba.ppok.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.toshiba.ppok.dto.CityDto;
import jp.co.toshiba.ppok.entity.City;
import jp.co.toshiba.ppok.entity.CityView;
import jp.co.toshiba.ppok.mapper.CityMapper;
import jp.co.toshiba.ppok.mapper.CityViewMapper;
import jp.co.toshiba.ppok.mapper.CountryMapper;
import jp.co.toshiba.ppok.mapper.LanguageMapper;
import jp.co.toshiba.ppok.service.CentreService;
import jp.co.toshiba.ppok.utils.Pagination;
import jp.co.toshiba.ppok.utils.StringUtils;
import oracle.jdbc.driver.OracleSQLException;

/**
 * Implementation class of central service interface
 *
 * @author Administrator
 */
@Service
@Transactional(rollbackFor = OracleSQLException.class)
public class CentreServiceImpl implements CentreService {

	/**
	 * City mapper
	 */
	@Resource
	private CityMapper cityMapper;

	/**
	 * Country mapper
	 */
	@Resource
	private CountryMapper countryMapper;

	/**
	 * CityInfo mapper
	 */
	@Resource
	private CityViewMapper cityViewMapper;

	/**
	 * Language mapper
	 */
	@Resource
	private LanguageMapper languageMapper;

	/**
	 * retrieve selected city info by id provided
	 *
	 * @param id cityID
	 * @return entity of city
	 */
	@Override
	public CityDto getCityInfo(final Integer id) {
		final CityView cityInfoById = this.cityViewMapper.getCityInfoById(id);
		final CityDto cityDto = new CityDto();
		BeanUtils.copyProperties(cityInfoById, cityDto);
		final String nationCode = this.countryMapper.getNationCode(cityInfoById.getNation());
		final String language = this.languageMapper.getLanguage(nationCode);
		cityDto.setLanguage(language);
		return cityDto;
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
		return 1 <= this.cityViewMapper.checkName(cityName);
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
		final CityView cityInfo = this.cityViewMapper.getCityInfoById(id);
		final String firstName = cityInfo.getNation();
		nationList.add(firstName);
		final List<String> countries = this.countryMapper.getNationsByCnt(cityInfo.getContinent()).stream()
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
		if (StringUtils.isNotEmpty(keyword)) {
			if (StringUtils.isEqual("max(pop)", keyword)) {
				final List<CityDto> maximumRanks = this.cityViewMapper.getMaximumRanks().stream().map(item -> {
					final CityDto cityDto = new CityDto();
					BeanUtils.copyProperties(item, cityDto);
					final String nationCode = this.countryMapper.getNationCode(item.getNation());
					final String language = this.languageMapper.getLanguage(nationCode);
					cityDto.setLanguage(language);
					return cityDto;
				}).collect(Collectors.toList());
				return Pagination.of(maximumRanks, 15, 1);
			} else if (StringUtils.isEqual("min(pop)", keyword)) {
				final List<CityDto> minimumRanks = this.cityViewMapper.getMinimumRanks().stream().map(item -> {
					final CityDto cityDto = new CityDto();
					BeanUtils.copyProperties(item, cityDto);
					final String nationCode = this.countryMapper.getNationCode(item.getNation());
					final String language = this.languageMapper.getLanguage(nationCode);
					cityDto.setLanguage(language);
					return cityDto;
				}).collect(Collectors.toList());
				return Pagination.of(minimumRanks, 15, 1);
			} else {
				final Integer keyNationsCnt = this.cityViewMapper.getByNationsCnt(keyword);
				if (keyNationsCnt > 0) {
					final List<CityDto> keyNations = this.cityViewMapper.getByNations(keyword, pageMax, pageMin)
							.stream().map(item -> {
								final CityDto cityDto = new CityDto();
								BeanUtils.copyProperties(item, cityDto);
								final String nationCode = this.countryMapper.getNationCode(item.getNation());
								final String language = this.languageMapper.getLanguage(nationCode);
								cityDto.setLanguage(language);
								return cityDto;
							}).collect(Collectors.toList());
					return Pagination.of(keyNations, keyNationsCnt, pageNum);
				} else {
					final Integer keyNamesCnt = this.cityViewMapper.getByNamesCnt(keyword);
					final List<CityDto> keyNames = this.cityViewMapper.getByNames(keyword, pageMax, pageMin).stream()
							.map(item -> {
								final CityDto cityDto = new CityDto();
								BeanUtils.copyProperties(item, cityDto);
								final String nationCode = this.countryMapper.getNationCode(item.getNation());
								final String language = this.languageMapper.getLanguage(nationCode);
								cityDto.setLanguage(language);
								return cityDto;
							}).collect(Collectors.toList());
					return Pagination.of(keyNames, keyNamesCnt, pageNum);
				}
			}
		}
		final Integer cityInfosCnt = this.cityViewMapper.getCityInfosCnt();
		final List<CityDto> cityInfos = this.cityViewMapper.getCityInfos(pageMax, pageMin).stream().map(item -> {
			final CityDto cityDto = new CityDto();
			BeanUtils.copyProperties(item, cityDto);
			final String nationCode = this.countryMapper.getNationCode(item.getNation());
			final String language = this.languageMapper.getLanguage(nationCode);
			cityDto.setLanguage(language);
			return cityDto;
		}).collect(Collectors.toList());
		return Pagination.of(cityInfos, cityInfosCnt, pageNum);
	}

	/**
	 * get language by nation
	 *
	 * @param nation name of nation
	 * @return language name
	 */
	@Override
	public String findLanguageByCty(final String nation) {
		final String nationCode = this.countryMapper.getNationCode(nation);
		return this.languageMapper.getLanguage(nationCode);
	}
}
