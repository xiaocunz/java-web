package net.xiaocun.bean;

/**
 * Created by zxiaocun on 2017/4/27.
 */
public class Company {
    private int id;
    private String companyName;
    private String[] websites;
    private Address address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String[] getWebsites() {
        return websites;
    }

    public void setWebsites(String[] websites) {
        this.websites = websites;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("\n id:" + this.id);
        sb.append("\n companyName:" + this.companyName);
        if (this.websites != null) {
            sb.append("\n website: ");
            for (String website : this.websites) {
                sb.append(website + ", ");
            }
        }
        if (this.address != null) {
            sb.append("\n address:" + this.address.toString());
        }
        return sb.toString();
    }
}
