package com.letscard.struts2.action.place;

import com.letscard.jpa.model.Place;
import com.letscard.jpa.service.PlaceService;
import com.letscard.jpa.service.ServiceFactory;
import com.letscard.utils.faker.PlaceFaker;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import java.util.List;

public class PlaceAction extends ActionSupport implements ModelDriven<Place> {

    private List<Place> places;
    private Place place = new PlaceFaker().create();

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public String removeAllPlaces() {
        ServiceFactory.getInstance().getPlaceService().deleteAll();
        return ActionSupport.SUCCESS;
    }

    public String removePlace() {
        PlaceService placeService = ServiceFactory.getInstance().getPlaceService();
        placeService.delete(placeService.read(getPlace().getId()));
        return ActionSupport.SUCCESS;
    }

    public String showPlaces() {
        PlaceService placeService = ServiceFactory.getInstance().getPlaceService();
        setPlaces(placeService.readAll());
        return ActionSupport.SUCCESS;
    }

    public String createPlacePage() {
        return ActionSupport.SUCCESS;
    }

    public String createPlace() {
        ServiceFactory.getInstance().getPlaceService().create(getPlace());
        return ActionSupport.SUCCESS;
    }

    @Override
    public Place getModel() {
        return place;
    }
}
