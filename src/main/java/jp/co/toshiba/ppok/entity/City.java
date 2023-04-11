package jp.co.toshiba.ppok.entity;

import java.io.Serializable;

/**
 * Entity of Table WORLD_CITY
 *
 * @author Administrator
 */
public class City implements Serializable {

	private static final long serialVersionUID = 1815689293387304425L;

	/**
	 * This field corresponds to the database column ID
	 */
	private Integer id;

	/**
	 * This field corresponds to the database column NAME
	 */
	private String name;

	/**
	 * This field corresponds to the database column COUNTRY_CODE
	 */
	private String countryCode;

	/**
	 * This field corresponds to the database column DISTRICT
	 */
	private String district;

	/**
	 * This field corresponds to the database column POPULATION
	 */
	private Long population;

	/**
	 * This field corresponds to the database column LOGIC_DELETE_FLG
	 */
	private String logicDeleteFlg;

	/**
	 * コンストラクタ
	 */
	public City() {
		super();
	}

	/**
	 * getter for id
	 *
	 * @return id
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * setter of id
	 *
	 * @param id セットする id
	 */
	public void setId(final Integer id) {
		this.id = id;
	}

	/**
	 * getter for name
	 *
	 * @return name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * setter of name
	 *
	 * @param name セットする name
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * getter for countryCode
	 *
	 * @return countryCode
	 */
	public String getCountryCode() {
		return this.countryCode;
	}

	/**
	 * setter of countryCode
	 *
	 * @param countryCode セットする countryCode
	 */
	public void setCountryCode(final String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * getter for district
	 *
	 * @return district
	 */
	public String getDistrict() {
		return this.district;
	}

	/**
	 * setter of district
	 *
	 * @param district セットする district
	 */
	public void setDistrict(final String district) {
		this.district = district;
	}

	/**
	 * getter for population
	 *
	 * @return population
	 */
	public Long getPopulation() {
		return this.population;
	}

	/**
	 * setter of population
	 *
	 * @param population セットする population
	 */
	public void setPopulation(final Long population) {
		this.population = population;
	}

	/**
	 * getter for logicDeleteFlg
	 *
	 * @return logicDeleteFlg
	 */
	public String getLogicDeleteFlg() {
		return this.logicDeleteFlg;
	}

	/**
	 * setter of logicDeleteFlg
	 *
	 * @param logicDeleteFlg セットする logicDeleteFlg
	 */
	public void setLogicDeleteFlg(final String logicDeleteFlg) {
		this.logicDeleteFlg = logicDeleteFlg;
	}

	/**
	 * toString
	 */
	@Override
	public String toString() {
		return "City [id=" + this.id + ", name=" + this.name + ", countryCode=" + this.countryCode + ", district="
				+ this.district + ", population=" + this.population + ", logicDeleteFlg=" + this.logicDeleteFlg + "]";
	}
}
