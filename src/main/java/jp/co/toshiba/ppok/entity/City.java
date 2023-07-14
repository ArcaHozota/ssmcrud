package jp.co.toshiba.ppok.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Entity of Table WORLD_CITY
 *
 * @author Administrator
 */
@Data
public class City implements Serializable {

	private static final long serialVersionUID = 1815689293387304425L;

	/**
	 * This field corresponds to the database column ID
	 */
	private Long id;

	/**
	 * This field corresponds to the database column NAME
	 */
	private String name;

	/**
	 * This field corresponds to the database column COUNTRY_CODE
	 */
	private String countryCode;

	/**
	 * This field corresponds to the database column DISTRICT
	 */
	private String district;

	/**
	 * This field corresponds to the database column POPULATION
	 */
	private Long population;

	/**
	 * This field corresponds to the database column LOGIC_DELETE_FLG
	 */
	private String logicDeleteFlg;
}
