package jp.co.toshiba.ppok.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CityDto {

	private static final long serialVersionUID = 6268974816656613539L;

	/**
	 * This field corresponds to the database column id
	 */
	private Integer id;

	/**
	 * This field corresponds to the database column name
	 */
	private String name;

	/**
	 * This field corresponds to the database column country.name
	 */
	private String nation;

	/**
	 * This field corresponds to the database column country.continent
	 */
	private String continent;

	/**
	 * This field corresponds to the database column district
	 */
	private String district;

	/**
	 * This field corresponds to the database column population
	 */
	private Long population;

}
