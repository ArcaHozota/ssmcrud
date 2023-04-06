package jp.co.toshiba.ppok.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Mapper of Table WORLD_LANGUAGE
 *
 * @author Administrator
 * @date 2022-12-17
 */
@Mapper
public interface LanguageMapper {

	/**
	 * Get official language by nationCode
	 *
	 * @param code countryCode
	 * @return String
	 */
	String getLanguage(@Param("nationCode") String code);
}
