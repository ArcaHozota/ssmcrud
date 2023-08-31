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
	 * Get official language by nation
	 *
	 * @param nation country
	 * @return String
	 */
	String getLanguage(@Param("nation") String nation);
}
