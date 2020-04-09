package com.letscard.utils.faker;

import com.letscard.jpa.model.SharableCard;

public class SharableCardFaker extends AbstractFaker<SharableCard> {

    @Override
    public SharableCard create() {
        SharableCard sharableCard = new SharableCard();
        sharableCard.setQuantity(10);
        sharableCard.setCardNumber(getFaker().code().gtin8());
        return sharableCard;
    }
}
