package jp.co.toshiba.ppok.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * 都市情報DTO
 *
 * @author Administrator
 */
@Data
public final class CityDto implements Serializable {

	private static final long serialVersionUID = -4246591226136439978L;

	/**
	 * This field corresponds to the database column ID
	 */
	private Long id;

	/**
	 * This field corresponds to the database column NAME
	 */
	private String name;

	/**
	 * This field corresponds to the database column CONTINENT
	 */
	private String continent;

	/**
	 * This field corresponds to the database column NATION
	 */
	private String nation;

	/**
	 * This field corresponds to the database column DISTRICT
	 */
	private String district;

	/**
	 * This field corresponds to the database column POPULATION
	 */
	private Long population;

	/**
	 * This field corresponds to the database column LANGUAGE
	 */
	private String language;
}
