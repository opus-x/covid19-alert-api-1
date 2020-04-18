package com.immotef.corona.company;//package com.immotef.corona.company;
//
//import com.immotef.corona.company.repository.CompaniesRepository;
//import lombok.extern.java.Log;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.hibernate.Filter;
//import org.hibernate.Session;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//@Aspect
//@Component
//@Log
//public class CompanyFilterAdvisor {
//    private CompaniesRepository companiesRepository;
//
//    @Autowired
//    public CompanyFilterAdvisor(CompaniesRepository companiesRepository) {
//        this.companiesRepository = companiesRepository;
//    }
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Pointcut("execution(public * com.immotef.corona.company.CompanyAwareRepository+.*(..))")
//    protected void companyAwareRepositoryMethod() {
//
//    }
//
//    @Around(value = "companyAwareRepositoryMethod()")
//    public Object enableCompanyFilter(ProceedingJoinPoint joinPoint) throws Throwable {
//        Session session = null;
//        Long companyId = getCompanyId();
//
//        if(companyId == -1) {
//            return joinPoint.proceed();
//        }
//
//        try {
//            session = entityManager.unwrap(Session.class);
//
//            Filter filter = session.enableFilter("companyIdFilter");
//
//            filter.setParameter("companyId", companyId);
//        } catch (Exception exception) {
//            log.info("Error enabling company filter : Reason - " + exception.getMessage());
//        }
//
//        Object object = joinPoint.proceed();
//
//        if (null != session) {
//            session.disableFilter("companyIdFilter");
//        }
//
//        return object;
//    }
//
//    public long getCompanyId()
//            //TODO to be implemented in security task
//    // throws NotAuthenticatedException
//    {
//
//        Long companyId = CompanyContext.getCompanyId();
//        if (companyId != null)
//            return companyId;
//        else
//            return 1;
////TODO            throw new NotAuthenticatedException();
//    }
//}
