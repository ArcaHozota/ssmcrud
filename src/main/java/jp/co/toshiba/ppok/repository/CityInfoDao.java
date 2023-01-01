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
	 * retrieve the continent list distinct.
	 * 
	 * @return List<CityInfo>
	 */
	@Query(value = "select distinct new CityInfo(cvw.continent) from CityInfo cvw")
	List<CityInfo> getContinents();

	/**
	 * retrieve the nation list distinct.
	 * 
	 * @param continent name of continent
	 * @return List<CityInfo>
	 */
	@Query(value = "select distinct new CityInfo(cvw.name,cvw.nation) from CityInfo cvw where cvw.continent =:continent")
	List<CityInfo> getNations(@Param("continent") final String continent);
}