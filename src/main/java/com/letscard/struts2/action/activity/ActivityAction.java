package com.letscard.struts2.action.activity;

import com.letscard.jpa.model.BusinessActivity;
import com.letscard.jpa.model.Card;
import com.letscard.jpa.model.Place;
import com.letscard.jpa.service.BusinessActivityService;
import com.letscard.jpa.service.ServiceFactory;
import com.letscard.utils.faker.BusinessActivityFaker;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import java.util.List;

public class ActivityAction extends ActionSupport implements ModelDriven<BusinessActivity>, Preparable {

    private BusinessActivity activity = new BusinessActivityFaker().create();
    private Long placeId;
    private Long cardId;
    private Long activityId;
    private List<Place> places;
    private List<BusinessActivity> activities;
    private List<Card> businessCards;


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

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
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

    public List<BusinessActivity> getActivities() {
        return activities;
    }

    public void setActivities(List<BusinessActivity> activities) {
        this.activities = activities;
    }

    public List<Card> getBusinessCards() {
        return businessCards;
    }

    public void setBusinessCards(List<Card> businessCards) {
        this.businessCards = businessCards;
    }

    public String removeActivity() {
        BusinessActivityService businessActivityService = ServiceFactory.getInstance().getBusinessActivityService();
        businessActivityService.delete(businessActivityService.read(getActivity().getId()));
        return ActionSupport.SUCCESS;
    }

    public String removeAllActivities() {
        ServiceFactory.getInstance().getBusinessActivityService().deleteAll();
        return ActionSupport.SUCCESS;
    }

    public String showActivities() {
        setActivities(ServiceFactory.getInstance().getBusinessActivityService().readAll());
        return ActionSupport.SUCCESS;
    }

    public String createActivityPage() {
        //prepare();
        return ActionSupport.SUCCESS;
    }

    public String createActivity() {
        getActivity().addPlace(ServiceFactory.getInstance().getPlaceService().read(getPlaceId()));
        ServiceFactory.getInstance().getBusinessActivityService().create(getActivity());
        return ActionSupport.SUCCESS;
    }

    public String showActivityCards() {
        System.out.println("Show activity cards " + getActivityId());
        setActivity(ServiceFactory.getInstance().getBusinessActivityService().read(getActivityId()));
        setBusinessCards(ServiceFactory.getInstance().getBusinessActivityService().getActivityCards(getActivityId()));
        return ActionSupport.SUCCESS;
    }

    public String removeActivityCard() {
        System.out.println("Remove activity card " + getActivityId());
        ServiceFactory.getInstance().getBusinessActivityService().removeCard(
                ServiceFactory.getInstance().getCardService().read(getCardId())
        );
        return ActionSupport.SUCCESS;
    }

    public String removeAllActivityCards() {
        ServiceFactory.getInstance().getBusinessActivityService().removeAllCard(
                ServiceFactory.getInstance().getBusinessActivityService().read(getActivityId())
        );
        return ActionSupport.SUCCESS;
    }

    /*@Override
    public void validate() {
        if (ServiceFactory.getInstance().getBusinessActivityService().readByNaturalId(getActivity().getName()) != null)
            addFieldError("activity.name", "Activity " + getActivity().getName() + " already registered");
    }*/

    @Override
    public void prepare() {
        /*if (getActivityId() != null) {
            BusinessActivity businessActivity = ServiceFactory.getInstance().getBusinessActivityService().read(getActivityId());
            setActivityId(getActivityId());
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

