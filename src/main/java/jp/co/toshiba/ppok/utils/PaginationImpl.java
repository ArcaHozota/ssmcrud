package jp.co.toshiba.ppok.utils;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Basic {@code Page} implementation.
 *
 * @param <T> the type of which the page consists.
 * @author Oliver Gierke
 * @author Mark Paluch
 */
public class PaginationImpl<T> extends PageImpl<T> {

    public PaginationImpl(List<T> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public PaginationImpl(List<T> content) {
        super(content);
    }
}

