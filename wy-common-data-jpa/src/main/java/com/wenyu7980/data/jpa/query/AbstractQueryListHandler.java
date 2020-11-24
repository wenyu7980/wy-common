package com.wenyu7980.data.jpa.query;

import com.wenyu7980.query.QueryPredicateExpress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author wenyu
 */
public abstract class AbstractQueryListHandler<E, T>
  implements QueryExistsHandler, QueryListHandler<T>, ConvertList<E, T> {
    @Autowired
    private QueryService<E> queryService;

    @Override
    public long count(QueryPredicateExpress express) {
        return queryService.count(express);
    }

    @Override
    public List<T> getList(QueryPredicateExpress express) {
        return queryService.findList(express, this::setListFetch).stream().map(this::convertList)
          .collect(Collectors.toList());
    }

    @Override
    public List<T> getList(QueryPredicateExpress express, Sort sort) {
        return queryService.findList(express, sort, this::setSortListFetch).stream().map(this::convertList)
          .collect(Collectors.toList());
    }

    protected void setListFetch(Root<E> root) {
    }

    protected void setSortListFetch(Root<E> root) {
        this.setListFetch(root);
    }
}
