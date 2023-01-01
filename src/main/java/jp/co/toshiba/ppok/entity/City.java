package jp.co.toshiba.ppok.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Administrator
 */
@Getter
@Setter
@Entity
@Proxy(lazy = false)
@Table(name = "world_city")
public class City implements Serializable {

	private static final long serialVersionUID = 1815689293387304425L;

	/**
	 * This field corresponds to the database column WORLD_CITY.ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

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

	public City() {
	}

	@Override
	public String toString() {
		return "City{" +
				"id=" + id +
				", name='" + name + '\'' +
				", countryCode='" + countryCode + '\'' +
				", district='" + district + '\'' +
				", population=" + population +
				", isDeleted=" + isDeleted +
				'}';
	}
}