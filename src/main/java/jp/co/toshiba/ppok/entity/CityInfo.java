package jp.co.toshiba.ppok.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Objects;

/**
 * dto of the view of world cities
 *
 * @author Administrator
 */

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Proxy(lazy = false)
@Table(name = "world_city_view")
public class CityInfo implements Serializable {

    private static final long serialVersionUID = -863534569423043863L;

    /**
     * This field corresponds to the database column WORLD_CITY_VIEW.ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * This field corresponds to the database column WORLD_CITY_VIEW.NAME
     */
    @Column(nullable = false)
    @Pattern(regexp = "^[a-zA-Z-\\p{IsWhiteSpace}]{4,17}$", message = "Name of cities should be in 4~17 Latin alphabets.")
    private String name;

    /**
     * This field corresponds to the database column WORLD_CITY_VIEW.CONTINENT
     */
    @Column(nullable = false)
    private String continent;

    /**
     * This field corresponds to the database column WORLD_CITY_VIEW.NATION
     */
    @Column(nullable = false)
    private String nation;

    /**
     * This field corresponds to the database column WORLD_CITY_VIEW.DISTRICT
     */
    @Column(nullable = false)
    private String district;

    /**
     * This field corresponds to the database column WORLD_CITY_VIEW.POPULATION
     */
    @Column(nullable = false)
    private Long population;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Hibernate.getClass(this) != Hibernate.getClass(obj)) {
            return false;
        }
        CityInfo cityInfo = (CityInfo) obj;
        return id != null && Objects.equals(id, cityInfo.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
