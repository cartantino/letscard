package com.letscard.utils.faker;

import com.letscard.jpa.model.Card;

public class CardFaker extends AbstractFaker<Card> {

    @Override
    public Card create() {
        String type = getFaker().random().nextInt(0, 1) == 1 ? "ST" : "SH";
        return create(type);
    }

    public Card create(String type) {
        if (type.equals("ST"))
            return new StandardCardFaker().create();
        else
            return new SharableCardFaker().create();
    }
/*    public SharableCard createSharableCard() {
        SharableCard sharableCard = new SharableCard();
        sharableCard.setQuantity(10);
        sharableCard.setCardNumber(getFaker().code().gtin8());
        return sharableCard;
    }

    public StandardCard createStandardCard() {
        StandardCard standardCard = new StandardCard();
        standardCard.setQuantity(10);
        standardCard.setCardNumber(getFaker().code().gtin8());
        return standardCard;
    }*/
}
