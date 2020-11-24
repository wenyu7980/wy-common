package com.wenyu7980.data.jpa.query;

import com.wenyu7980.query.QueryPredicateExpress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.function.Consumer;

/**
 *
 * @author wenyu
 */
public abstract class AbstractQueryService<T> implements QueryService<T> {
    @Autowired
    private JpaSpecificationExecutor<T> executor;

    @Override
    public List<T> findList(QueryPredicateExpress express, Consumer<Root<T>> setFetch) {
        return this.executor.findAll((Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            setFetch.accept(root);
            return express.predicate(root, criteriaBuilder);
        });
    }

    @Override
    public List<T> findList(QueryPredicateExpress express, Sort sort, Consumer<Root<T>> setFetch) {
        return this.executor.findAll((Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            setFetch.accept(root);
            return express.predicate(root, criteriaBuilder);
        }, sort);
    }

    @Override
    public Page<T> findPage(QueryPredicateExpress express, Pageable pageable, Consumer<Root<T>> setFetch) {
        return this.executor.findAll((Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (query.getResultType() != Long.class) {
                setFetch.accept(root);
            }
            return express.predicate(root, criteriaBuilder);
        }, pageable);
    }

    @Override
    public long count(QueryPredicateExpress express) {
        return this.executor.count((Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            return express.predicate(root, criteriaBuilder);
        });
    }
}
