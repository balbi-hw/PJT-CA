package anki.hw.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "members")
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Deck> decks = new ArrayList<>();

    public static Member createMember(String username) {
        Member member = new Member();
        member.name = username;
        return member;
    }

    public void addDeck(Deck deck) {
        decks.add(deck);
        deck.setMember(this);
    }

    public void removeDeck(Deck deck) {
        decks.remove(deck);
        deck.setMember(null);
    }

    public void changeName(String name) {
        this.name = name;
    }

}
