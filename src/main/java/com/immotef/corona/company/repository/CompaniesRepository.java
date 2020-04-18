package com.immotef.corona.company.repository;

import com.immotef.corona.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CompaniesRepository extends JpaRepository<Company, Long>, QuerydslPredicateExecutor<Company> {
}
