package com.letscard.jpa.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.*;

@Entity
@NamedQuery(
        name = "findActivityByName",
        query = "SELECT b FROM BusinessActivity b WHERE b.name LIKE :name"
)
public class BusinessActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NaturalId
    private String name;
    private String type;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Place place;
    @OneToMany(mappedBy = "businessActivity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Card> cards = new ArrayList<>();

    public BusinessActivity() {
    }

    public BusinessActivity(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public void addCard(Card card) {
        getCards().add(card);
        card.setBusinessActivity(this);
    }

    public void removeCard(Card card) {
        getCards().remove(card);
        if (card instanceof StandardCard) {
            Iterator<UserStandardCard> i = ((StandardCard) card).getUsers().iterator();
            UserStandardCard u;
            while (i.hasNext()) {
                u = i.next();
                i.remove();
                u.getUser().removeStandardCard((StandardCard) card);
            }
        } else if (card instanceof SharableCard) {
            Iterator<UserSharableCard> i = ((SharableCard) card).getUsers1().iterator();
            UserSharableCard u;
            while (i.hasNext()) {
                u = i.next();
                i.remove();
                u.getUser1().removeSharableCard(u.getUser2(), (SharableCard) card);
            }
        }
        card.setBusinessActivity(null);
    }

    public void removeAllCard() {
        ListIterator<Card> i = getCards().listIterator();
        Card card;
        while (i.hasNext()) {
            card = i.next();
            i.remove();
            removeCard(card);
        }
    }

    public void addPlace(Place place) {
        setPlace(place);
        place.getActivities().add(this);
    }

    public void removePlace() {
        getPlace().getActivities().remove(this);
        setPlace(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BusinessActivity)) return false;
        BusinessActivity that = (BusinessActivity) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "BusinessActivity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", cards=" + cards +
                '}';
    }
}
