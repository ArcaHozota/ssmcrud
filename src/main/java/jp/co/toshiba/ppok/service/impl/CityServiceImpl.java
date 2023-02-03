package jp.co.toshiba.ppok.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import jp.co.toshiba.ppok.dto.CityDto;
import jp.co.toshiba.ppok.entity.City;
import jp.co.toshiba.ppok.entity.Country;
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
		final CityDto cityDto = new CityDto();
		final City city = this.cityMapper.getCityInfoById(id);
		final Country country = this.countryMapper.getNationById(city.getCountryCode());
		BeanUtils.copyProperties(city, cityDto);
		cityDto.setContinent(country.getContinent());
		cityDto.setNation(country.getName());
		return cityDto;
	}
}
