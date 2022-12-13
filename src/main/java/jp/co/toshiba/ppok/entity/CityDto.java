package jp.co.toshiba.ppok.entity;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.io.Serializable;

/**
 * dto of the view of world cities
 *
 * @author Administrator
 */

@Data
public class CityDto implements Serializable {

	/**
	 * This field corresponds to the database column WORLD_CITY_VIEW.ID
	 */
	private Long id;

	/**
	 * This field corresponds to the database column WORLD_CITY_VIEW.NAME
	 */
	@Pattern(regexp = "^[a-zA-Z_-]{4,17}$", message = "Name of cities should be in 4~17 Latin alphabets.")
	private String name;

	/**
	 * This field corresponds to the database column WORLD_CITY_VIEW.CONTINENT
	 */
	private String continent;

	/**
	 * This field corresponds to the database column WORLD_CITY_VIEW.NATION
	 */
	private String nation;

	/**
	 * This field corresponds to the database column WORLD_CITY_VIEW.DISTRICT
	 */
	private String district;

	/**
	 * This field corresponds to the database column WORLD_CITY_VIEW.POPULATION
	 */
	private Long population;
}
