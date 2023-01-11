package jp.co.toshiba.ppok.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

/**
 * dto of the view of world cities
 *
 * @author Administrator
 */
@Entity
@Proxy(lazy = false)
@NoArgsConstructor
@Table(name = "city_view")
@NamedQueries({
		@NamedQuery(name = "CityInfo.findByNations", query = "select c from CityInfo c where c.nation = :nation"),
		@NamedQuery(name = "CityInfo.getByNations", query = "select c from CityInfo c where c.nation = :nation order by c.id asc"),
		@NamedQuery(name = "CityInfo.getByNames", query = "select c from CityInfo c where c.name like concat('%', :name, '%') order by c.id asc") })
public record CityInfo(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) Integer id,
		@Column(nullable = false) @Pattern(regexp = "^[a-zA-Z-\\p{IsWhiteSpace}]{4,17}$", message = "Name of cities should be in 4~17 Latin alphabets.") String name,
		@Column(nullable = false) String continent, @Column(nullable = false) String nation,
		@Column(nullable = false) String district, @Column(nullable = false) Long population) implements Serializable {
	private static final long serialVersionUID = -863534569423043863L;
}
