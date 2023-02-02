package jp.co.toshiba.ppok.entity;

import java.io.Serializable;
import java.math.BigDecimal;

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
@Table(name = "country")
@Proxy(lazy = false)
@NamedQuery(name = "Country.findNationCode", query = "select distinct n from Country n where n.name = :name")
@NamedQuery(name = "Country.findNationsByCnt", query = "select distinct n from Country n where n.continent = :continent order by n.name asc")
public class Country implements Serializable {

	private static final long serialVersionUID = -437505450837045511L;

	/**
	 * This field corresponds to the database column code
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String code;

	/**
	 * This field corresponds to the database column name
	 */
	@Column(nullable = false)
	private String name;

	/**
	 * This field corresponds to the database column continent
	 */
	@Column(nullable = false)
	private String continent;

	/**
	 * This field corresponds to the database column region
	 */
	@Column(nullable = false)
	private String region;

	/**
	 * This field corresponds to the database column surface_area
	 */
	@Column(name = "surface_area", nullable = false)
	private BigDecimal surfaceArea;

	/**
	 * This field corresponds to the database column independence_year
	 */
	@Column(name = "independence_year")
	private Integer independenceYear;

	/**
	 * This field corresponds to the database column population
	 */
	@Column(nullable = false)
	private Long population;

	/**
	 * This field corresponds to the database column life_expectancy
	 */
	@Column(name = "life_expectancy")
	private Integer lifeExpectancy;

	/**
	 * This field corresponds to the database column gnp
	 */
	private BigDecimal gnp;

	/**
	 * This field corresponds to the database column gnp_old
	 */
	@Column(name = "gnp_old")
	private BigDecimal gnpOld;

	/**
	 * This field corresponds to the database column local_name
	 */
	@Column(name = "local_name", nullable = false)
	private String localName;

	/**
	 * This field corresponds to the database column government_form
	 */
	@Column(name = "government_form", nullable = false)
	private String governmentForm;

	/**
	 * This field corresponds to the database column head_of_state
	 */
	@Column(name = "head_of_state")
	private String headOfState;

	/**
	 * This field corresponds to the database column capital
	 */
	private Integer capital;

	/**
	 * This field corresponds to the database column code2
	 */
	@Column(nullable = false)
	private String code2;

	/**
	 * This field corresponds to the database column is_deleted
	 */
	@Column(name = "is_deleted", nullable = false)
	private Integer isDeleted;

	@Override
	public String toString() {
		return "Country [code=" + this.code + ", name=" + this.name + ", continent=" + this.continent + ", region="
				+ this.region + ", surfaceArea=" + this.surfaceArea + ", independenceYear=" + this.independenceYear
				+ ", population=" + this.population + ", lifeExpectancy=" + this.lifeExpectancy + ", gnp=" + this.gnp
				+ ", gnpOld=" + this.gnpOld + ", localName=" + this.localName + ", governmentForm="
				+ this.governmentForm + ", headOfState=" + this.headOfState + ", capital=" + this.capital + ", code2="
				+ this.code2 + ", isDeleted=" + this.isDeleted + "]";
	}
}
