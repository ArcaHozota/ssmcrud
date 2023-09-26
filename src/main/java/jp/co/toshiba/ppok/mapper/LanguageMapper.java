package jp.co.toshiba.ppok.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * テーブルWORLD_LANGUAGEのマッパー
 *
 * @author ArcaHozota
 * @since 8.52
 */
@Mapper
public interface LanguageMapper {

	/**
	 * 公用語を取得する
	 *
	 * @param nation 国名
	 * @return String
	 */
	String getLanguageByNationName(@Param("nation") String nation);
}
