package com.letscard.struts2.action.user;

import com.letscard.jpa.model.*;
import com.letscard.jpa.service.ServiceFactory;
import com.letscard.jpa.service.UserService;
import com.letscard.utils.faker.UserFaker;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import java.util.List;

public class UserAction extends ActionSupport implements ModelDriven<User>, Preparable {

    private User user = new UserFaker().create();
    private Long userId;
    private Long otherUserId;
    private Long cardId;
    private Long placeId;
    private List<User> users;
    private List<Place> places;
    private List<UserStandardCard> userStandardCards;
    private List<UserSharableCard> userSharableCards;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOtherUserId() {
        return otherUserId;
    }

    public void setOtherUserId(Long otherUserId) {
        this.otherUserId = otherUserId;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    public List<UserStandardCard> getUserStandardCards() {
        return userStandardCards;
    }

    public void setUserStandardCards(List<UserStandardCard> userStandardCards) {
        this.userStandardCards = userStandardCards;
    }

    public List<UserSharableCard> getUserSharableCards() {
        return userSharableCards;
    }

    public void setUserSharableCards(List<UserSharableCard> userSharableCards) {
        this.userSharableCards = userSharableCards;
    }

    public String createUserPage() {
        //prepare();
        return ActionSupport.SUCCESS;
    }

    public String createUser() {
        getUser().addPlace(ServiceFactory.getInstance().getPlaceService().read(getPlaceId()));
        ServiceFactory.getInstance().getUserService().create(getUser());
        return ActionSupport.SUCCESS;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String removeUser() {
        UserService userService = ServiceFactory.getInstance().getUserService();
        userService.delete(userService.read(getUser().getId()));
        return ActionSupport.SUCCESS;
    }

    public String removeAllUsers() {
        ServiceFactory.getInstance().getUserService().deleteAll();
        return ActionSupport.SUCCESS;
    }

    public String showUsers() {
        UserService userService = ServiceFactory.getInstance().getUserService();
        setUsers(userService.readAll());
        return ActionSupport.SUCCESS;
    }

    public String removeStandardCard() {
        ServiceFactory.getInstance().getUserService().removeStandardCard(
                ServiceFactory.getInstance().getUserService().read(getUserId()),
                (StandardCard) ServiceFactory.getInstance().getCardService().read(getCardId())
        );
        return ActionSupport.SUCCESS;
    }

    public String removeSharableCard() {
        ServiceFactory.getInstance().getUserService().removeSharableCard(
                ServiceFactory.getInstance().getUserService().read(getUserId()),
                ServiceFactory.getInstance().getUserService().read(getOtherUserId()),
                (SharableCard) ServiceFactory.getInstance().getCardService().read(getCardId())
        );
        return ActionSupport.SUCCESS;
    }

    public String removeAllUserStandardCards() {
        ServiceFactory.getInstance().getUserService().removeAllStandardCard(
                ServiceFactory.getInstance().getUserService().read(getUserId())
        );
        return ActionSupport.SUCCESS;
    }

    public String removeAllUserSharableCards() {
        ServiceFactory.getInstance().getUserService().removeAllSharableCard(
                ServiceFactory.getInstance().getUserService().read(getUserId())
        );
        return ActionSupport.SUCCESS;
    }

    public String removeAllUserCards() {
        ServiceFactory.getInstance().getUserService().removeAllCard(
                ServiceFactory.getInstance().getUserService().read(getUserId())
        );
        return ActionSupport.SUCCESS;
    }

    public String showUserCards() {
        setUser(ServiceFactory.getInstance().getUserService().read(getUserId()));
        setUserStandardCards(ServiceFactory.getInstance().getUserService().getUserStandardCard(getUserId()));
        setUserSharableCards(ServiceFactory.getInstance().getUserService().getUserSharablecard(getUserId()));
        return ActionSupport.SUCCESS;
    }

    public String removeUserCards() {
        return ActionSupport.SUCCESS;
    }

    @Override
    public void prepare() {
        setPlaces(ServiceFactory.getInstance().getPlaceService().readAll());
    }

    @Override
    public User getModel() {
        return user;
    }
}
