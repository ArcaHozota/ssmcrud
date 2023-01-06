package jp.co.toshiba.ppok.entity;

import java.io.Serializable;
import java.math.BigDecimal;

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
import lombok.Setter;

/**
 * @author Administrator
 */
@Getter
@Setter
@Entity
@Proxy(lazy = false)
@Table(name = "country")
@NamedQueries({
		@NamedQuery(name = "Nation.getNationCode", query = "select distinct n from Nation n where n.name = :name")
})
public class Nation implements Serializable {

	private static final long serialVersionUID = -437505450837045511L;

	/**
	 * This field corresponds to the database column WORLD_COUNTRY.CODE
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String code;

	/**
	 * This field corresponds to the database column WORLD_COUNTRY.NAME
	 */
	@Column(nullable = false)
	private String name;

	/**
	 * This field corresponds to the database column WORLD_COUNTRY.CONTINENT
	 */
	@Column(nullable = false)
	private String continent;

	/**
	 * This field corresponds to the database column WORLD_COUNTRY.REGION
	 */
	@Column(nullable = false)
	private String region;

	/**
	 * This field corresponds to the database column WORLD_COUNTRY.SURFACE_AREA
	 */
	@Column(name = "surface_area", nullable = false)
	private BigDecimal surfaceArea;

	/**
	 * This field corresponds to the database column WORLD_COUNTRY.INDEPENDENCE_YEAR
	 */
	@Column(name = "independence_year")
	private Integer independenceYear;

	/**
	 * This field corresponds to the database column WORLD_COUNTRY.POPULATION
	 */
	@Column(nullable = false)
	private Long population;

	/**
	 * This field corresponds to the database column WORLD_COUNTRY.LIFE_EXPECTANCY
	 */
	@Column(name = "life_expectancy")
	private Integer lifeExpectancy;

	/**
	 * This field corresponds to the database column WORLD_COUNTRY.GNP
	 */
	private BigDecimal gnp;

	/**
	 * This field corresponds to the database column WORLD_COUNTRY.GNP_OLD
	 */
	@Column(name = "gnp_old")
	private BigDecimal gnpOld;

	/**
	 * This field corresponds to the database column WORLD_COUNTRY.LOCAL_NAME
	 */
	@Column(name = "local_name", nullable = false)
	private String localName;

	/**
	 * This field corresponds to the database column WORLD_COUNTRY.GOVERNMENT_FORM
	 */
	@Column(name = "government_form", nullable = false)
	private String governmentForm;

	/**
	 * This field corresponds to the database column WORLD_COUNTRY.HEAD_OF_STATE
	 */
	@Column(name = "head_of_state")
	private String headOfState;

	/**
	 * This field corresponds to the database column WORLD_COUNTRY.CAPITAL
	 */
	private Integer capital;

	/**
	 * This field corresponds to the database column WORLD_COUNTRY.CODE2
	 */
	@Column(nullable = false)
	private String code2;

	/**
	 * This field corresponds to the database column WORLD_COUNTRY.IS_DELETED
	 */
	@Column(name = "is_deleted", nullable = false)
	private Integer isDeleted;

	public Nation() {
	}

	public Nation(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Nation{" + "code='" + this.code + '\'' + ", name='" + this.name + '\'' + ", continent='"
				+ this.continent + '\'' + ", region='" + this.region + '\'' + ", surfaceArea=" + this.surfaceArea
				+ ", independenceYear=" + this.independenceYear + ", population=" + this.population
				+ ", lifeExpectancy=" + this.lifeExpectancy + ", gnp=" + this.gnp + ", gnpOld=" + this.gnpOld
				+ ", localName='" + this.localName + '\'' + ", governmentForm='" + this.governmentForm + '\''
				+ ", headOfState='" + this.headOfState + '\'' + ", capital=" + this.capital + ", code2='" + this.code2
				+ '\'' + ", isDeleted=" + this.isDeleted + '}';
	}
}