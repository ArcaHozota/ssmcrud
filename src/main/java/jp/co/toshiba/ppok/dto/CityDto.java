package jp.co.toshiba.ppok.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * Entity of View WORLD_CITY_VIEW
 *
 * @author Administrator
 */
@Data
public class CityDto implements Serializable {

	private static final long serialVersionUID = -1945579742723628429L;

	/**
	 * This field corresponds to the database column ID
	 */
	private Integer id;

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
