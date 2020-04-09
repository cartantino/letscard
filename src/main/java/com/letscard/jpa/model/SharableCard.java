package com.letscard.jpa.model;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue(value = "SH")
public class SharableCard extends Card implements Serializable {

    @OneToMany(mappedBy = "user1", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserSharableCard> users1 = new HashSet<>();

    @OneToMany(mappedBy = "user2", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserSharableCard> users2 = new HashSet<>();

    public SharableCard() {
    }

    public SharableCard(String cardNumber, int quantity) {
        super(cardNumber, quantity);
    }

    public SharableCard(String cardNumber) {
        super(cardNumber);
    }

    public SharableCard(int quantity) {
        super(quantity);
    }

    public Set<UserSharableCard> getUsers1() {
        return users1;
    }

    public void setUsers1(Set<UserSharableCard> users1) {
        this.users1 = users1;
    }

    public Set<UserSharableCard> getUsers2() {
        return users2;
    }

    public void setUsers2(Set<UserSharableCard> users2) {
        this.users2 = users2;
    }

    @Override
    public String toString() {
        return "SharableCard{} " + super.toString();
    }
}
