package anki.hw.repository;

import anki.hw.domain.Card;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CardRepository {

    private final EntityManager em;

    public void save(Card card) {
        if (card.getId() == null) {
            em.persist(card);
        } else {
            em.merge(card);
        }
    }

    public Card findOne(Long id) {
        return em.find(Card.class, id);
    }

    public List<Card> findByWord(String word) {
        return em.createQuery("select c from Card c where c.word = :word", Card.class)
                .setParameter("word", word)
                .getResultList();
    }

    public List<Card> findAll() {
        return em.createQuery("select c from Card c", Card.class)
                .getResultList();
    }

    public void delete(Card card) {
        em.remove(card);
    }

    public List<Card> findByDeckId(Long deckId) {
        return em.createQuery(
                        "select c from Card c join fetch c.deck d where d.id = :deckId", Card.class)
                .setParameter("deckId", deckId)
                .getResultList();
    }

}
