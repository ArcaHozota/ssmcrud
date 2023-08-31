package jp.co.toshiba.ppok.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * テーブルWORLD_LANGUAGEのマッパー
 *
 * @author Administrator
 * @since 2022-12-17
 */
@Mapper
public interface LanguageMapper {

	/**
	 * 公用語を取得する
	 *
	 * @param nation 国名
	 * @return String
	 */
	String getLanguage(@Param("nation") String nation);
}
