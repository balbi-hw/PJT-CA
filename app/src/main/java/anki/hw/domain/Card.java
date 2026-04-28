package anki.hw.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import static jakarta.persistence.FetchType.*;

@Entity
@Table(name = "cards")
@Getter @Setter
public class Card {

    @Id @GeneratedValue
    @Column(name = "card_id")
    private Long id;

    private String word;
    private String meaning;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "deck_id")
    private Deck deck;

    public static Card createCard(String word, String meaning) {
        Card card = new Card();
        card.word = word;
        card.meaning = meaning;
        return card;
    }

    void setDeck(Deck deck) {
        this.deck = deck;
    }

    public void changeContent(String word, String meaning) {
        this.word = word;
        this.meaning = meaning;
    }

}
