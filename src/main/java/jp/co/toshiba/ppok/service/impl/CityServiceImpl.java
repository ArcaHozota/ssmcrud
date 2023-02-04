package jp.co.toshiba.ppok.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import jp.co.toshiba.ppok.dto.CityDto;
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
}
