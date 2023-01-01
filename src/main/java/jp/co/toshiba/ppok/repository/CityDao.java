package jp.co.toshiba.ppok.repository;

import jp.co.toshiba.ppok.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * searching dao of table WORLD_CITY
 *
 * @author Administrator
 * @date 2022-12-17
 */
@Repository
public interface CityDao extends JpaRepository<City, Long> {

    /**
     * logic remove query.
     *
     * @param id id of the selected city
     */
    @Modifying
    @Transactional
    @Query(value = "update City cvn set cvn.isDeleted = 1 where cvn.id =:id")
    void removeById(@Param("id") final Long id);
}
