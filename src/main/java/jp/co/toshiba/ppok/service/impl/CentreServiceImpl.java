package jp.co.toshiba.ppok.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import oracle.jdbc.driver.OracleSQLException;

/**
 * サービス実装クラス
 *
 * @author Administrator
 * @since 6.71
 */
@Service
@Transactional(rollbackFor = OracleSQLException.class)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class CentreServiceImpl implements CentreService {

	/**
	 * Cityマッパー
	 */
	private final CityMapper cityMapper;

	/**
	 * Countryマッパー
	 */
	private final CountryMapper countryMapper;

	/**
	 * CityViewマッパー
	 */
	private final CityViewMapper cityViewMapper;

	/**
	 * Languageマッパー
	 */
	private final LanguageMapper languageMapper;

	@Override
	public CityDto getCityInfo(final Integer id) {
		final CityView cityInfoById = this.cityViewMapper.getCityInfoById(id);
		final CityDto cityDto = new CityDto();
		BeanUtils.copyProperties(cityInfoById, cityDto);
		final String language = this.findLanguageByCty(cityInfoById.getNation());
		cityDto.setLanguage(language);
		return cityDto;
	}

	@Override
	public void save(final CityDto cityDto) {
		final City city = new City();
		BeanUtils.copyProperties(cityDto, city, "continent", "nation");
		final Long saiban = this.cityMapper.saiban();
		final String nationCode = this.countryMapper.getNationCode(cityDto.getNation());
		city.setId(saiban);
		city.setCountryCode(nationCode);
		city.setLogicDeleteFlg("visible");
		this.cityMapper.saveById(city);
	}

	@Override
	public void update(final CityDto cityDto) {
		final City city = new City();
		BeanUtils.copyProperties(cityDto, city, "continent", "nation");
		final String nationCode = this.countryMapper.getNationCode(cityDto.getNation());
		city.setCountryCode(nationCode);
		this.cityMapper.updateById(city);
	}

	@Override
	public void removeById(final Integer id) {
		this.cityMapper.removeById(id);
	}

	@Override
	public Boolean checkDuplicated(final String cityName) {
		return 1 <= this.cityMapper.checkDuplicatedName(cityName);
	}

	@Override
	public List<String> findAllContinents() {
		return this.countryMapper.getAllContinents();
	}

	@Override
	public List<String> findNationsByCnt(final String continent) {
		return this.countryMapper.getNationsByCnt(continent);
	}

	@Override
	public List<String> findNationsByCityId(final Integer id) {
		final List<String> nationList = new ArrayList<>();
		final CityView cityInfo = this.cityViewMapper.getCityInfoById(id);
		final String firstName = cityInfo.getNation();
		nationList.add(firstName);
		final List<String> countries = this.countryMapper.getNationsByCnt(cityInfo.getContinent()).stream()
				.filter(item -> StringUtils.isNotEqual(firstName, item)).collect(Collectors.toList());
		nationList.addAll(countries);
		return nationList;
	}

	@Override
	public Pagination<CityDto> findByKeywords(final Integer pageNum, final Integer pageSize, final String keyword) {
		final Integer offset = (pageNum - 1) * pageSize;
		if (StringUtils.isNotEmpty(keyword)) {
			if (StringUtils.isEqual("max(pop)", keyword)) {
				final List<CityDto> maximumRanks = this.cityViewMapper.getMaximumRanks().stream().map(item -> {
					final CityDto cityDto = new CityDto();
					BeanUtils.copyProperties(item, cityDto);
					final String language = this.findLanguageByCty(item.getNation());
					cityDto.setLanguage(language);
					return cityDto;
				}).collect(Collectors.toList());
				return Pagination.of(maximumRanks, maximumRanks.size(), pageNum);
			}
			if (StringUtils.isEqual("min(pop)", keyword)) {
				final List<CityDto> minimumRanks = this.cityViewMapper.getMinimumRanks().stream().map(item -> {
					final CityDto cityDto = new CityDto();
					BeanUtils.copyProperties(item, cityDto);
					final String language = this.findLanguageByCty(item.getNation());
					cityDto.setLanguage(language);
					return cityDto;
				}).collect(Collectors.toList());
				return Pagination.of(minimumRanks, minimumRanks.size(), pageNum);
			}
			final Integer keyNationsCnt = this.cityViewMapper.getByNationsCnt(keyword);
			if (keyNationsCnt > 0) {
				final List<CityDto> keyNations = this.cityViewMapper.getByNations(keyword, offset, pageSize).stream()
						.map(item -> {
							final CityDto cityDto = new CityDto();
							BeanUtils.copyProperties(item, cityDto);
							final String language = this.findLanguageByCty(item.getNation());
							cityDto.setLanguage(language);
							return cityDto;
						}).collect(Collectors.toList());
				return Pagination.of(keyNations, keyNationsCnt, pageNum);
			}
			final Integer keyNamesCnt = this.cityViewMapper.getByNamesCnt(keyword);
			final List<CityDto> keyNames = this.cityViewMapper.getByNames(keyword, offset, pageSize).stream()
					.map(item -> {
						final CityDto cityDto = new CityDto();
						BeanUtils.copyProperties(item, cityDto);
						final String language = this.findLanguageByCty(item.getNation());
						cityDto.setLanguage(language);
						return cityDto;
					}).collect(Collectors.toList());
			return Pagination.of(keyNames, keyNamesCnt, pageNum);
		}
		final Integer cityInfosCnt = this.cityViewMapper.getCityInfosCnt();
		final List<CityDto> cityInfos = this.cityViewMapper.getCityInfos(offset, pageSize).stream().map(item -> {
			final CityDto cityDto = new CityDto();
			BeanUtils.copyProperties(item, cityDto);
			final String language = this.findLanguageByCty(item.getNation());
			cityDto.setLanguage(language);
			return cityDto;
		}).collect(Collectors.toList());
		return Pagination.of(cityInfos, cityInfosCnt, pageNum);
	}

	@Override
	public String findLanguageByCty(final String nation) {
		return this.languageMapper.getLanguage(nation);
	}
}
