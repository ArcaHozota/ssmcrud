package jp.co.toshiba.ppok.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

/**
 * Entity of Table WORLD_LANGUAGE
 *
 * @author Administrator
 */
@Data
public final class Language implements Serializable {

	private static final long serialVersionUID = 487191389336636732L;

	/**
	 * This field corresponds to the database column COUNTRY_CODE
	 */
	private String countryCode;

	/**
	 * This field corresponds to the database column LANGUAGE
	 */
	private String name;

	/**
	 * This field corresponds to the database column IS_OFFICIAL
	 */
	private String isOfficial;

	/**
	 * This field corresponds to the database column PERCENTAGE
	 */
	private BigDecimal percentage;

	/**
	 * This field corresponds to the database column LOGIC_DELETE_FLG
	 */
	private String deleteFlg;
}
