package jp.co.toshiba.ppok.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.cj.jdbc.exceptions.MySQLTransactionRollbackException;

import jp.co.toshiba.ppok.dto.CityDto;
import jp.co.toshiba.ppok.entity.City;

/**
 * searching dao of table city
 *
 * @author Administrator
 * @date 2022-12-17
 */
@Mapper
public interface CityMapper {

	/**
	 * Retrieve city infos by nation name provided.
	 *
	 * @param nation name of nation
	 * @return List<City>
	 */
	List<CityDto> getByNations(@Param("nation") String nation);

	/**
	 * Retrieve city infos by city name provided.
	 *
	 * @param name city name
	 * @return Page<City>
	 */
	List<CityDto> getByNames(@Param("name") String name);

	/**
	 * Retrieve city infos.
	 *
	 * @return Page<City>
	 */
	List<CityDto> getCityInfos();

	/**
	 * Retrieve city infos by population ascending.
	 *
	 * @return Page<City>
	 */
	List<CityDto> getMinimumRanks();

	/**
	 * Retrieve city infos by population descending.
	 *
	 * @return Page<City>
	 */
	List<CityDto> getMaximumRanks();

	/**
	 * logic remove query.
	 *
	 * @param id id of the selected city
	 */
	@Transactional(rollbackFor = MySQLTransactionRollbackException.class)
	void removeById(@Param("id") Integer id);

	/**
	 * Retrieve city infos by id.
	 *
	 * @param id city id
	 * @return entity of city
	 */
	City getCityInfoById(@Param("id") Integer id);

	/**
	 * Save the city info.
	 *
	 * @param city entity
	 */
	void insert(City city);

	/**
	 * Update the selected city info.
	 *
	 * @param city entity
	 */
	void updateSelective(City city);

	/**
	 * Check the duplication of name.
	 * 
	 * @param cityName city name
	 * @return 0: no duplication, 1 or more: duplicated.
	 */
	Integer checkName(@Param("cityName") String cityName);
}