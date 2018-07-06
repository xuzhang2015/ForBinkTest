package bean;

import utilities.UtilitiesMethod;

import java.util.Date;

/**
 * Created by zx on 05/07/2018.
 * data bean
 * Create a new filed totalRent to display the total rent in table
 * Date filed as as long type
 * rent filed set as float with 2 digital type
 * years filed set as integer type
 */
public class CouncilProperty {
    private String name;
    private String address1;
    private String address2;
    private String address3;
    private String address4;
    private String unitName;
    private String tenantName;
    private Long leaseStartDate;
    private Long leaseEndDate;
    private Integer leaseYears;
    private Float currentRent;
    private Float totalRent;


    public CouncilProperty(String[] item) {
        name = item[0];
        address1 = item[1];
        address2 = item[2];
        address3 = item[3];
        address4 = item[4];
        unitName = item[5];
        tenantName = item[6];
        leaseStartDate = UtilitiesMethod.GetTheDate(item[7]);
        leaseEndDate = UtilitiesMethod.GetTheDate(item[8]);
        leaseYears = Integer.valueOf(item[9]);
        currentRent = Float.valueOf(item[10]);
        totalRent = leaseYears * currentRent;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getAddress4() {
        return address4;
    }

    public void setAddress4(String address4) {
        this.address4 = address4;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public Long getLeaseStartDate() {
        return leaseStartDate;
    }

    public void setLeaseStartDate(Long leaseStartDate) {
        this.leaseStartDate = leaseStartDate;
    }

    public Long getLeaseEndDate() {
        return leaseEndDate;
    }

    public void setLeaseEndDate(Long leaseEndDate) {
        this.leaseEndDate = leaseEndDate;
    }

    public Integer getLeaseYears() {
        return leaseYears;
    }

    public void setLeaseYears(Integer leaseYears) {
        this.leaseYears = leaseYears;
    }

    public Float getCurrentRent() {
        return currentRent;
    }

    public void setCurrentRent(Float currentRent) {
        this.currentRent = currentRent;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public Float getTotalRent() {
        return totalRent;
    }

    public void setTotalRent(Float totalRent) {
        this.totalRent = totalRent;
    }

    //return the String array to build the table
    public String[] getData() {
        String[] item = new String[]{
                name,
                address1,
                address2,
                address3,
                address4,
                unitName,
                tenantName,
                UtilitiesMethod.sdf.format(new Date(leaseStartDate)),
                UtilitiesMethod.sdf.format(new Date(leaseEndDate)),
                leaseYears + "",
                UtilitiesMethod.fnum.format(currentRent),
                UtilitiesMethod.fnum.format(totalRent)
        };
        return item;
    }
}
