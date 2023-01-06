package jp.co.toshiba.ppok.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.toshiba.ppok.entity.CityInfo;

/**
 * searching dao of table WORLD_CITY_VIEW
 *
 * @author Administrator
 * @date 2022-12-17
 */
@Repository
public interface CityInfoDao extends JpaRepository<CityInfo, Integer> {

	/**
	 * Retrieve city infos by nation name provided.
	 *
	 * @param nation name of nation
	 * @return List<CityInfo>
	 */
	List<CityInfo> findByNations(@Param("nation") final String nation);

	/**
	 * Retrieve city infos by nation name provided.
	 *
	 * @param nation name of nation
	 * @param pageable page
	 * @return List<CityInfo>
	 */
	Page<CityInfo> getByNations(@Param("nation") final String nation, final Pageable pageable);

	/**
	 * Retrieve city infos by nation name provided.
	 *
	 * @param name city name
	 * @param pageable page
	 * @return List<CityInfo>
	 */
	Page<CityInfo> getByNames(@Param("name") final String name, final Pageable pageable);
}