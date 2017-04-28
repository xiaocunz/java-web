package net.xiaocun;

import com.thoughtworks.xstream.XStream;
import net.xiaocun.bean.Address;
import net.xiaocun.bean.Company;
import net.xiaocun.dao.DataDAO;

/**
 * Created by zxiaocun on 2017/4/27.
 */
public class App {
    public static void main(String[] args) {

        Company company = DataDAO.createCompany();

        XStream xs = new XStream();

        xs.alias("company", Company.class);
        xs.aliasField("name", Company.class, "companyName");

          // <websites></websites> ---> <website></website> <website></website>
//        xs.addImplicitArray(Company.class, "websites", "website");

        // useAttributeFor(Class definedIn, String fieldName).
        xs.useAttributeFor(Company.class, "id");

        xs.useAttributeFor(Company.class, "companyName");
        xs.aliasAttribute("name", "companyName");

        String packageName = Company.class.getPackage().getName();
        xs.aliasPackage("net.xiaocun", packageName);


        // JAVA OBJECT --> XML
        String xml = xs.toXML(company);

        System.out.println(xml);
    }
}
