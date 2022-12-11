package jp.co.toshiba.ppok.utils;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * the dto of view wvc
 *
 * @author Administrator
 */

@Data
public class CityDto {

	private Long id;

	@Pattern(regexp = "^[a-zA-Z_-]{4,17}$", message = "Name of cities should be in 4~17 Latin alphabets.")
	private String name;

	private String continent;

	private String nation;

	private String district;

	private Long population;
}
