package jp.co.toshiba.ppok.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

/**
 * @author Administrator
 */
@Data
public class Country implements Serializable {

	private static final long serialVersionUID = -437505450837045511L;

	/**
	 * This field corresponds to the database column code
	 */
	private String code;

	/**
	 * This field corresponds to the database column name
	 */
	private String name;

	/**
	 * This field corresponds to the database column continent
	 */
	private String continent;

	/**
	 * This field corresponds to the database column region
	 */
	private String region;

	/**
	 * This field corresponds to the database column surface_area
	 */
	private BigDecimal surfaceArea;

	/**
	 * This field corresponds to the database column independence_year
	 */
	private Integer independenceYear;

	/**
	 * This field corresponds to the database column population
	 */
	private Long population;

	/**
	 * This field corresponds to the database column life_expectancy
	 */
	private Integer lifeExpectancy;

	/**
	 * This field corresponds to the database column gnp
	 */
	private BigDecimal gnp;

	/**
	 * This field corresponds to the database column gnp_old
	 */
	private BigDecimal gnpOld;

	/**
	 * This field corresponds to the database column local_name
	 */
	private String localName;

	/**
	 * This field corresponds to the database column government_form
	 */
	private String governmentForm;

	/**
	 * This field corresponds to the database column head_of_state
	 */
	private String headOfState;

	/**
	 * This field corresponds to the database column capital
	 */
	private Integer capital;

	/**
	 * This field corresponds to the database column code2
	 */
	private String code2;

	/**
	 * This field corresponds to the database column is_deleted
	 */
	private Integer isDeleted;
}
