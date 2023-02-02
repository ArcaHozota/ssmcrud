package jp.co.toshiba.ppok.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jp.co.toshiba.ppok.entity.Country;

/**
 * searching dao of table country
 *
 * @author Administrator
 * @date 2022-12-16
 */
public interface NationDao extends JpaRepository<Country, String> {

	/**
	 * Retrieve continent list distinct.
	 *
	 * @return List<CityInfo>
	 */
	@Query(value = "select distinct cty.continent from country cty", nativeQuery = true)
	List<String> findAllContinents();

	/**
	 * Retrieve the nation list distinct.
	 *
	 * @param continent name of continent
	 * @return List<CityInfo>
	 */
	@Query(value = "select distinct cty.name from country cty where cty.continent =:continent", nativeQuery = true)
	List<String> findNationsByCnt(@Param("continent") final String continent);

	/**
	 * Retrieve the nationcd through name.
	 *
	 * @param name name of nation
	 * @return List<CityInfo>
	 */
	Country findNationCode(@Param("name") final String name);
}
