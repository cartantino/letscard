package com.letscard.jpa.dao;

import com.letscard.jpa.model.Place;

public class PlaceDAO extends AbstractDAO<Place, Long, String> {

    public PlaceDAO() {
        super(Place.class);
    }
}
