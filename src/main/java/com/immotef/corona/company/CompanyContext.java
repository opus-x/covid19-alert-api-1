package com.immotef.corona.company;

import lombok.extern.java.Log;

@Log
public class CompanyContext {
    private static ThreadLocal<Long> company = new ThreadLocal<>();
    private static ThreadLocal<Long> loggedUserId = new ThreadLocal<>();

    static {
        setCompanyId(-1L);
        setLoggedUserId(-1L);
    }

    public static void setCompanyId(long companyId) {
        company.set(companyId);
    }

    public static Long getCompanyId() {
        return company.get() == null ? -1L : company.get();
    }

    public static void setLoggedUserId(Long userId) {
        loggedUserId.set(userId);
    }

    public static Long getLoggedUserId() {
        return loggedUserId.get() == null ? -1L : loggedUserId.get();
    }
}