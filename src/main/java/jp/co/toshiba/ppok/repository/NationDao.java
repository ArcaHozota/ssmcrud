package jp.co.toshiba.ppok.repository;

import jp.co.sony.ppog.entity.Nation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * searching dao of table WORLD_COUNTRY
 *
 * @author Administrator
 * @date 2022-12-16
 */
@Repository
public interface NationDao extends JpaRepository<Nation, String> {
}
