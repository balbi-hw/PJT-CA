package repository;

import model.WordCard;

import java.util.ArrayList;

public class Repository {
    ArrayList<WordCard> cards = new ArrayList<>();

    public void add(WordCard wordCard) {
        cards.add(wordCard);
    }

    public ArrayList<WordCard> findAll() {
        return cards;
    }

    public WordCard findByWord(String searchWord) {
        for (WordCard card : cards) {
            if (card.getWord().equals(searchWord)) {
                return card;
            }
        }

        return null;
    }


    public Boolean deleteByWord(String target) {
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getWord().equals(target)) {
                cards.remove(i);
                return true;
            }
        }
        return false;
    }
}
