package jp.co.toshiba.ppok.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.toshiba.ppok.entity.Country;

/**
 * searching dao of table country
 *
 * @author Administrator
 * @date 2022-12-16
 */
@Mapper
public interface CountryMapper {

	/**
	 * Retrieve continent list distinct.
	 *
	 * @return List<CityInfo>
	 */
	List<String> findAllContinents();

	/**
	 * Retrieve the nation list distinct.
	 *
	 * @param continent name of continent
	 * @return List<CityInfo>
	 */
	List<String> findNationsByCnt(@Param("continent") String continent);

	/**
	 * Retrieve the nationcd through name.
	 *
	 * @param name name of nation
	 * @return List<CityInfo>
	 */
	Country getNationCode(@Param("name") String name);
}
