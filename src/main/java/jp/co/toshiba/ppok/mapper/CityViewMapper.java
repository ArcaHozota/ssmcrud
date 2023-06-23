/**
 *
 */
package jp.co.toshiba.ppok.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.toshiba.ppok.entity.CityView;

/**
 * Mapper of View WORLD_CITY_VIEW
 *
 * @author Administrator
 * @date 2022-12-27
 */
@Mapper
public interface CityViewMapper {

	/**
	 * Retrieve cityInfos by nation name provided.
	 *
	 * @param nation   name of nation
	 * @param offset   offset
	 * @param pageSize pageSize
	 * @return List<CityInfo>
	 */
	List<CityView> getByNations(@Param("nation") String nation, @Param("offset") Integer offset,
			@Param("pageSize") Integer pageSize);

	/**
	 * Retrieve the number of cityInfos by nation name provided.
	 *
	 * @param nation name of nation
	 * @return Integer
	 */
	Integer getByNationsCnt(@Param("nation") String nation);

	/**
	 * Retrieve cityInfos by city name provided.
	 *
	 * @param name     city name
	 * @param offset   offset
	 * @param pageSize pageSize
	 * @return List<CityInfo>
	 */
	List<CityView> getByNames(@Param("name") String name, @Param("offset") Integer offset,
			@Param("pageSize") Integer pageSize);

	/**
	 * Retrieve the number of cityInfos by city name provided.
	 *
	 * @param name city name
	 * @return Integer
	 */
	Integer getByNamesCnt(@Param("name") String name);

	/**
	 * Retrieve cityInfos.
	 *
	 * @param offset   offset
	 * @param pageSize pageSize
	 * @return List<CityInfo>
	 */
	List<CityView> getCityInfos(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

	/**
	 * Retrieve the number of cityInfos.
	 *
	 * @return Integer
	 */
	Integer getCityInfosCnt();

	/**
	 * Retrieve cityInfos by population ascending.
	 *
	 * @return List<CityInfo>
	 */
	List<CityView> getMinimumRanks();

	/**
	 * Retrieve cityInfos by population descending.
	 *
	 * @return List<CityInfo>
	 */
	List<CityView> getMaximumRanks();

	/**
	 * Retrieve cityInfo by id.
	 *
	 * @param id city id
	 * @return entity of city
	 */
	CityView getCityInfoById(@Param("id") Integer id);
}
