package jp.co.toshiba.ppok.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * Entity of View WORLD_CITY_VIEW
 *
 * @author ArcaHozota
 * @since 4.14
 */
@Data
public final class CityView implements Serializable {

	private static final long serialVersionUID = 3899140616215776494L;

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
