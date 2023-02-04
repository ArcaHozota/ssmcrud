package jp.co.toshiba.ppok.service;

import jp.co.toshiba.ppok.dto.CityDto;

public interface CityService {

	CityDto getCityInfo(Integer id);

	void save(CityDto cityDto);

	void update(CityDto cityDto);
}
