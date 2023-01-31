package jp.co.toshiba.ppok.dto;

import jp.co.toshiba.ppok.entity.City;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CityDto extends City {

	/**
	 * This field corresponds to the database column country.name
	 */
	private String nation;

	/**
	 * This field corresponds to the database column country.continent
	 */
	private String continent;
}
