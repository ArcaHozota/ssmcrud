package jp.co.sony.ppog.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "WORLD_CITY")
@NamedQuery(name = "City.removeById", query = "update City c set c.isDeleted = 1 where c.id =:id")
public class City implements Serializable {

	private static final long serialVersionUID = 1815689293387304425L;

	/**
	 * This field corresponds to the database column id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * This field corresponds to the database column name
	 */
	@Column(nullable = false)
	private String name;

	/**
	 * This field corresponds to the database column country_code
	 */
	@Column(name = "COUNTRY_CODE", nullable = false)
	private String countryCode;

	/**
	 * This field corresponds to the database column district
	 */
	@Column(nullable = false)
	private String district;

	/**
	 * This field corresponds to the database column population
	 */
	@Column(nullable = false)
	private Long population;

	/**
	 * This field corresponds to the database column is_deleted
	 */
	@Column(name = "IS_DELETED", nullable = false)
	private Integer isDeleted;

	@Override
	public String toString() {
		return "City [id=" + this.id + ", name=" + this.name + ", countryCode=" + this.countryCode + ", district="
				+ this.district + ", population=" + this.population + ", isDeleted=" + this.isDeleted + "]";
	}
}