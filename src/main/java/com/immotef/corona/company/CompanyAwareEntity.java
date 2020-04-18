package com.immotef.corona.company;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
@FilterDef(name = "companyIdFilter", parameters = {@ParamDef(name = "companyId", type = "long")})
@Filter(name = "companyIdFilter", condition = "company_id = :companyId")
public class CompanyAwareEntity implements Serializable {

    @Column(name = "company_id")
    protected long companyId = CompanyContext.getCompanyId();

    public void setCompanyId(long companyId) {
        this.companyId = CompanyContext.getCompanyId();
    }

    public long getCompanyId() {
        return companyId;
    }

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "company_id", insertable = false, updatable = false, nullable = false)
    protected Company company;

}