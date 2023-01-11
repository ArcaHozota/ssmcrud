package jp.co.toshiba.ppok.entity;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import org.hibernate.annotations.Proxy;

/**
 * @author Administrator
 */
@Entity
@Proxy(lazy = false)
@Table(name = "country")
@NamedQueries({
		@NamedQuery(name = "Nation.findNationCode", query = "select distinct n from Nation n where n.name = :name"),
		@NamedQuery(name = "Nation.findNationsByCnt", query = "select distinct n from Nation n where n.continent = :continent order by n.name asc") })
public record Nation(@Id @GeneratedValue(strategy = GenerationType.AUTO) String code,
		@Column(nullable = false) String name, @Column(nullable = false) String continent,
		@Column(nullable = false) String region,
		@Column(name = "surface_area", nullable = false) BigDecimal surfaceArea,
		@Column(name = "independence_year") Integer independenceYear, @Column(nullable = false) Long population,
		@Column(name = "life_expectancy") Integer lifeExpectancy, BigDecimal gnp,
		@Column(name = "gnp_old") BigDecimal gnpOld, @Column(name = "local_name", nullable = false) String localName,
		@Column(name = "government_form", nullable = false) String governmentForm,
		@Column(name = "head_of_state") String headOfState, Integer capital, @Column(nullable = false) String code2,
		@Column(name = "is_deleted", nullable = false) Integer isDeleted) implements Serializable {
	@Serial
	private static final long serialVersionUID = -437505450837045511L;
}