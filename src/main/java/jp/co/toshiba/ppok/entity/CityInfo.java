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
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.Proxy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * dto of the view of world cities
 *
 * @author Administrator
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Proxy(lazy = false)
@Table(name = "city_view")
@NamedQueries({
		@NamedQuery(name = "CityInfo.findByNations", query = "select c from CityInfo c where c.nation = :nation"),
		@NamedQuery(name = "CityInfo.getByNations", query = "select c from CityInfo c where c.nation = :nation order by c.id asc"),
		@NamedQuery(name = "CityInfo.getByNames", query = "select c from CityInfo c where c.name like concat('%', :name, '%') order by c.id asc") })
public class CityInfo implements Serializable {

	private static final long serialVersionUID = -863534569423043863L;

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
	@Pattern(regexp = "^[a-zA-Z-\\p{IsWhiteSpace}]{4,17}$", message = "Name of cities should be in 4~17 Latin alphabets.")
	private String name;

	/**
	 * This field corresponds to the database column continent
	 */
	@Column(nullable = false)
	private String continent;

	/**
	 * This field corresponds to the database column nation
	 */
	@Column(nullable = false)
	private String nation;

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

	@Override
	public String toString() {
		return "CityInfo [id=" + this.id + ", name=" + this.name + ", continent=" + this.continent + ", nation="
				+ this.nation + ", district=" + this.district + ", population=" + this.population + "]";
	}
}
