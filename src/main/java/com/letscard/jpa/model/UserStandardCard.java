package com.letscard.jpa.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class UserStandardCard implements Serializable {

    @Id
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private User user;
    @Id
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private StandardCard standardCard;
    private int consumed = 0;

    public UserStandardCard() {
    }

    public UserStandardCard(User user, StandardCard standardCard, int consumed) {
        this.user = user;
        this.standardCard = standardCard;
        this.consumed = consumed;
    }

    UserStandardCard(User user, StandardCard standardCard) {
        this.user = user;
        this.standardCard = standardCard;
        this.consumed = 0;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public StandardCard getStandardCard() {
        return standardCard;
    }

    public void setStandardCard(StandardCard standardCard) {
        this.standardCard = standardCard;
    }

    public int getConsumed() {
        return consumed;
    }

    public void setConsumed(int consumed) {
        this.consumed = consumed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserStandardCard)) return false;
        UserStandardCard that = (UserStandardCard) o;
        return Objects.equals(user, that.user) &&
                Objects.equals(standardCard, that.standardCard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, standardCard);
    }

    @Override
    public String toString() {
        return "UserStandardCard{" +
                "user=" + user +
                ", standardCard=" + standardCard +
                ", consumed=" + consumed +
                '}';
    }
}
