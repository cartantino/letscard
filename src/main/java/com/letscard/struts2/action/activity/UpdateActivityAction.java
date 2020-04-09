package com.letscard.struts2.action.activity;

import com.letscard.jpa.model.BusinessActivity;
import com.letscard.jpa.model.Place;
import com.letscard.jpa.service.BusinessActivityService;
import com.letscard.jpa.service.ServiceFactory;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import java.util.List;

public class UpdateActivityAction extends ActionSupport implements ModelDriven<BusinessActivity>, Preparable {

    private BusinessActivity activity;
    private Long placeId;
    private Long activityId;
    private List<Place> places;

    public BusinessActivity getActivity() {
        return activity;
    }

    public void setActivity(BusinessActivity activity) {
        this.activity = activity;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    public String createUpdateActivityPage() {
        BusinessActivity businessActivity = ServiceFactory.getInstance().getBusinessActivityService().read(getActivityId());
        setActivity(businessActivity);
        if (businessActivity.getPlace() != null)
            setPlaceId(businessActivity.getPlace().getId());
        //setPlaces(ServiceFactory.getInstance().getPlaceService().readAll());
        //prepare();
        return ActionSupport.SUCCESS;
    }

    public String updateActivity() {
        BusinessActivityService businessActivityService = ServiceFactory.getInstance().getBusinessActivityService();
        BusinessActivity businessActivity = businessActivityService.read(getActivityId());
        businessActivity.setType(getActivity().getType());
        businessActivity.addPlace(ServiceFactory.getInstance().getPlaceService().read(getPlaceId()));
        businessActivityService.update(businessActivity);
        return ActionSupport.SUCCESS;
    }

    @Override
    public void prepare() {
        /*if (getActivityId() != null) {
            BusinessActivity businessActivity = ServiceFactory.getInstance().getBusinessActivityService().read(getActivityId());
            setActivity(businessActivity);
            if (businessActivity.getPlace() != null)
                setPlaceId(businessActivity.getPlace().getId());
        }
        if (places == null)*/
        setPlaces(ServiceFactory.getInstance().getPlaceService().readAll());
    }

    @Override
    public BusinessActivity getModel() {
        return activity;
    }
}
