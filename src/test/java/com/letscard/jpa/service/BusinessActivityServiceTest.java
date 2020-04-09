package com.letscard.jpa.service;

import com.letscard.jpa.model.BusinessActivity;
import com.letscard.utils.Helper;
import com.letscard.utils.faker.BusinessActivityFaker;
import com.letscard.utils.faker.CardFaker;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BusinessActivityServiceTest {

    private BusinessActivityFaker faker = new BusinessActivityFaker();
    private BusinessActivityService businessActivityService = ServiceFactory.getInstance().getBusinessActivityService();

    @AfterClass
    public static void dropDb() {
        Helper.dropDatabase();
    }

    @Before
    public void before() {
        Helper.dropDatabase();
    }

    private void createActivities(int quantity) {
        List<BusinessActivity> activities = faker.create(quantity);
        for (BusinessActivity activity : activities)
            businessActivityService.create(activity);
    }

    private BusinessActivity createBusinessActivityWithCards(int quantity) {
        BusinessActivity businessActivity = faker.createWithCards(quantity);
        businessActivityService.create(businessActivity);
        return businessActivity;
    }

    private BusinessActivity createBusinessActivityWithPlace() {
        BusinessActivity businessActivity = faker.createWithPlace();
        businessActivityService.create(businessActivity);
        return businessActivity;
    }

    private BusinessActivity createBusinessActivity() {
        BusinessActivity businessActivity = faker.create();
        businessActivityService.create(businessActivity);
        return businessActivity;
    }

    @Test
    public void create() {
        BusinessActivity businessActivity = createBusinessActivity();
        assertNotNull(businessActivity.getId());
    }

    @Test
    public void readByNaturalId() {
        BusinessActivity businessActivity = createBusinessActivity();
        BusinessActivity businessActivity1 = businessActivityService.readByNaturalId(businessActivity.getName());
        assertEquals(businessActivity, businessActivity1);
    }

    @Test
    public void read() {
        BusinessActivity businessActivity = createBusinessActivity();
        businessActivity = businessActivityService.read(businessActivity.getId());
        assertNotNull(businessActivity);
        assertNotNull(businessActivity.getName());
        assertNotNull(businessActivity.getType());
    }

    @Test
    public void update() {
        BusinessActivity businessActivity = createBusinessActivity();
        businessActivity.setType("Test");
        businessActivity = businessActivityService.update(businessActivity);
        businessActivity = businessActivityService.read(businessActivity.getId());
        assertEquals(businessActivity.getType(), "Test");
    }

    @Test
    public void readAll() {
        createActivities(2);
        assertEquals(2, businessActivityService.readAll().size());
    }

    @Test
    public void deleteAll() {
        createActivities(2);
        businessActivityService.deleteAll();
        assertEquals(0, businessActivityService.readAll().size());
    }

    @Test
    public void addPlace() {
        BusinessActivity businessActivity = createBusinessActivityWithPlace();
        assertNotNull(businessActivity.getPlace().getId());
    }

    @Test
    public void removePlace() {
        BusinessActivity businessActivity = createBusinessActivityWithPlace();
        businessActivityService.removePlace(businessActivity);
        businessActivity = businessActivityService.read(businessActivity.getId());
        assertNull(businessActivity.getPlace());
    }

    @Test
    public void addCard() {
        BusinessActivity businessActivity = createBusinessActivity();
        businessActivityService.addCard(businessActivity, new CardFaker().create());
        assertEquals(1, businessActivity.getCards().size());
    }

    @Test
    public void removeCard() {
        BusinessActivity businessActivity = createBusinessActivityWithCards(1);
        businessActivityService.removeCard(businessActivity.getCards().get(0));
        assertEquals(0, businessActivity.getCards().size());
    }

    @Test
    public void removeAllCard() {
        BusinessActivity businessActivity = createBusinessActivityWithCards(2);
        businessActivityService.removeAllCard(businessActivity);
        businessActivity = businessActivityService.read(businessActivity.getId());
        assertEquals(0, businessActivity.getCards().size());
    }

    @Test
    public void delete() {
        BusinessActivity businessActivity = createBusinessActivity();
        businessActivityService.delete(businessActivity);
        businessActivity = businessActivityService.read(businessActivity.getId());
        assertNull(businessActivity);
    }
}