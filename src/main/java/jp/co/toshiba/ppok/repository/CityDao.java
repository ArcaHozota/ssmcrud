package jp.co.toshiba.ppok.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jp.co.toshiba.ppok.entity.City;

/**
 * searching dao of table WORLD_CITY
 *
 * @author Administrator
 * @date 2022-12-17
 */
@Repository
public interface CityDao extends JpaRepository<City, Integer> {

	/**
	 * logic remove query.
	 *
	 * @param id id of the selected city
	 */
	@Modifying
	@Transactional
	void removeById(@Param("id") final Integer id);
}
