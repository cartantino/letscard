package com.letscard.jpa.service;

import com.letscard.jpa.model.Card;
import com.letscard.utils.Helper;
import com.letscard.utils.faker.CardFaker;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CardServiceTest {

    private CardService cardService = ServiceFactory.getInstance().getCardService();
    private CardFaker faker = new CardFaker();

    @AfterClass
    public static void tearDown() {
        Helper.dropDatabase();
    }

    @Before
    public void before() {
        Helper.dropDatabase();
        //Helper.resetIdAutoIncrement(Card.class);
    }

    private void createCards(int quantity) {
        List<Card> cards = faker.create(quantity);
        for (Card card : cards)
            cardService.create(card);
    }

    private Card createCard() {
        Card card = faker.create();
        cardService.create(card);
        return card;
    }

    @Test
    public void create() {
        Card card = createCard();
        assertNotNull(card.getId());
    }

    @Test
    public void read() {
        Card card = createCard();
        card = cardService.read(card.getId());
        assertNotNull(card);
    }

    @Test
    public void readByNaturalId() {
        Card card = createCard();
        Card card1 = cardService.readByNaturalId(card.getCardNumber());
        assertEquals(card, card1);
    }

    @Test
    public void update() {
        Card card = createCard();
        card.setQuantity(8);
        card = cardService.update(card);
        Card newCard = cardService.read(card.getId());
        assertEquals(8, newCard.getQuantity());
    }

    @Test
    public void readAll() {
        createCards(2);
        assertEquals(2, cardService.readAll().size());
    }

    @Test
    public void deleteAll() {
        createCards(2);
        cardService.deleteAll();
        assertEquals(0, cardService.readAll().size());
    }

    @Test
    public void delete() {
        Card card = createCard();
        cardService.delete(card);
        card = cardService.read(card.getId());
        assertNull(card);
    }
}
