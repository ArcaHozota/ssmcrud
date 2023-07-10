/**
 *
 */
package jp.co.toshiba.ppok.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.toshiba.ppok.entity.CityView;

/**
 * ビューWORLD_CITY_VIEWのマッパー
 *
 * @author Administrator
 */
@Mapper
public interface CityViewMapper {

	/**
	 * 国名によって都市情報を検索する
	 *
	 * @param nation   国名
	 * @param offset   オフセット
	 * @param pageSize ページサイズ
	 * @return List<CityInfo>
	 */
	List<CityView> getByNations(@Param("nation") String nation, @Param("offset") Integer offset,
			@Param("pageSize") Integer pageSize);

	/**
	 * 国名によって都市情報を検索する
	 *
	 * @param nation 国名
	 * @return Integer
	 */
	Integer getByNationsCnt(@Param("nation") String nation);

	/**
	 * 都市名によって都市情報を検索する
	 *
	 * @param name     都市名
	 * @param offset   オフセット
	 * @param pageSize ページサイズ
	 * @return List<CityInfo>
	 */
	List<CityView> getByNames(@Param("name") String name, @Param("offset") Integer offset,
			@Param("pageSize") Integer pageSize);

	/**
	 * 都市名によって都市情報を検索する
	 *
	 * @param name 都市名
	 * @return Integer
	 */
	Integer getByNamesCnt(@Param("name") String name);

	/**
	 * 都市情報を検索する
	 *
	 * @param offset   オフセット
	 * @param pageSize ページサイズ
	 * @return List<CityInfo>
	 */
	List<CityView> getCityInfos(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

	/**
	 * 都市情報を検索する
	 *
	 * @return Integer
	 */
	Integer getCityInfosCnt();

	/**
	 * 人口数量昇順で都市情報を検索する
	 *
	 * @return List<CityInfo>
	 */
	List<CityView> getMinimumRanks();

	/**
	 * 人口数量降順で都市情報を検索する
	 *
	 * @return List<CityInfo>
	 */
	List<CityView> getMaximumRanks();

	/**
	 * 都市IDによって都市情報を検索する
	 *
	 * @param id 都市ID
	 * @return CityView
	 */
	CityView getCityInfoById(@Param("id") Integer id);
}
