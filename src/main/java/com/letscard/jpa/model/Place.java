package com.letscard.jpa.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

@Entity
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NaturalId
    private String CAP;
    private String city;
    private String province;
    private String region;
    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private List<User> users = new ArrayList<>();
    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private List<BusinessActivity> activities = new ArrayList<>();

    public Place() {
    }

    public Place(String CAP, String city, String province, String region) {
        this.CAP = CAP;
        this.city = city;
        this.province = province;
        this.region = region;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCAP() {
        return CAP;
    }

    public void setCAP(String CAP) {
        this.CAP = CAP;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<BusinessActivity> getActivities() {
        return activities;
    }

    public void setActivities(List<BusinessActivity> activities) {
        this.activities = activities;
    }

    public void addBusinessActivity(BusinessActivity businessActivity) {
        getActivities().add(businessActivity);
        businessActivity.setPlace(this);
    }

    public void removeBusinessActivity(BusinessActivity businessActivity) {
        getActivities().remove(businessActivity);
        businessActivity.setPlace(null);
    }

    public void removeAllActivities() {
        ListIterator<BusinessActivity> i = getActivities().listIterator();
        BusinessActivity businessActivity;
        while (i.hasNext()) {
            businessActivity = i.next();
            i.remove();
            removeBusinessActivity(businessActivity);
        }
    }

    public void addUser(User user) {
        getUsers().add(user);
        user.setPlace(this);
    }

    public void removeUser(User user) {
        getUsers().remove(user);
        user.setPlace(null);
    }

    public void removeAllUser() {
        ListIterator<User> i = getUsers().listIterator();
        User user;
        while (i.hasNext()) {
            user = i.next();
            i.remove();
            removeUser(user);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Place)) return false;
        Place place = (Place) o;
        return Objects.equals(CAP, place.CAP);
    }

    @Override
    public int hashCode() {
        return Objects.hash(CAP);
    }

    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", region='" + region + '\'' +
                '}';
    }
}