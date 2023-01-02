package jp.co.toshiba.ppok.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.Proxy;

import lombok.Getter;
import lombok.Setter;

/**
 * dto of the view of world cities
 *
 * @author Administrator
 */

@Getter
@Setter
@Entity
@Proxy(lazy = false)
@Table(name = "city_view")
public class CityInfo implements Serializable {

	private static final long serialVersionUID = -863534569423043863L;

	/**
	 * This field corresponds to the database column WORLD_CITY_VIEW.ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * This field corresponds to the database column WORLD_CITY_VIEW.NAME
	 */
	@Column(nullable = false)
	@Pattern(regexp = "^[a-zA-Z-\\p{IsWhiteSpace}]{4,17}$", message = "Name of cities should be in 4~17 Latin alphabets.")
	private String name;

	/**
	 * This field corresponds to the database column WORLD_CITY_VIEW.CONTINENT
	 */
	@Column(nullable = false)
	private String continent;

	/**
	 * This field corresponds to the database column WORLD_CITY_VIEW.NATION
	 */
	@Column(nullable = false)
	private String nation;

	/**
	 * This field corresponds to the database column WORLD_CITY_VIEW.DISTRICT
	 */
	@Column(nullable = false)
	private String district;

	/**
	 * This field corresponds to the database column WORLD_CITY_VIEW.POPULATION
	 */
	@Column(nullable = false)
	private Long population;

	public CityInfo() {
	}

	public CityInfo(final Integer id, final String name, final String continent, final String nation,
			final String district, final Long population) {
		this.id = id;
		this.name = name;
		this.continent = continent;
		this.nation = nation;
		this.district = district;
		this.population = population;
	}

	@Override
	public String toString() {
		return "CityInfo{" + "id=" + this.id + ", name='" + this.name + '\'' + ", continent='" + this.continent + '\''
				+ ", nation='" + this.nation + '\'' + ", district='" + this.district + '\'' + ", population="
				+ this.population + '}';
	}
}
