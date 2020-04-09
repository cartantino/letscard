package com.letscard.jpa.service;

import com.letscard.jpa.model.SharableCard;
import com.letscard.jpa.model.StandardCard;
import com.letscard.jpa.model.User;
import com.letscard.jpa.model.UserSharableCard;
import com.letscard.utils.Helper;
import com.letscard.utils.faker.CardFaker;
import com.letscard.utils.faker.UserFaker;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserServiceTest {

    private CardService cardService = ServiceFactory.getInstance().getCardService();
    private UserService userService = ServiceFactory.getInstance().getUserService();
    private UserFaker faker = new UserFaker();

    @AfterClass
    public static void tearDown() {
        Helper.dropDatabase();
    }

    @Before
    public void before() {
        Helper.dropDatabase();
        //Helper.resetIdAutoIncrement(Card.class);
    }

    private User createUser() {
        User user = faker.create();
        userService.create(user);
        return user;
    }

    private void createUsers(int quantity) {
        List<User> users = faker.create(quantity);
        for (User user : users)
            userService.create(user);
    }

    private User createUserWithPlace() {
        User user = faker.createWithPlace();
        userService.create(user);
        return user;
    }

    private User createUserWithStandardCards(int quantity) {
        User user = createUser();
        StandardCard standardCard;
        for (int i = 0; i < quantity; i++) {
            standardCard = (StandardCard) new CardFaker().create("ST");
            cardService.create(standardCard);
            user.addStandardCard(standardCard);
        }
        userService.create(user);
        return user;
    }

    private User createUserWithSharableCards(int quantity) {
        User user = createUser();
        SharableCard sharableCard;
        for (int i = 0; i < quantity; i++) {
            sharableCard = (SharableCard) new CardFaker().create("SH");
            cardService.create(sharableCard);
            user.addSharableCard(createUser(), sharableCard);
        }
        userService.create(user);
        return user;
    }

    @Test
    public void create() {
        User user = createUser();
        assertNotNull(user.getId());
    }

    @Test
    public void readByNaturalId() {
        User user = createUser();
        User user1 = userService.readByNaturalId(user.getEmail());
        assertEquals(user, user1);
    }

    @Test
    public void read() {
        User user = createUser();
        user = userService.read(user.getId());
        assertNotNull(user);
    }

    @Test
    public void update() {
        User user = createUser();
        user.setName("Test");
        user = userService.update(user);
        assertEquals("Test", user.getName());
    }

    @Test
    public void readAll() {
        createUsers(3);
        assertEquals(3, userService.readAll().size());
    }

    @Test
    public void deleteAll() {
        createUsers(3);
        userService.deleteAll();
        assertEquals(0, userService.readAll().size());
    }

    @Test
    public void addPlace() {
        User user = createUserWithPlace();
        assertNotNull(user.getPlace().getId());
    }

    @Test
    public void removePlace() {
        User user = createUserWithPlace();
        userService.removePlace(user);
        user = userService.read(user.getId());
        assertNull(user.getPlace());
    }

    @Test
    public void addStandardCard() {
        User user = createUserWithStandardCards(1);
        assertEquals(1, user.getStandardCards().size());
    }

    @Test
    public void removeStandardCard() {
        User user = createUserWithStandardCards(1);
        StandardCard standardCard = user.getStandardCards().iterator().next().getStandardCard();
        userService.removeStandardCard(user, standardCard);
        assertEquals(0, user.getStandardCards().size());
    }

    @Test
    public void removeAllStandardCard() {
        User user = createUserWithStandardCards(2);
        userService.removeAllStandardCard(user);
        assertEquals(0, user.getStandardCards().size());
    }

    @Test
    public void addSharableCard() {
        User user = createUserWithSharableCards(1);
        assertNotNull(user.getSharableCards().iterator().next().getUser2().getId());
    }

    @Test
    public void removeSharableCard() {
        User user = createUserWithSharableCards(1);
        UserSharableCard ush = user.getSharableCards().iterator().next();
        userService.removeSharableCard(ush.getUser1(), ush.getUser2(), ush.getSharableCard());
        assertEquals(0, user.getSharableCards().size());
    }

    @Test
    public void removeAllSharableCard() {
        User user = createUserWithSharableCards(2);
        userService.removeAllSharableCard(user);
        assertEquals(0, user.getSharableCards().size());
    }

    @Test
    public void removeAllCard() {
        User user1 = createUserWithSharableCards(2);
        User user2 = createUserWithStandardCards(2);
        userService.removeAllCard(user1);
        assertEquals(0, user1.getSharableCards().size());
        userService.removeAllCard(user2);
        assertEquals(0, user2.getStandardCards().size());
    }

    @Test
    public void delete() {
        User user = createUserWithPlace();
        userService.delete(user);
        user = userService.read(user.getId());
        assertNull(user);
    }
}