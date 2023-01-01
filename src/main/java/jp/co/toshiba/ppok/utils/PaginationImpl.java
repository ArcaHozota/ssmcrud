package jp.co.toshiba.ppok.utils;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Basic {@code Page} implementation.
 *
 * @param <T> the type of which the page consists.
 * @author Oliver Gierke
 * @author Mark Paluch
 */
public class PaginationImpl<T> extends PageImpl<T> {

    private Integer current;

    private Integer totalPg;

    private Long totalRecords;

    public PaginationImpl(List<T> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public PaginationImpl(List<T> content) {
        super(content);
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getTotalPg() {
        return totalPg;
    }

    public void setTotalPg(Integer totalPg) {
        this.totalPg = totalPg;
    }

    public Long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Long totalRecords) {
        this.totalRecords = totalRecords;
    }
}

