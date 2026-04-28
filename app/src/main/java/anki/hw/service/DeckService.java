package anki.hw.service;

import anki.hw.domain.Deck;
import anki.hw.domain.Member;
import anki.hw.repository.DeckRepository;
import anki.hw.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DeckService {

    private final DeckRepository deckRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long createDeck(Long memberId, String deckName) {
        Member member = memberRepository.findOne(memberId);
        Deck deck = Deck.createDeck(deckName);
        member.addDeck(deck);
        deckRepository.save(deck);
        return deck.getId();
    }

    public Deck findOne(Long deckId) {
        return deckRepository.findOne(deckId);
    }

    public List<Deck> findDecks() {
        return deckRepository.findAll();
    }

    public List<Deck> findDecksWithMember() {
        return deckRepository.findAllWithMember();
    }

    @Transactional
    public void updateDeck(Long deckId, String name) {
        Deck deck = deckRepository.findOne(deckId);
        deck.changeInfo(name);
    }

    @Transactional
    public void deleteDeck(Long deckId) {
        Deck deck = deckRepository.findOne(deckId);
        deckRepository.delete(deck);
    }
}
