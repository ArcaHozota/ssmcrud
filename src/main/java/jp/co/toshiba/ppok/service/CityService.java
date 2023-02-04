package jp.co.toshiba.ppok.service;

import jp.co.toshiba.ppok.dto.CityDto;

public interface CityService {

	/**
	 * retrieve selected city info by id provided
	 * 
	 * @param id cityID
	 * @return cityDto
	 */
	CityDto getCityInfo(Integer id);

	/**
	 * save inputted city info
	 * 
	 * @param cityDto dto of city.
	 */
	void save(CityDto cityDto);

	/**
	 * update inputted city info
	 *
	 * @param cityDto dto of city.
	 */
	void update(CityDto cityDto);

	/**
	 * remove city info by id provided
	 * 
	 * @param id cityID
	 */
	void removeById(Integer id);
}
