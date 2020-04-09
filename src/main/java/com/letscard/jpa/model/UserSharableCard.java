package com.letscard.jpa.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class UserSharableCard implements Serializable {

    @Id
    @ManyToOne
    private User user1;
    @Id
    @ManyToOne
    private User user2;
    @Id
    @ManyToOne
    private SharableCard sharableCard;
    private int consumed;

    public UserSharableCard() {
    }

    public UserSharableCard(User user1, User user2, SharableCard sharableCard) {
        this.user1 = user1;
        this.user2 = user2;
        this.sharableCard = sharableCard;
    }

    public UserSharableCard(User user1, User user2, SharableCard sharableCard, int consumed) {
        this.user1 = user1;
        this.user2 = user2;
        this.sharableCard = sharableCard;
        this.consumed = consumed;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public SharableCard getSharableCard() {
        return sharableCard;
    }

    public void setSharableCard(SharableCard sharableCard) {
        this.sharableCard = sharableCard;
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
        if (!(o instanceof UserSharableCard)) return false;
        UserSharableCard that = (UserSharableCard) o;
        return user1.equals(that.user1) &&
                user2.equals(that.user2) &&
                sharableCard.equals(that.sharableCard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user1, user2, sharableCard);
    }

    @Override
    public String toString() {
        return "UserSharableCard{" +
                "user1=" + user1 +
                ", user2=" + user2 +
                ", sharableCard=" + sharableCard +
                ", consumed=" + consumed +
                '}';
    }
}
