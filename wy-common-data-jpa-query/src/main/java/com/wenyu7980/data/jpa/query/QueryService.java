package com.wenyu7980.data.jpa.query;

import com.wenyu7980.query.QueryPredicateExpress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.criteria.Root;
import java.util.List;
import java.util.function.Consumer;

/**
 *
 * @author wenyu
 */
public interface QueryService<T> {
    /**
     * 列表查询
     * @param express
     * @param setFetch
     * @return
     */
    List<T> findList(QueryPredicateExpress express, Consumer<Root<T>> setFetch);

    /**
     * 列表排序查询
     * @param express
     * @param sort
     * @param setFetch
     * @return
     */
    List<T> findList(QueryPredicateExpress express, Sort sort, Consumer<Root<T>> setFetch);

    /**
     * 分页查询
     * @param express
     * @param pageable
     * @param setFetch
     * @return
     */
    Page<T> findPage(QueryPredicateExpress express, Pageable pageable, Consumer<Root<T>> setFetch);

    /**
     * 个数查询
     * @param express
     * @return
     */
    long count(QueryPredicateExpress express);
}
