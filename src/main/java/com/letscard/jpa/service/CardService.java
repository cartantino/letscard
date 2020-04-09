package com.letscard.jpa.service;

import com.letscard.jpa.dao.CardDAO;
import com.letscard.jpa.model.BusinessActivity;
import com.letscard.jpa.model.Card;
import com.letscard.jpa.model.SharableCard;
import com.letscard.jpa.model.StandardCard;

import java.util.List;

public class CardService extends AbstractService<Card, Long, String> {

    public CardService() {
        super(new CardDAO());
    }

    @Override
    public void delete(Card card) {
        BusinessActivity businessActivity = card.getBusinessActivity();
        getDao().begin();
        if (businessActivity != null)
            businessActivity.removeCard(card);
        getDao().commit();
        getDao().begin();
        getDao().delete(card);
        getDao().commit();
    }

    public List<StandardCard> getStandardCards() {
        return ((CardDAO) getDao()).getStandardCards();
    }

    public List<SharableCard> getSharableCards() {
        return ((CardDAO) getDao()).getSharableCards();
    }

}
