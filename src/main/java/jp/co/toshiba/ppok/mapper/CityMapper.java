package jp.co.toshiba.ppok.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import jp.co.toshiba.ppok.dto.CityDto;
import jp.co.toshiba.ppok.entity.City;
import oracle.jdbc.driver.OracleSQLException;

/**
 * Mapper of Table WORLD_CITY
 *
 * @author Administrator
 * @date 2022-12-17
 */
@Mapper
public interface CityMapper {

	/**
	 * Retrieve cityInfos by nation name provided.
	 *
	 * @param nation  name of nation
	 * @param pageMax paging maximum
	 * @param pageMin paging minimum
	 * @return List<CityDto>
	 */
	List<CityDto> getByNations(@Param("nation") String nation, @Param("pageMax") Integer pageMax,
			@Param("pageMin") Integer pageMin);

	/**
	 * Retrieve the number of cityInfos by nation name provided.
	 *
	 * @param nation name of nation
	 * @return Integer
	 */
	Integer getByNationsCnt(@Param("nation") String nation);

	/**
	 * Retrieve cityInfos by city name provided.
	 *
	 * @param name    city name
	 * @param pageMax paging maximum
	 * @param pageMin paging minimum
	 * @return List<CityDto>
	 */
	List<CityDto> getByNames(@Param("name") String name, @Param("pageMax") Integer pageMax,
			@Param("pageMin") Integer pageMin);

	/**
	 * Retrieve the number of cityInfos by city name provided.
	 *
	 * @param name city name
	 * @return Integer
	 */
	Integer getByNamesCnt(@Param("name") String name);

	/**
	 * Retrieve cityInfos.
	 *
	 * @param pageMax paging maximum
	 * @param pageMin paging minimum
	 * @return List<CityDto>
	 */
	List<CityDto> getCityInfos(@Param("pageMax") Integer pageMax, @Param("pageMin") Integer pageMin);

	/**
	 * Retrieve the number of cityInfos.
	 *
	 * @return Integer
	 */
	Integer getCityInfosCnt();

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
	@Transactional(rollbackFor = OracleSQLException.class)
	void removeById(@Param("id") Integer id);

	/**
	 * Retrieve city infos by id.
	 *
	 * @param id city id
	 * @return entity of city
	 */
	CityDto getCityInfoById(@Param("id") Integer id);

	/**
	 * Save the city info.
	 *
	 * @param city entity
	 */
	@Transactional(rollbackFor = OracleSQLException.class)
	void saveById(City city);

	/**
	 * Update the selected city info.
	 *
	 * @param city entity
	 */
	@Transactional(rollbackFor = OracleSQLException.class)
	void updateById(City city);

	/**
	 * Check the duplication of name.
	 *
	 * @param cityName city name
	 * @return 0: no duplication, 1 or more: duplicated.
	 */
	Integer checkName(@Param("cityName") String cityName);
}