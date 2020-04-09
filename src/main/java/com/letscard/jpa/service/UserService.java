package com.letscard.jpa.service;

import com.letscard.jpa.dao.UserDAO;
import com.letscard.jpa.model.*;

import java.util.List;

public class UserService extends AbstractService<User, Long, String> {

    public UserService() {
        super(new UserDAO());
    }

    public List<User> getUsersNotEquals(Long id) {
        return ((UserDAO) getDao()).getUsersNotEquals(id);
    }

    public List<UserStandardCard> getUserStandardCard(Long id) {
        return ((UserDAO) getDao()).getUserStandardCards(id);
    }

    public List<UserSharableCard> getUserSharablecard(Long id) {
        return ((UserDAO) getDao()).getUserSharableCards(id);
    }

    public void addPlace(User user, Place place) {
        getDao().begin();
        user.addPlace(place);
        getDao().commit();
    }

    public void removePlace(User user) {
        Place place = user.getPlace();
        getDao().begin();
        if (place != null)
            user.removePlace();
        getDao().commit();
    }

    public void addStandardCard(User user, StandardCard standardCard) {
        getDao().begin();
        user.addStandardCard(standardCard);
        getDao().commit();
    }

    public void removeStandardCard(User user, StandardCard standardCard) {
        getDao().begin();
        user.removeStandardCard(standardCard);
        getDao().commit();
    }

    public void removeAllStandardCard(User user) {
        getDao().begin();
        user.removeAllStandardCard();
        getDao().commit();
    }

    public void addSharableCard(User user1, User user2, SharableCard sharableCard) {
        getDao().begin();
        user1.addSharableCard(user2, sharableCard);
        getDao().commit();
    }

    public void removeSharableCard(User user1, User user2, SharableCard sharableCard) {
        getDao().begin();
        user1.removeSharableCard(user2, sharableCard);
        getDao().commit();
    }

    public void removeAllSharableCard(User user) {
        getDao().begin();
        user.removeAllSharableCard();
        getDao().commit();
    }

    public void removeAllCard(User user) {
        getDao().begin();
        user.removeAllCard();
        getDao().commit();
    }

    @Override
    public void delete(User user) {
        removePlace(user);
        removeAllCard(user);
        getDao().begin();
        getDao().delete(user);
        getDao().commit();
    }

}
