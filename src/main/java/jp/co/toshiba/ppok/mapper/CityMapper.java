package jp.co.toshiba.ppok.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.postgresql.util.PSQLException;
import org.springframework.transaction.annotation.Transactional;

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
	 * Retrieve cityInfos by nation name provided.
	 *
	 * @param nation name of nation
	 * @return List<City>
	 */
	List<CityDto> getByNations(@Param("nation") String nation, @Param("pageSize") Integer pageSize,
			@Param("offset") Integer offset);

	/**
	 * Retrieve the number of cityInfos by nation name provided.
	 *
	 * @param nation name of nation
	 * @return List<City>
	 */
	Integer getByNationsCnt(@Param("nation") String nation);

	/**
	 * Retrieve cityInfos by city name provided.
	 *
	 * @param name city name
	 * @return Page<City>
	 */
	List<CityDto> getByNames(@Param("name") String name, @Param("pageSize") Integer pageSize,
			@Param("offset") Integer offset);

	/**
	 * Retrieve the number of cityInfos by city name provided.
	 *
	 * @param name city name
	 * @return Page<City>
	 */
	Integer getByNamesCnt(@Param("name") String name);

	/**
	 * Retrieve cityInfos.
	 *
	 * @return Page<City>
	 */
	List<CityDto> getCityInfos(@Param("pageSize") Integer pageSize, @Param("offset") Integer offset);

	/**
	 * Retrieve the number of cityInfos.
	 *
	 * @return Page<City>
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
	@Transactional(rollbackFor = PSQLException.class)
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
	@Transactional(rollbackFor = PSQLException.class)
	void insert(City city);

	/**
	 * Update the selected city info.
	 *
	 * @param city entity
	 */
	@Transactional(rollbackFor = PSQLException.class)
	void updateSelective(City city);

	/**
	 * Check the duplication of name.
	 *
	 * @param cityName city name
	 * @return 0: no duplication, 1 or more: duplicated.
	 */
	Integer checkName(@Param("cityName") String cityName);
}