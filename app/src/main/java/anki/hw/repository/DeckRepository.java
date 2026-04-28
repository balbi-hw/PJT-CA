package anki.hw.repository;

import anki.hw.domain.Deck;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DeckRepository {

    private final EntityManager em;

    public void save(Deck deck) {
        if (deck.getId() == null) {
            em.persist(deck);
        } else {
            em.merge(deck);
        }
    }

    public Deck findOne(Long id) {
        return em.find(Deck.class, id);
    }

    public List<Deck> findAll() {
        return em.createQuery("select d from Deck d", Deck.class)
                .getResultList();
    }

    public List<Deck> findAllWithMember() {
        return em.createQuery(
                        "select d from Deck d join fetch d.member", Deck.class)
                .getResultList();
    }

    public List<Deck> findByName(String name) {
        return em.createQuery("select d from Deck d where d.name = :name", Deck.class)
                .setParameter("name", name)
                .getResultList();
    }

    public void delete(Deck deck) {
        em.remove(deck);
    }
}
