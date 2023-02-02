package jp.co.toshiba.ppok.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

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
@Table(name = "city")
@NamedQuery(name = "City.removeById", query = "update City c set c.isDeleted = 1 where c.id = :id")
@NamedQuery(name = "City.getCityInfos", query = "select c from City c where c.isDeleted = 0 order by c.id asc")
@NamedQuery(name = "City.findByNations", query = "select c from City c where c.countryCode = :nation")
@NamedQuery(name = "City.getByNations", query = "select c from City c where c.countryCode = :nation order by c.id asc")
@NamedQuery(name = "City.getByNames", query = "select c from City c where c.name like concat('%', :name, '%') order by c.id asc")
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
	@Column(name = "country_code", nullable = false)
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
	@Column(name = "is_deleted", nullable = false)
	private Integer isDeleted;

	@Override
	public String toString() {
		return "City [id=" + this.id + ", name=" + this.name + ", countryCode=" + this.countryCode + ", district="
				+ this.district + ", population=" + this.population + ", isDeleted=" + this.isDeleted + "]";
	}
}
