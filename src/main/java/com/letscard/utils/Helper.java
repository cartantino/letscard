package com.letscard.utils;

import com.letscard.jpa.dao.EntityManagerSingleton;
import com.letscard.jpa.model.*;
import com.letscard.jpa.service.BusinessActivityService;
import com.letscard.jpa.service.PlaceService;
import com.letscard.jpa.service.ServiceFactory;
import com.letscard.jpa.service.UserService;
import com.letscard.utils.faker.BusinessActivityFaker;
import com.letscard.utils.faker.PlaceFaker;
import com.letscard.utils.faker.UserFaker;

import javax.persistence.EntityManager;

public class Helper {

    private final static EntityManager entityManager;

    static {
        entityManager = EntityManagerSingleton.createEntityManager();
    }

    public static void resetIdAutoIncrement(Class tClass) {
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("ALTER TABLE " + tClass.getSimpleName() + " AUTO_INCREMENT=1", tClass)
                .executeUpdate();
        entityManager.getTransaction().commit();
    }

    public static void populateDatabase(int quantity) {
        PlaceFaker faker = new PlaceFaker();
        PlaceService placeService = ServiceFactory.getInstance().getPlaceService();
        for (int i = 0; i < quantity; i++)
            placeService.create(faker.create());
        BusinessActivityFaker businessActivityFaker = new BusinessActivityFaker();
        BusinessActivityService businessActivityService = ServiceFactory.getInstance().getBusinessActivityService();
        for (int i = 0; i < quantity; i++)
            businessActivityService.create(businessActivityFaker.createWithPlaceAndCards(3));
        UserFaker userFaker = new UserFaker();
        UserService userService = ServiceFactory.getInstance().getUserService();
        for (int i = 0; i < quantity; i++)
            userService.create(userFaker.createWithPlace());
    }

    public static void dropDatabase() {
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("delete ignore from " + UserSharableCard.class.getSimpleName()).executeUpdate();
        entityManager.createNativeQuery("delete ignore from " + UserStandardCard.class.getSimpleName()).executeUpdate();
        entityManager.createNativeQuery("delete ignore from " + Card.class.getSimpleName()).executeUpdate();
        entityManager.createNativeQuery("delete ignore from " + User.class.getSimpleName()).executeUpdate();
        entityManager.createNativeQuery("delete ignore from " + Place.class.getSimpleName()).executeUpdate();
        entityManager.createNativeQuery("delete ignore from " + BusinessActivity.class.getSimpleName()).executeUpdate();
        entityManager.getTransaction().commit();
    }
}
