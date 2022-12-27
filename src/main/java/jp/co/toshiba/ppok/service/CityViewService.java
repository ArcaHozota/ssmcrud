package jp.co.toshiba.ppok.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author Administrator
 */
public interface CityViewService extends IService<CityView> {

	/**
	 * Search continents of cities located on.
	 *
	 * @return List<CityDto>
	 */
	List<CityView> getContinents();

	/**
	 * Search nation's name of cities.
	 *
	 * @param continent name of continent which the nation located on.
	 * @return List<CityDto>
	 */
	List<CityView> getNations(String continent);

	/**
	 * Search city info by id.
	 *
	 * @param id city id
	 * @return List<CityDto>
	 */
	CityView getCityInfo(Long id);

	/**
	 * Save city info.
	 *
	 * @param cityView city info
	 */
	void saveCityInfo(CityView cityView);

	/**
	 * Update city info.
	 *
	 * @param cityView city info
	 */
	void updateCityInfo(CityView cityView);

	/**
	 * Delete city info by id.
	 *
	 * @param id city id
	 */
	void deleteCityInfo(Long id);

	/**
	 * Check the duplicated name of cities.
	 *
	 * @param name city name
	 * @return true: duplicated, false: insertable
	 */
	boolean checkDuplicated(String name);
}
