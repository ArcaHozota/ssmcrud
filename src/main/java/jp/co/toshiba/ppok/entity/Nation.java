package jp.co.toshiba.ppok.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

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

	public Nation(String code, String name, String continent, String region, BigDecimal surfaceArea,
			Integer independenceYear, Long population, BigDecimal lifeExpectancy, BigDecimal gnp, BigDecimal gnpOld,
			String localName, String governmentForm, String headOfState, Long capital, String code2,
			Integer isDeleted) {
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
		return "Nation{" + "code='" + code + '\'' + ", name='" + name + '\'' + ", continent='" + continent + '\''
				+ ", region='" + region + '\'' + ", surfaceArea=" + surfaceArea + ", independenceYear="
				+ independenceYear + ", population=" + population + ", lifeExpectancy=" + lifeExpectancy + ", gnp="
				+ gnp + ", gnpOld=" + gnpOld + ", localName='" + localName + '\'' + ", governmentForm='"
				+ governmentForm + '\'' + ", headOfState='" + headOfState + '\'' + ", capital=" + capital + ", code2='"
				+ code2 + '\'' + ", isDeleted=" + isDeleted + '}';
	}
}