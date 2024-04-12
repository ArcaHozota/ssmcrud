package jp.co.toshiba.ppok.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import jp.co.toshiba.ppok.dto.CityDto;
import jp.co.toshiba.ppok.entity.City;
import jp.co.toshiba.ppok.entity.CityView;
import jp.co.toshiba.ppok.mapper.CityMapper;
import jp.co.toshiba.ppok.mapper.CityViewMapper;
import jp.co.toshiba.ppok.service.CentreService;
import jp.co.toshiba.ppok.utils.Messages;
import jp.co.toshiba.ppok.utils.Pagination;
import jp.co.toshiba.ppok.utils.RestMsg;
import jp.co.toshiba.ppok.utils.SecondBeanUtils;
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
	 * ナビゲーションのページ数
	 */
	private static final Integer NAVI_PAGES = 7;

	/**
	 * Cityマッパー
	 */
	private final CityMapper cityMapper;

	/**
	 * CityViewマッパー
	 */
	private final CityViewMapper cityViewMapper;

	@Override
	public Boolean checkDuplicated(final String cityName) {
		return 1 <= this.cityMapper.checkDuplicatedName(cityName);
	}

	@Override
	public List<String> findAllContinents() {
		return this.cityViewMapper.getAllContinents();
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
				final List<CityDto> maximumRanks = this.cityViewMapper.getMaximumRanks(sort).stream()
						.map(item -> new CityDto(item.getId(), item.getName(), item.getContinent(), item.getNation(),
								item.getDistrict(), item.getPopulation(), item.getLanguage()))
						.toList();
				if ((pageNum * pageSize) >= sort) {
					return Pagination.of(maximumRanks.subList(offset, sort), maximumRanks.size(), pageNum, pageSize,
							CentreServiceImpl.NAVI_PAGES);
				}
				return Pagination.of(maximumRanks.subList(offset, offset + pageSize), maximumRanks.size(), pageNum,
						pageSize, CentreServiceImpl.NAVI_PAGES);
			}
			if (hankakuKeyword.startsWith("min(pop)")) {
				final int index = hankakuKeyword.indexOf(")");
				final String keisan = hankakuKeyword.substring(index + 1);
				if (StringUtils.isNotEmpty(keisan)) {
					sort = Integer.parseInt(keisan);
				}
				final List<CityDto> minimumRanks = this.cityViewMapper.getMinimumRanks(sort).stream()
						.map(item -> new CityDto(item.getId(), item.getName(), item.getContinent(), item.getNation(),
								item.getDistrict(), item.getPopulation(), item.getLanguage()))
						.toList();
				if ((pageNum * pageSize) >= sort) {
					return Pagination.of(minimumRanks.subList(offset, sort), minimumRanks.size(), pageNum, pageSize,
							CentreServiceImpl.NAVI_PAGES);
				}
				return Pagination.of(minimumRanks.subList(offset, pageNum * pageSize), minimumRanks.size(), pageNum,
						pageSize, CentreServiceImpl.NAVI_PAGES);
			}
			final Integer keyNationsCnt = this.cityViewMapper.getCityInfosByNationCnt(hankakuKeyword);
			if (keyNationsCnt > 0) {
				final List<CityDto> keyNations = this.cityViewMapper
						.getCityInfosByNation(hankakuKeyword, offset, pageSize).stream()
						.map(item -> new CityDto(item.getId(), item.getName(), item.getContinent(), item.getNation(),
								item.getDistrict(), item.getPopulation(), item.getLanguage()))
						.toList();
				return Pagination.of(keyNations, keyNationsCnt, pageNum, pageSize, CentreServiceImpl.NAVI_PAGES);
			}
			final Integer keyNamesCnt = this.cityViewMapper.getCityInfosByNameCnt(hankakuKeyword);
			final List<CityDto> keyNames = this.cityViewMapper.getCityInfosByName(hankakuKeyword, offset, pageSize)
					.stream().map(item -> new CityDto(item.getId(), item.getName(), item.getContinent(),
							item.getNation(), item.getDistrict(), item.getPopulation(), item.getLanguage()))
					.toList();
			return Pagination.of(keyNames, keyNamesCnt, pageNum, pageSize, CentreServiceImpl.NAVI_PAGES);
		}
		final Integer cityInfosCnt = this.cityViewMapper.getCityInfosCnt();
		final Integer pageMax = (cityInfosCnt / pageSize) + ((cityInfosCnt % pageSize) != 0 ? 1 : 0);
		if (pageNum > pageMax) {
			final List<CityDto> cityInfos = this.cityViewMapper.getCityInfos((pageMax - 1) * pageSize, pageSize)
					.stream().map(item -> new CityDto(item.getId(), item.getName(), item.getContinent(),
							item.getNation(), item.getDistrict(), item.getPopulation(), item.getLanguage()))
					.toList();
			return Pagination.of(cityInfos, cityInfosCnt, pageMax, pageSize, CentreServiceImpl.NAVI_PAGES);
		}
		final List<CityDto> cityInfos = this.cityViewMapper.getCityInfos(offset, pageSize).stream()
				.map(item -> new CityDto(item.getId(), item.getName(), item.getContinent(), item.getNation(),
						item.getDistrict(), item.getPopulation(), item.getLanguage()))
				.toList();
		return Pagination.of(cityInfos, cityInfosCnt, pageNum, pageSize, CentreServiceImpl.NAVI_PAGES);
	}

	@Override
	public String findLanguageByCty(final String nation) {
		return this.cityViewMapper.getLanguageByNation(nation);
	}

	@Override
	public List<String> findNationsByCnt(final String continent) {
		if (StringUtils.isDigital(continent)) {
			final Integer id = Integer.parseInt(continent);
			final List<String> nationList = Lists.newArrayList();
			final CityView cityView = this.cityViewMapper.getCityInfoById(id);
			final String firstName = cityView.getNation();
			nationList.add(firstName);
			final List<String> countries = this.cityViewMapper.getNationsByCnt(cityView.getContinent()).stream()
					.filter(item -> StringUtils.isNotEqual(firstName, item)).toList();
			nationList.addAll(countries);
			return nationList;
		}
		return this.cityViewMapper.getNationsByCnt(StringUtils.toHankaku(continent));
	}

	@Override
	public CityDto getCityInfo(final Integer id) {
		final CityView cityView = this.cityViewMapper.getCityInfoById(id);
		return new CityDto(cityView.getId(), cityView.getName(), cityView.getContinent(), cityView.getNation(),
				cityView.getDistrict(), cityView.getPopulation(), cityView.getLanguage());
	}

	@Override
	public RestMsg removeById(final Integer id) {
		this.cityMapper.removeById(id);
		return RestMsg.success(Messages.MSG013);
	}

	@Override
	public RestMsg save(final CityDto cityDto) {
		final City city = new City();
		final Long saiban = this.cityMapper.saiban();
		final String nationCode = this.cityViewMapper.getNationCode(cityDto.nation());
		SecondBeanUtils.copyNullableProperties(cityDto, city);
		city.setId(saiban);
		city.setCountryCode(nationCode);
		city.setDeleteFlg(Messages.MSG007);
		try {
			this.cityMapper.saveById(city);
		} catch (final Exception e) {
			return RestMsg.failure().add("errorMsg", Messages.MSG009);
		}
		return RestMsg.success(Messages.MSG011);
	}

	@Override
	public RestMsg update(final CityDto cityDto) {
		final City city = this.cityMapper.selectById(cityDto.id());
		final City original = new City();
		SecondBeanUtils.copyNullableProperties(city, original);
		final String nationCode = this.cityViewMapper.getNationCode(cityDto.nation());
		city.setCountryCode(nationCode);
		SecondBeanUtils.copyNullableProperties(cityDto, city);
		if (original.equals(city)) {
			return RestMsg.failure().add("errorMsg", Messages.MSG012);
		}
		this.cityMapper.updateById(city);
		return RestMsg.success(Messages.MSG010);
	}
}
