/**
 *
 */
package jp.co.toshiba.ppok.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.toshiba.ppok.dto.CityDto;

/**
 * ビューWORLD_CITY_VIEWのマッパー
 *
 * @author ArcaHozota
 * @since 8.84
 */
@Mapper
public interface CityDtoMapper {

	/**
	 * 国名によって都市情報を検索する
	 *
	 * @param nation   国名
	 * @param offset   オフセット
	 * @param pageSize ページサイズ
	 * @return List<CityInfo>
	 */
	List<CityDto> getCityInfosByNation(@Param("nation") String nation, @Param("offset") Integer offset,
			@Param("pageSize") Integer pageSize);

	/**
	 * 国名によって都市情報を検索する
	 *
	 * @param nation 国名
	 * @return Integer
	 */
	Integer getCityInfosByNationCnt(@Param("nation") String nation);

	/**
	 * 都市名によって都市情報を検索する
	 *
	 * @param name     都市名
	 * @param offset   オフセット
	 * @param pageSize ページサイズ
	 * @return List<CityInfo>
	 */
	List<CityDto> getCityInfosByName(@Param("name") String name, @Param("offset") Integer offset,
			@Param("pageSize") Integer pageSize);

	/**
	 * 都市名によって都市情報を検索する
	 *
	 * @param name 都市名
	 * @return Integer
	 */
	Integer getCityInfosByNameCnt(@Param("name") String name);

	/**
	 * 都市情報を検索する
	 *
	 * @param offset   オフセット
	 * @param pageSize ページサイズ
	 * @return List<CityInfo>
	 */
	List<CityDto> getCityInfos(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

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
	List<CityDto> getMinimumRanks(@Param("sort") Integer sort);

	/**
	 * 人口数量降順で都市情報を検索する
	 *
	 * @return List<CityInfo>
	 */
	List<CityDto> getMaximumRanks(@Param("sort") Integer sort);

	/**
	 * 都市IDによって都市情報を検索する
	 *
	 * @param id 都市ID
	 * @return CityDto
	 */
	CityDto getCityInfoById(@Param("id") Integer id);
}
