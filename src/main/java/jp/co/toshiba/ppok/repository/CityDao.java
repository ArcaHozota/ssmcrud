package jp.co.toshiba.ppok.repository;

import org.postgresql.util.PSQLException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import jp.co.toshiba.ppok.entity.City;

/**
 * searching dao of table city
 *
 * @author Administrator
 * @date 2022-12-17
 */
public interface CityDao extends JpaRepository<City, Integer> {

	/**
	 * logic remove query.
	 *
	 * @param id id of the selected city
	 */
	@Modifying
	@Transactional(rollbackFor = PSQLException.class)
	void removeById(@Param("id") final Integer id);
}
