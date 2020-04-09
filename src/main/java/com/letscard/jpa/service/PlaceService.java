package com.letscard.jpa.service;

import com.letscard.jpa.dao.PlaceDAO;
import com.letscard.jpa.model.BusinessActivity;
import com.letscard.jpa.model.Place;
import com.letscard.jpa.model.User;

public class PlaceService extends AbstractService<Place, Long, String> {

    public PlaceService() {
        super(new PlaceDAO());
    }

    public void addUser(Place place, User user) {
        getDao().begin();
        place.addUser(user);
        getDao().commit();
    }

    public void removeUser(User user) {
        Place place = user.getPlace();
        getDao().begin();
        if (place != null)
            place.removeUser(user);
        getDao().commit();
    }

    public void removeAllUser(Place place) {
        getDao().begin();
        place.removeAllUser();
        getDao().commit();
    }

    public void addBusinessActivity(Place place, BusinessActivity businessActivity) {
        getDao().begin();
        place.addBusinessActivity(businessActivity);
        getDao().commit();
    }

    public void removeBusinessActivity(BusinessActivity businessActivity) {
        Place place = businessActivity.getPlace();
        getDao().begin();
        if (place != null)
            place.removeBusinessActivity(businessActivity);
        getDao().commit();
    }

    public void removeAllActivities(Place place) {
        getDao().begin();
        place.removeAllActivities();
        getDao().commit();
    }

    @Override
    public void delete(Place place) {
        removeAllUser(place);
        removeAllActivities(place);
        getDao().begin();
        getDao().delete(place);
        getDao().commit();
    }
}
