package com.immotef.corona.company.context;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface CompanyAwareRepository<T, ID extends Serializable> extends JpaRepository<T, ID>,
        QuerydslPredicateExecutor<T> {
}