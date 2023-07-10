package jp.co.toshiba.ppok.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Entity of Table WORLD_LANGUAGE
 *
 * @author Administrator
 */
public class Language implements Serializable {

	private static final long serialVersionUID = 487191389336636732L;

	/**
	 * This field corresponds to the database column COUNTRY_CODE
	 */
	private String countryCode;

	/**
	 * This field corresponds to the database column LANGUAGE
	 */
	private String name;

	/**
	 * This field corresponds to the database column IS_OFFICIAL
	 */
	private String isOfficial;

	/**
	 * This field corresponds to the database column PERCENTAGE
	 */
	private BigDecimal percentage;

	/**
	 * This field corresponds to the database column LOGIC_DELETE_FLG
	 */
	private String logicDeleteFlg;

	/**
	 * コンストラクタ
	 */
	public Language() {
		super();
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
	 * getter for isOfficial
	 *
	 * @return isOfficial
	 */
	public String getIsOfficial() {
		return this.isOfficial;
	}

	/**
	 * setter of isOfficial
	 *
	 * @param isOfficial セットする isOfficial
	 */
	public void setIsOfficial(final String isOfficial) {
		this.isOfficial = isOfficial;
	}

	/**
	 * getter for percentage
	 *
	 * @return percentage
	 */
	public BigDecimal getPercentage() {
		return this.percentage;
	}

	/**
	 * setter of percentage
	 *
	 * @param percentage セットする percentage
	 */
	public void setPercentage(final BigDecimal percentage) {
		this.percentage = percentage;
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

	@Override
	public String toString() {
		return "Language [countryCode=" + this.countryCode + ", name=" + this.name + ", isOfficial=" + this.isOfficial
				+ ", percentage=" + this.percentage + ", logicDeleteFlg=" + this.logicDeleteFlg + "]";
	}
}
