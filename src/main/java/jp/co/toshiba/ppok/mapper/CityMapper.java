package jp.co.toshiba.ppok.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.cj.jdbc.exceptions.MySQLTransactionRollbackException;

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
	List<City> getByNations(@Param("nation") final String nation);

	/**
	 * Retrieve city infos by city name provided.
	 *
	 * @param name city name
	 * @return Page<City>
	 */
	List<City> getByNames(@Param("name") final String name);

	/**
	 * Retrieve city infos.
	 *
	 * @return Page<City>
	 */
	List<City> getCityInfos();

	/**
	 * Retrieve city infos by population ascending.
	 *
	 * @return Page<City>
	 */
	List<City> findMinimumRanks();

	/**
	 * Retrieve city infos by population descending.
	 *
	 * @return Page<City>
	 */
	List<City> findMaximumRanks();

	/**
	 * logic remove query.
	 *
	 * @param id id of the selected city
	 */
	@Transactional(rollbackFor = MySQLTransactionRollbackException.class)
	void removeById(@Param("id") final Integer id);
}