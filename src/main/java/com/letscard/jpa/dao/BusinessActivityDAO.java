package com.letscard.jpa.dao;

import com.letscard.jpa.model.BusinessActivity;
import com.letscard.jpa.model.Card;

import java.util.List;

public class BusinessActivityDAO extends AbstractDAO<BusinessActivity, Long, String> {

    public BusinessActivityDAO() {
        super(BusinessActivity.class);
    }

    public List<Card> getActivityCards(Long id) {
        return getEntityManager().createQuery("SELECT c from Card c WHERE businessActivity_id =" + id).getResultList();
    }

}
