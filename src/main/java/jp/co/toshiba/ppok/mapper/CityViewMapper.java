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
 * @author ArcaHozota
 * @since 8.84
 */
@Mapper
public interface CityViewMapper {

	/**
	 * すべての大陸名称を取得する
	 *
	 * @return List<String>
	 */
	List<String> getAllContinents();

	/**
	 * 都市IDによって都市情報を検索する
	 *
	 * @param id 都市ID
	 * @return CityView
	 */
	CityView getCityInfoById(@Param("id") Integer id);

	/**
	 * 都市情報を検索する
	 *
	 * @param offset   オフセット
	 * @param pageSize ページサイズ
	 * @return List<CityInfo>
	 */
	List<CityView> getCityInfos(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

	/**
	 * 都市名によって都市情報を検索する
	 *
	 * @param name     都市名
	 * @param offset   オフセット
	 * @param pageSize ページサイズ
	 * @return List<CityInfo>
	 */
	List<CityView> getCityInfosByName(@Param("name") String name, @Param("offset") Integer offset,
			@Param("pageSize") Integer pageSize);

	/**
	 * 都市名によって都市情報を検索する
	 *
	 * @param name 都市名
	 * @return Integer
	 */
	Integer getCityInfosByNameCnt(@Param("name") String name);

	/**
	 * 国名によって都市情報を検索する
	 *
	 * @param nation   国名
	 * @param offset   オフセット
	 * @param pageSize ページサイズ
	 * @return List<CityInfo>
	 */
	List<CityView> getCityInfosByNation(@Param("nation") String nation, @Param("offset") Integer offset,
			@Param("pageSize") Integer pageSize);

	/**
	 * 国名によって都市情報を検索する
	 *
	 * @param nation 国名
	 * @return Integer
	 */
	Integer getCityInfosByNationCnt(@Param("nation") String nation);

	/**
	 * 都市情報を検索する
	 *
	 * @return Integer
	 */
	Integer getCityInfosCnt();

	/**
	 * 国名によって公用語を取得する
	 *
	 * @param nation 国名
	 * @return String
	 */
	String getLanguageByNation(String nation);

	/**
	 * 人口数量降順で都市情報を検索する
	 *
	 * @return List<CityInfo>
	 */
	List<CityView> getMaximumRanks(@Param("sort") Integer sort);

	/**
	 * 人口数量昇順で都市情報を検索する
	 *
	 * @return List<CityInfo>
	 */
	List<CityView> getMinimumRanks(@Param("sort") Integer sort);

	/**
	 * 国名によって国家コードを取得する
	 *
	 * @param nation 国名
	 * @return String
	 */
	String getNationCode(@Param("nation") String nation);

	/**
	 * 大陸名称によってすべての国名を取得する
	 *
	 * @param continent 大陸名称
	 * @return List<String>
	 */
	List<String> getNationsByCnt(@Param("continent") String continent);
}
