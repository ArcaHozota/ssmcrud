package jp.co.toshiba.ppok.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import jp.co.toshiba.ppok.dto.CityDto;
import jp.co.toshiba.ppok.entity.City;
import jp.co.toshiba.ppok.mapper.CityMapper;
import jp.co.toshiba.ppok.mapper.CountryMapper;
import jp.co.toshiba.ppok.service.CityService;

@Service
public class CityServiceImpl implements CityService {

	@Resource
	private CityMapper cityMapper;

	@Resource
	private CountryMapper countryMapper;

	@Override
	public CityDto getCityInfo(final Integer id) {
		return this.cityMapper.getCityInfoById(id);
	}

	@Override
	public void save(final CityDto cityDto) {
		final City city = new City();
		BeanUtils.copyProperties(cityDto, city, "continent", "nation");
		final String nationCode = this.countryMapper.getNationCode(cityDto.getNation());
		city.setCountryCode(nationCode);
		city.setIsDeleted(0);
		this.cityMapper.insert(city);
	}

	@Override
	public void update(final CityDto cityDto) {
		final City city = new City();
		BeanUtils.copyProperties(cityDto, city, "continent", "nation");
		final String nationCode = this.countryMapper.getNationCode(cityDto.getNation());
		city.setCountryCode(nationCode);
		city.setIsDeleted(0);
		this.cityMapper.updateSelective(city);
	}
}
