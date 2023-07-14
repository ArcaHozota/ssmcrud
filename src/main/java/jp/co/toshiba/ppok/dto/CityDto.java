package jp.co.toshiba.ppok.dto;

import jp.co.toshiba.ppok.entity.CityView;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO class of CityInfo
 *
 * @author Administrator
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CityDto extends CityView {

	private static final long serialVersionUID = 7478219222594404009L;

	/**
	 * This field corresponds to the database column LANGUAGE
	 */
	private String language;
}
