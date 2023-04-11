package jp.co.toshiba.ppok.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Entity of Table WORLD_COUNTRY
 *
 * @author Administrator
 */
public class Country implements Serializable {

	private static final long serialVersionUID = -437505450837045511L;

	/**
	 * This field corresponds to the database column CODE
	 */
	private String code;

	/**
	 * This field corresponds to the database column NAME
	 */
	private String name;

	/**
	 * This field corresponds to the database column CONTINENT
	 */
	private String continent;

	/**
	 * This field corresponds to the database column REGION
	 */
	private String region;

	/**
	 * This field corresponds to the database column SURFACE_AREA
	 */
	private BigDecimal surfaceArea;

	/**
	 * This field corresponds to the database column INDEPENDENCE_YEAR
	 */
	private Integer independenceYear;

	/**
	 * This field corresponds to the database column POPULATION
	 */
	private Long population;

	/**
	 * This field corresponds to the database column LIFE_EXPECTANCY
	 */
	private Integer lifeExpectancy;

	/**
	 * This field corresponds to the database column GNP
	 */
	private BigDecimal gnp;

	/**
	 * This field corresponds to the database column GNP_OLD
	 */
	private BigDecimal gnpOld;

	/**
	 * This field corresponds to the database column LOCAL_NAME
	 */
	private String localName;

	/**
	 * This field corresponds to the database column GOVERNMENT_FORM
	 */
	private String governmentForm;

	/**
	 * This field corresponds to the database column HEAD_OF_STATE
	 */
	private String headOfState;

	/**
	 * This field corresponds to the database column CAPITAL
	 */
	private Integer capital;

	/**
	 * This field corresponds to the database column CODE2
	 */
	private String code2;

	/**
	 * This field corresponds to the database column LOGIC_DELETE_FLG
	 */
	private String logicDeleteFlg;

	/**
	 * コンストラクタ
	 */
	public Country() {
		super();
	}

	/**
	 * getter for code
	 *
	 * @return code
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * setter of code
	 *
	 * @param code セットする code
	 */
	public void setCode(final String code) {
		this.code = code;
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
	 * getter for continent
	 *
	 * @return continent
	 */
	public String getContinent() {
		return this.continent;
	}

	/**
	 * setter of continent
	 *
	 * @param continent セットする continent
	 */
	public void setContinent(final String continent) {
		this.continent = continent;
	}

	/**
	 * getter for region
	 *
	 * @return region
	 */
	public String getRegion() {
		return this.region;
	}

	/**
	 * setter of region
	 *
	 * @param region セットする region
	 */
	public void setRegion(final String region) {
		this.region = region;
	}

	/**
	 * getter for surfaceArea
	 *
	 * @return surfaceArea
	 */
	public BigDecimal getSurfaceArea() {
		return this.surfaceArea;
	}

	/**
	 * setter of surfaceArea
	 *
	 * @param surfaceArea セットする surfaceArea
	 */
	public void setSurfaceArea(final BigDecimal surfaceArea) {
		this.surfaceArea = surfaceArea;
	}

	/**
	 * getter for independenceYear
	 *
	 * @return independenceYear
	 */
	public Integer getIndependenceYear() {
		return this.independenceYear;
	}

	/**
	 * setter of independenceYear
	 *
	 * @param independenceYear セットする independenceYear
	 */
	public void setIndependenceYear(final Integer independenceYear) {
		this.independenceYear = independenceYear;
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
	 * getter for lifeExpectancy
	 *
	 * @return lifeExpectancy
	 */
	public Integer getLifeExpectancy() {
		return this.lifeExpectancy;
	}

	/**
	 * setter of lifeExpectancy
	 *
	 * @param lifeExpectancy セットする lifeExpectancy
	 */
	public void setLifeExpectancy(final Integer lifeExpectancy) {
		this.lifeExpectancy = lifeExpectancy;
	}

	/**
	 * getter for gnp
	 *
	 * @return gnp
	 */
	public BigDecimal getGnp() {
		return this.gnp;
	}

	/**
	 * setter of gnp
	 *
	 * @param gnp セットする gnp
	 */
	public void setGnp(final BigDecimal gnp) {
		this.gnp = gnp;
	}

	/**
	 * getter for gnpOld
	 *
	 * @return gnpOld
	 */
	public BigDecimal getGnpOld() {
		return this.gnpOld;
	}

	/**
	 * setter of gnpOld
	 *
	 * @param gnpOld セットする gnpOld
	 */
	public void setGnpOld(final BigDecimal gnpOld) {
		this.gnpOld = gnpOld;
	}

	/**
	 * getter for localName
	 *
	 * @return localName
	 */
	public String getLocalName() {
		return this.localName;
	}

	/**
	 * setter of localName
	 *
	 * @param localName セットする localName
	 */
	public void setLocalName(final String localName) {
		this.localName = localName;
	}

	/**
	 * getter for governmentForm
	 *
	 * @return governmentForm
	 */
	public String getGovernmentForm() {
		return this.governmentForm;
	}

	/**
	 * setter of governmentForm
	 *
	 * @param governmentForm セットする governmentForm
	 */
	public void setGovernmentForm(final String governmentForm) {
		this.governmentForm = governmentForm;
	}

	/**
	 * getter for headOfState
	 *
	 * @return headOfState
	 */
	public String getHeadOfState() {
		return this.headOfState;
	}

	/**
	 * setter of headOfState
	 *
	 * @param headOfState セットする headOfState
	 */
	public void setHeadOfState(final String headOfState) {
		this.headOfState = headOfState;
	}

	/**
	 * getter for capital
	 *
	 * @return capital
	 */
	public Integer getCapital() {
		return this.capital;
	}

	/**
	 * setter of capital
	 *
	 * @param capital セットする capital
	 */
	public void setCapital(final Integer capital) {
		this.capital = capital;
	}

	/**
	 * getter for code2
	 *
	 * @return code2
	 */
	public String getCode2() {
		return this.code2;
	}

	/**
	 * setter of code2
	 *
	 * @param code2 セットする code2
	 */
	public void setCode2(final String code2) {
		this.code2 = code2;
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
		return "Country [code=" + this.code + ", name=" + this.name + ", continent=" + this.continent + ", region="
				+ this.region + ", surfaceArea=" + this.surfaceArea + ", independenceYear=" + this.independenceYear
				+ ", population=" + this.population + ", lifeExpectancy=" + this.lifeExpectancy + ", gnp=" + this.gnp
				+ ", gnpOld=" + this.gnpOld + ", localName=" + this.localName + ", governmentForm="
				+ this.governmentForm + ", headOfState=" + this.headOfState + ", capital=" + this.capital + ", code2="
				+ this.code2 + ", logicDeleteFlg=" + this.logicDeleteFlg + "]";
	}
}
