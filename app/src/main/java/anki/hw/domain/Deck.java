package anki.hw.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "decks")
@Getter @Setter
public class Deck {

    @Id @GeneratedValue
    @Column(name = "deck_id")
    private Long id;

    private String name;

    private int size;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "deck", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Card> cards = new ArrayList<>();

    public static Deck createDeck(String name) {
        Deck deck = new Deck();
        deck.name = name;
        deck.size = 0;
        return deck;
    }

    void setMember(Member member) {
        this.member = member;
    }

    public void addCard(Card card) {
        cards.add(card);
        card.setDeck(this);
        this.size = cards.size();
    }

    public void removeCard(Card card) {
        cards.remove(card);
        card.setDeck(null);
        this.size = cards.size();
    }

    public void changeInfo(String name) {
        this.name = name;
    }
}
