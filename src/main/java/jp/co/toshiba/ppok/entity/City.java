package jp.co.toshiba.ppok.entity;

import java.io.Serial;
import java.io.Serializable;

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
@Table(name = "city")
@NamedQueries({ @NamedQuery(name = "City.removeById", query = "update City c set c.isDeleted = 1 where c.id = :id") })
public record City(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) Integer id,
		@Column(nullable = false) String name, @Column(nullable = false) String countryCode,
		@Column(nullable = false) String district, @Column(nullable = false) Long population,
		@Column(nullable = false) Integer isDeleted) implements Serializable {
	@Serial
	private static final long serialVersionUID = 1815689293387304425L;
}