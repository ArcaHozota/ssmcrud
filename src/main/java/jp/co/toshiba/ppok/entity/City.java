package jp.co.toshiba.ppok.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * @author Administrator
 */
@Data
public class City implements Serializable {

	private static final long serialVersionUID = 1815689293387304425L;

	/**
	 * This field corresponds to the database column id
	 */
	private Integer id;

	/**
	 * This field corresponds to the database column name
	 */
	private String name;

	/**
	 * This field corresponds to the database column country_code
	 */
	private String countryCode;

	/**
	 * This field corresponds to the database column district
	 */
	private String district;

	/**
	 * This field corresponds to the database column population
	 */
	private Long population;

	/**
	 * This field corresponds to the database column is_deleted
	 */
	private Integer isDeleted;
}
