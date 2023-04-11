package jp.co.toshiba.ppok.dto;

import jp.co.toshiba.ppok.entity.CityInfo;

/**
 * DTO class of CityInfo
 *
 * @author Administrator
 */
public class CityDto extends CityInfo {

	private static final long serialVersionUID = 7478219222594404009L;

	/**
	 * This field corresponds to the database column LANGUAGE
	 */
	private String language;

	/**
	 * コンストラクタ
	 */
	public CityDto() {
		super();
	}

	/**
	 * getter for language
	 *
	 * @return language
	 */
	public String getLanguage() {
		return this.language;
	}

	/**
	 * setter of language
	 *
	 * @param language セットする language
	 */
	public void setLanguage(final String language) {
		this.language = language;
	}

}
