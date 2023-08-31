package jp.co.toshiba.ppok.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * テーブルWORLD_COUNTRYのマッパー
 *
 * @author Administrator
 * @since 2022-12-16
 */
@Mapper
public interface CountryMapper {

	/**
	 * すべての大陸名称を取得する
	 *
	 * @return List<CityInfo>
	 */
	List<String> getAllContinents();

	/**
	 * 選択された大陸のすべての国を取得する
	 *
	 * @param continent 大陸名
	 * @return List<CityInfo>
	 */
	List<String> getNationsByCnt(@Param("continent") String continent);

	/**
	 * 国名によって国家コードを取得する
	 *
	 * @param nation 国名
	 * @return List<CityInfo>
	 */
	String getNationCode(@Param("name") String nation);
}
