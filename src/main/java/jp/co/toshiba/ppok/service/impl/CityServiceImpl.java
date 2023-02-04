package jp.co.toshiba.ppok.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import jp.co.toshiba.ppok.dto.CityDto;
import jp.co.toshiba.ppok.entity.City;
import jp.co.toshiba.ppok.mapper.CityMapper;
import jp.co.toshiba.ppok.mapper.CountryMapper;
import jp.co.toshiba.ppok.service.CityService;
import jp.co.toshiba.ppok.utils.StringUtils;

@Service
public class CityServiceImpl implements CityService {

	@Resource
	private CityMapper cityMapper;

	@Resource
	private CountryMapper countryMapper;

	/**
	 * retrieve selected city info by id provided
	 *
	 * @param id cityID
	 * @return cityDto
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
		city.setIsDeleted(0);
		this.cityMapper.insert(city);
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
		city.setIsDeleted(0);
		this.cityMapper.updateSelective(city);
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
		final List<String> nationList = Lists.newArrayList();
		final CityDto cityDto = this.cityMapper.getCityInfoById(id);
		nationList.add(cityDto.getNation());
		final List<String> countries = this.countryMapper.getNationsByCnt(cityDto.getContinent());
		countries.forEach(item -> {
			if (StringUtils.isNotEqual(cityDto.getNation(), item)) {
				nationList.add(item);
			}
		});
		return nationList;
	}
}
