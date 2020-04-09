package com.letscard.struts2.action.user;

import com.letscard.jpa.model.SharableCard;
import com.letscard.jpa.model.StandardCard;
import com.letscard.jpa.model.User;
import com.letscard.jpa.service.ServiceFactory;
import com.letscard.jpa.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import java.util.List;

public class AddCardAction extends ActionSupport implements ModelDriven<User> {

    private User user;
    private List<SharableCard> sharableCards;
    private List<StandardCard> standardCards;
    private List<User> usersShare;
    private Long userId;
    private Long userShareId;
    private Long cardId;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<SharableCard> getSharableCards() {
        return sharableCards;
    }

    public void setSharableCards(List<SharableCard> sharableCards) {
        this.sharableCards = sharableCards;
    }

    public List<StandardCard> getStandardCards() {
        return standardCards;
    }

    public void setStandardCards(List<StandardCard> standardCards) {
        this.standardCards = standardCards;
    }

    public List<User> getUsersShare() {
        return usersShare;
    }

    public void setUsersShare(List<User> usersShare) {
        this.usersShare = usersShare;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserShareId() {
        return userShareId;
    }

    public void setUserShareId(Long userShareId) {
        this.userShareId = userShareId;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public String createAddStandardCardPage() {
        setStandardCards(ServiceFactory.getInstance().getCardService().getStandardCards());
        return ActionSupport.SUCCESS;
    }

    public String createAddSharableCardPage() {
        setSharableCards(ServiceFactory.getInstance().getCardService().getSharableCards());
        setUsersShare(ServiceFactory.getInstance().getUserService().getUsersNotEquals(getUserId()));
        System.out.println(getUsersShare().isEmpty());
        return ActionSupport.SUCCESS;
    }

    public String addStandardCard() {
        UserService userService = ServiceFactory.getInstance().getUserService();
        User user = userService.read(getUserId());
        StandardCard st = (StandardCard) ServiceFactory.getInstance().getCardService().read(getCardId());
        userService.addStandardCard(user, st);
        userService.create(user);
        //setUser(user);
        return ActionSupport.SUCCESS;
    }

    public String addSharableCard() {
        UserService userService = ServiceFactory.getInstance().getUserService();
        User user1 = userService.read(getUserId());
        User user2 = userService.read(getUserShareId());
        SharableCard sh = (SharableCard) ServiceFactory.getInstance().getCardService().read(getCardId());
        userService.addSharableCard(user1, user2, sh);
        userService.create(user1);
        //setUser(user);
        return ActionSupport.SUCCESS;
    }

    @Override
    public User getModel() {
        return user;
    }
}
