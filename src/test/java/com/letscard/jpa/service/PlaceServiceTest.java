package com.letscard.jpa.service;

import com.letscard.jpa.model.Place;
import com.letscard.utils.Helper;
import com.letscard.utils.faker.PlaceFaker;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PlaceServiceTest {

    private PlaceService placeService = ServiceFactory.getInstance().getPlaceService();
    private PlaceFaker faker = new PlaceFaker();

    @AfterClass
    public static void dropDb() {
        Helper.dropDatabase();
    }

    @Before
    public void before() {
        Helper.dropDatabase();
    }

    private Place createPlaceWithActivities(int quantity) {
        Place place = faker.createWithActivities(quantity);
        placeService.create(place);
        return place;
    }

    private Place createPlaceWithUsers(int quantity) {
        Place place = faker.createWithUsers(quantity);
        placeService.create(place);
        return place;
    }

    private Place createPlace() {
        Place place = faker.create();
        placeService.create(place);
        return place;
    }

    private void createPlaces(int quantity) {
        List<Place> places = faker.create(quantity);
        for (Place place : places)
            placeService.create(place);
    }

    private void createPlacesWithUsersAndActivities(int quantity) {
        Place place;
        for (int i = 0; i < quantity; i++) {
            place = faker.createWithUsersAndActivities(quantity);
            placeService.create(place);
        }
    }

    @Test
    public void create() {
        Place place = createPlace();
        assertNotNull(place.getId());
    }

    @Test
    public void readByNaturalId() {
        Place place = createPlace();
        Place place1 = placeService.readByNaturalId(place.getCAP());
        assertEquals(place, place1);
    }

    @Test
    public void read() {
        Place place = createPlace();
        place = placeService.read(place.getId());
        assertNotNull(place);
    }

    @Test
    public void update() {
        Place place = createPlace();
        place.setProvince("Test");
        place = placeService.update(place);
        assertEquals("Test", place.getProvince());
    }

    @Test
    public void readAll() {
        createPlacesWithUsersAndActivities(2);
        assertEquals(2, placeService.readAll().size());
    }

    @Test
    public void deleteAll() {
        createPlacesWithUsersAndActivities(2);
        placeService.deleteAll();
        assertEquals(0, placeService.readAll().size());
    }

    @Test
    public void addUser() {
        Place place = createPlaceWithUsers(1);
        assertEquals(1, place.getUsers().size());
    }

    @Test
    public void removeUser() {
        Place place = createPlaceWithUsers(1);
        placeService.removeUser(place.getUsers().get(0));
        assertEquals(0, place.getUsers().size());
    }

    @Test
    public void removeAllUser() {
        Place place = createPlaceWithUsers(2);
        placeService.removeAllUser(place);
        assertEquals(0, place.getUsers().size());
    }

    @Test
    public void addBusinessActivity() {
        Place place = createPlaceWithActivities(1);
        assertEquals(1, place.getActivities().size());
    }

    @Test
    public void removeBusinessActivity() {
        Place place = createPlaceWithActivities(1);
        placeService.removeBusinessActivity(place.getActivities().get(0));
        assertEquals(0, place.getActivities().size());
    }

    @Test
    public void removeAllActivities() {
        createPlacesWithUsersAndActivities(2);
        for (Place place : placeService.readAll())
            placeService.removeAllActivities(place);
        for (Place place : placeService.readAll())
            assertEquals(0, place.getActivities().size());
    }

    @Test
    public void delete() {
        Place place = createPlace();
        placeService.delete(place);
        place = placeService.read(place.getId());
        assertNull(place);
    }
}