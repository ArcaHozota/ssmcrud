package jp.co.toshiba.ppok.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "world_country")
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
	private BigDecimal lifeExpectancy;

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
	private Long capital;

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

	public Nation(final String code, final String name, final String continent, final String region,
			final BigDecimal surfaceArea, final Integer independenceYear, final Long population,
			final BigDecimal lifeExpectancy, final BigDecimal gnp, final BigDecimal gnpOld, final String localName,
			final String governmentForm, final String headOfState, final Long capital, final String code2,
			final Integer isDeleted) {
		this.code = code;
		this.name = name;
		this.continent = continent;
		this.region = region;
		this.surfaceArea = surfaceArea;
		this.independenceYear = independenceYear;
		this.population = population;
		this.lifeExpectancy = lifeExpectancy;
		this.gnp = gnp;
		this.gnpOld = gnpOld;
		this.localName = localName;
		this.governmentForm = governmentForm;
		this.headOfState = headOfState;
		this.capital = capital;
		this.code2 = code2;
		this.isDeleted = isDeleted;
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