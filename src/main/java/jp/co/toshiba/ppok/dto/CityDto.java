package jp.co.toshiba.ppok.dto;

import java.io.Serializable;

/**
 * Entity of View WORLD_CITY_VIEW
 *
 * @author Administrator
 */
public class CityDto implements Serializable {

	private static final long serialVersionUID = -1945579742723628429L;

	/**
	 * This field corresponds to the database column ID
	 */
	private Integer id;

	/**
	 * This field corresponds to the database column NAME
	 */
	private String name;

	/**
	 * This field corresponds to the database column CONTINENT
	 */
	private String continent;

	/**
	 * This field corresponds to the database column NATION
	 */
	private String nation;

	/**
	 * This field corresponds to the database column DISTRICT
	 */
	private String district;

	/**
	 * This field corresponds to the database column POPULATION
	 */
	private Long population;

	/**
	 * This field corresponds to the database column LANGUAGE
	 */
	private String language;

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
	 * getter for nation
	 *
	 * @return nation
	 */
	public String getNation() {
		return this.nation;
	}

	/**
	 * setter of nation
	 *
	 * @param nation セットする nation
	 */
	public void setNation(final String nation) {
		this.nation = nation;
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

	/**
	 * toString
	 */
	@Override
	public String toString() {
		return "CityDto [id=" + this.id + ", name=" + this.name + ", continent=" + this.continent + ", nation="
				+ this.nation + ", district=" + this.district + ", population=" + this.population + ", language="
				+ this.language + "]";
	}
}
