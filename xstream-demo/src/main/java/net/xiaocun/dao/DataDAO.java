package net.xiaocun.dao;

import net.xiaocun.bean.Address;
import net.xiaocun.bean.Company;

/**
 * Created by zxiaocun on 2017/4/27.
 */
public class DataDAO {
    public static Company createCompany() {

        Company company = new Company();
        company.setId(111);
        company.setCompanyName("Microsoft");

        String[] websites = { "http://microsoft.com",
                "http://msn.com", "http://hotmail.com" };
        company.setWebsites(websites);

        Address address = new Address();
        address.setCity("Redmond");
        address.setStreet("1 Microsoft Way");

        company.setAddress(address);

        return company;
    }
}
