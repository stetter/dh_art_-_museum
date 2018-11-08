
package com.stetter.dhartmuseum.model;


import com.google.gson.annotations.Expose;


public class Venue {

    @Expose
    private String address1;
    @Expose
    private Object address2;
    @Expose
    private String begindate;
    @Expose
    private String city;
    @Expose
    private Object country;
    @Expose
    private String enddate;
    @Expose
    private String fullname;
    @Expose
    private Long ishamvenue;
    @Expose
    private String name;
    @Expose
    private String state;
    @Expose
    private Long venueid;
    @Expose
    private String zipcode;

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public Object getAddress2() {
        return address2;
    }

    public void setAddress2(Object address2) {
        this.address2 = address2;
    }

    public String getBegindate() {
        return begindate;
    }

    public void setBegindate(String begindate) {
        this.begindate = begindate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Object getCountry() {
        return country;
    }

    public void setCountry(Object country) {
        this.country = country;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Long getIshamvenue() {
        return ishamvenue;
    }

    public void setIshamvenue(Long ishamvenue) {
        this.ishamvenue = ishamvenue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getVenueid() {
        return venueid;
    }

    public void setVenueid(Long venueid) {
        this.venueid = venueid;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
