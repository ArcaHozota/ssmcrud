package jp.co.toshiba.ppok.service;

import java.util.List;

import jp.co.toshiba.ppok.dto.CityDto;
import jp.co.toshiba.ppok.entity.City;

public interface CentreService {

	/**
	 * retrieve selected city info by id provided
	 * 
	 * @param id cityID
	 * @return cityDto
	 */
	City getCityInfo(Integer id);

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

	/**
	 * check the duplication of city name
	 * 
	 * @param cityName name of city
	 * @return true: duplicated, false: can be inserted;
	 */
	Boolean checkDuplicated(String cityName);

	/**
	 * get all continents' names
	 * 
	 * @return list of names
	 */
	List<String> findAllContinents();

	/**
	 * get all nations' names in the selected continent
	 * 
	 * @param continent selected continent
	 * @return list of names
	 */
	List<String> findNationsByCnt(String continent);

	/**
	 * get all nations' names in the selected city's continent
	 *
	 * @param id cityID
	 * @return list of names
	 */
	List<String> findNationsByCityId(Integer id);

	/**
	 * get all cities by keyword.
	 * 
	 * @param keyword name of nation
	 * @return list of cities
	 */
	List<CityDto> findByKeywords(String keyword);
}
