package jp.co.toshiba.ppok.repository;

import jp.co.toshiba.ppok.entity.CityInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * searching dao of table WORLD_CITY_VIEW
 *
 * @author Administrator
 * @date 2022-12-17
 */
@Repository
public interface CityInfoDao extends JpaRepository<CityInfo, Long> {

	/**
	 * Retrieve the nation list distinct.
	 * 
	 * @param continent name of continent
	 * @return List<CityInfo>
	 */
	@Query(value = "select distinct new CityInfo(cvw.nation) from CityInfo cvw where cvw.continent =:continent")
	List<CityInfo> getNations(@Param("continent") final String continent);

	/**
	 * Retrieve city infos by nation name provided.
	 * 
	 * @param nation name of nation
	 * @return List<CityInfo>
	 */
	@Query(value = "select new CityInfo(cvw.id,cvw.name,cvw.continent,cvw.nation,cvw.district,cvw.population) from CityInfo cvw where cvw.nation =:nation")
	List<CityInfo> findCityByNation(@Param("nation") final String nation);
}