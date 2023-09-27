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
import jp.co.toshiba.ppok.utils.Messages;
import jp.co.toshiba.ppok.utils.Pagination;
import jp.co.toshiba.ppok.utils.StringUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import oracle.jdbc.driver.OracleSQLException;

/**
 * サービス実装クラス
 *
 * @author ArcaHozota
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
		final CityView cityView = this.cityViewMapper.getCityInfoById(id);
		final CityDto cityDto = new CityDto();
		BeanUtils.copyProperties(cityView, cityDto);
		return cityDto;
	}

	@Override
	public void save(final CityDto cityDto) {
		final City city = new City();
		BeanUtils.copyProperties(cityDto, city, "continent", "nation", "language");
		final Long saiban = this.cityMapper.saiban();
		final String nationCode = this.countryMapper.getNationCode(cityDto.getNation());
		city.setId(saiban);
		city.setCountryCode(nationCode);
		city.setDeleteFlg(Messages.MSG007);
		this.cityMapper.saveById(city);
	}

	@Override
	public void update(final CityDto cityDto) {
		final City city = new City();
		BeanUtils.copyProperties(cityDto, city, "continent", "nation", "language");
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
		return this.countryMapper.getNationsByCnt(StringUtils.toHankaku(continent));
	}

	@Override
	public List<String> findNationsByCityId(final Integer id) {
		final List<String> nationList = new ArrayList<>();
		final CityView cityView = this.cityViewMapper.getCityInfoById(id);
		final String firstName = cityView.getNation();
		nationList.add(firstName);
		final List<String> countries = this.countryMapper.getNationsByCnt(cityView.getContinent()).stream()
				.filter(item -> StringUtils.isNotEqual(firstName, item)).collect(Collectors.toList());
		nationList.addAll(countries);
		return nationList;
	}

	@Override
	public Pagination<CityDto> findByKeywords(final Integer pageNum, final Integer pageSize, final String keyword) {
		final int offset = (pageNum - 1) * pageSize;
		int sort = 100;
		if (StringUtils.isNotEmpty(keyword)) {
			final String hankakuKeyword = StringUtils.toHankaku(keyword);
			if (hankakuKeyword.startsWith("max(pop)")) {
				final int index = hankakuKeyword.indexOf(")");
				final String keisan = hankakuKeyword.substring(index + 1);
				if (StringUtils.isNotEmpty(keisan)) {
					sort = Integer.parseInt(keisan);
				}
				final List<CityDto> maximumRanks = this.cityViewMapper.getMaximumRanks(sort).stream().map(item -> {
					final CityDto cityDto = new CityDto();
					BeanUtils.copyProperties(item, cityDto);
					return cityDto;
				}).collect(Collectors.toList());
				if (pageNum * pageSize >= sort) {
					return Pagination.of(maximumRanks.subList(offset, sort), maximumRanks.size(), pageNum, pageSize);
				}
				return Pagination.of(maximumRanks.subList(offset, offset + pageSize), maximumRanks.size(), pageNum,
						pageSize);
			}
			if (hankakuKeyword.startsWith("min(pop)")) {
				final int index = hankakuKeyword.indexOf(")");
				final String keisan = hankakuKeyword.substring(index + 1);
				if (StringUtils.isNotEmpty(keisan)) {
					sort = Integer.parseInt(keisan);
				}
				final List<CityDto> minimumRanks = this.cityViewMapper.getMinimumRanks(sort).stream().map(item -> {
					final CityDto cityDto = new CityDto();
					BeanUtils.copyProperties(item, cityDto);
					return cityDto;
				}).collect(Collectors.toList());
				if (pageNum * pageSize >= sort) {
					return Pagination.of(minimumRanks.subList(offset, sort), minimumRanks.size(), pageNum, pageSize);
				}
				return Pagination.of(minimumRanks.subList(offset, pageNum * pageSize), minimumRanks.size(), pageNum,
						pageSize);
			}
			final Integer keyNationsCnt = this.cityViewMapper.getCityInfosByNationCnt(hankakuKeyword);
			if (keyNationsCnt > 0) {
				final List<CityDto> keyNations = this.cityViewMapper
						.getCityInfosByNation(hankakuKeyword, offset, pageSize).stream().map(item -> {
							final CityDto cityDto = new CityDto();
							BeanUtils.copyProperties(item, cityDto);
							return cityDto;
						}).collect(Collectors.toList());
				return Pagination.of(keyNations, keyNationsCnt, pageNum, pageSize);
			}
			final Integer keyNamesCnt = this.cityViewMapper.getCityInfosByNameCnt(hankakuKeyword);
			final List<CityDto> keyNames = this.cityViewMapper.getCityInfosByName(hankakuKeyword, offset, pageSize)
					.stream().map(item -> {
						final CityDto cityDto = new CityDto();
						BeanUtils.copyProperties(item, cityDto);
						return cityDto;
					}).collect(Collectors.toList());
			return Pagination.of(keyNames, keyNamesCnt, pageNum, pageSize);
		}
		final Integer cityInfosCnt = this.cityViewMapper.getCityInfosCnt();
		final List<CityDto> cityInfos = this.cityViewMapper.getCityInfos(offset, pageSize).stream().map(item -> {
			final CityDto cityDto = new CityDto();
			BeanUtils.copyProperties(item, cityDto);
			return cityDto;
		}).collect(Collectors.toList());
		return Pagination.of(cityInfos, cityInfosCnt, pageNum, pageSize);
	}

	@Override
	public String findLanguageByCty(final String nation) {
		return this.languageMapper.getLanguageByNationName(nation);
	}
}
