package jp.co.toshiba.ppok.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Administrator
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Proxy(lazy = false)
@Table(name = "city")
@NamedQueries({ @NamedQuery(name = "City.removeById", query = "update City c set c.isDeleted = 1 where c.id = :id") })
public class City implements Serializable {

	private static final long serialVersionUID = 1815689293387304425L;

	/**
	 * This field corresponds to the database column WORLD_CITY.ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * This field corresponds to the database column WORLD_CITY.NAME
	 */
	@Column(nullable = false)
	private String name;

	/**
	 * This field corresponds to the database column WORLD_CITY.COUNTRY_CODE
	 */
	@Column(name = "country_code", nullable = false)
	private String countryCode;

	/**
	 * This field corresponds to the database column WORLD_CITY.DISTRICT
	 */
	@Column(nullable = false)
	private String district;

	/**
	 * This field corresponds to the database column WORLD_CITY.POPULATION
	 */
	@Column(nullable = false)
	private Long population;

	/**
	 * This field corresponds to the database column WORLD_CITY.IS_DELETED
	 */
	@Column(name = "is_deleted", nullable = false)
	private Integer isDeleted;

	@Override
	public String toString() {
		return "City{" + "id=" + this.id + ", name='" + this.name + '\'' + ", countryCode='" + this.countryCode + '\''
				+ ", district='" + this.district + '\'' + ", population=" + this.population + ", isDeleted="
				+ this.isDeleted + '}';
	}
}
