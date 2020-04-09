package com.letscard.jpa.dao;

import com.letscard.jpa.model.Card;
import com.letscard.jpa.model.SharableCard;
import com.letscard.jpa.model.StandardCard;

import java.util.List;

public class CardDAO extends AbstractDAO<Card, Long, String> {

    public CardDAO() {
        super(Card.class);
    }

    public List<StandardCard> getStandardCards() {
        return getEntityManager().createQuery("SELECT c FROM Card c WHERE TYPE = 'ST'").getResultList();
    }

    public List<SharableCard> getSharableCards() {
        return getEntityManager().createQuery("SELECT c FROM Card c WHERE TYPE = 'SH'").getResultList();
    }
}

