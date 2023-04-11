package jp.co.toshiba.ppok.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

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
	 * Save inputed cityInfo.
	 *
	 * @param city entity
	 */
	@Transactional(rollbackFor = OracleSQLException.class)
	void saveById(City city);

	/**
	 * Logic deletion
	 *
	 * @param id id of the selected city
	 */
	@Transactional(rollbackFor = OracleSQLException.class)
	void removeById(@Param("id") Integer id);

	/**
	 * Update the selected cityInfo.
	 *
	 * @param city entity
	 */
	@Transactional(rollbackFor = OracleSQLException.class)
	void updateById(City city);
}