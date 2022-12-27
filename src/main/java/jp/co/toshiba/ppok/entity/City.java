package jp.co.toshiba.ppok.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Administrator
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || Hibernate.getClass(this) != Hibernate.getClass(obj)) {
			return false;
		}
		City city = (City) obj;
		return id != null && Objects.equals(id, city.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}