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
	List<City> findByNations(@Param("nation") final String nation);

	/**
	 * Retrieve city infos by nation name provided.
	 *
	 * @param nation   name of nation
	 * @param pageable page
	 * @return Page<City>
	 */
	Page<City> getByNations(@Param("nation") final String nation, final Pageable pageable);

	/**
	 * Retrieve city infos by city name provided.
	 *
	 * @param name     city name
	 * @param pageable page
	 * @return Page<City>
	 */
	Page<City> getByNames(@Param("name") final String name, final Pageable pageable);

	/**
	 * Retrieve city infos.
	 *
	 * @param pageable page
	 * @return Page<City>
	 */
	Page<City> getCityInfos(final Pageable pageable);

	/**
	 * Retrieve city infos by population ascending.
	 *
	 * @return Page<City>
	 */
	@Query(value = "select cn.id, cn.name, cn.country_code, cn.distrcit, cn.population from city cn where cn.is_deleted = 0 order by cn.population asc fetch first 15 rows only", nativeQuery = true)
	List<City> findMinimumRanks();

	/**
	 * Retrieve city infos by population descending.
	 *
	 * @return Page<City>
	 */
	@Query(value = "select cn.id, cn.name, cn.country_code, cn.distrcit, cn.population from city cn where cn.is_deleted = 0 order by cn.population desc fetch first 15 rows only", nativeQuery = true)
	List<City> findMaximumRanks();

	/**
	 * logic remove query.
	 *
	 * @param id id of the selected city
	 */
	@Modifying
	@Transactional(rollbackFor = MySQLTransactionRollbackException.class)
	void removeById(@Param("id") final Integer id);
}