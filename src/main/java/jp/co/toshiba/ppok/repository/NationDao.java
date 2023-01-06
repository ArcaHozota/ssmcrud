package jp.co.toshiba.ppok.repository;

import jp.co.toshiba.ppok.entity.Nation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * searching dao of table WORLD_COUNTRY
 *
 * @author Administrator
 * @date 2022-12-16
 */
@Repository
public interface NationDao extends JpaRepository<Nation, String> {

	/**
	 * Retrieve the nation list distinct.
	 *
	 * @param continent name of continent
	 * @return List<CityInfo>
	 */
	List<Nation> findNationsByCnt(@Param("continent") final String continent);

	/**
	 * Retrieve the nationcd through name.
	 *
	 * @param name name of nation
	 * @return List<CityInfo>
	 */
	Nation findNationCode(@Param("name") final String name);

	/**
	 * Retrieve continent list distinct.
	 *
	 * @return List<CityInfo>
	 */
	@Query(value = "select distinct cty.continent from country cty", nativeQuery = true)
	List<Nation> findAllContinents();
}
