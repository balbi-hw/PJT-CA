package service;

import model.WordCard;
import repository.Repository;

import java.util.ArrayList;
import java.util.Scanner;

public class Service {

    private final Repository repository = new Repository();

    public void addCard(Scanner scanner) {
        System.out.print("단어: ");
        String word = scanner.nextLine();

        System.out.print("뜻: ");
        String meaning = scanner.nextLine();

        System.out.print("예문: ");
        String example = scanner.nextLine();

        repository.add(new WordCard(word, meaning, example));
        System.out.println(word + " 카드가 추가되었습니다.");
    }

    public void printList() {
        ArrayList<WordCard> cards = repository.findAll();

        if (cards.isEmpty()) {
            System.out.println("저장된 카드가 없습니다.");
            return;
        }

        for (WordCard card : cards) {
            System.out.println("===========");
            card.printCard();
        }
    }

    public void searchCard(Scanner scanner) {
        ArrayList<WordCard> cards = repository.findAll();

        System.out.print("검색할 단어: ");
        String searchWord = scanner.nextLine();

        WordCard card = repository.findByWord(searchWord);

        if (card != null) {
            System.out.println("검색 결과: ");
            card.printCard();
        } else {
            System.out.println("해당 단어를 찾을 수 없습니다.");
        }
    }

    public void quiz(Scanner scanner) {
        ArrayList<WordCard> cards = repository.findAll();

        if (cards.isEmpty()) {
            System.out.println("카드가 존재하지 않습니다.");
            return;
        }

        int score = 0;

        for (WordCard card : cards) {
            String word = card.getWord();

            System.out.println(word);
            System.out.print("뜻을 입력하세요: ");
            String answer = scanner.nextLine();

            if (answer.equals(card.getMeaning())) {
                System.out.println("정답입니다!");
                score++;
            } else {
                System.out.println("오답입니다. 정답은 " + card.getMeaning() + "입니다.");
                exampleQuery(card, scanner);
            }
        }

        System.out.println("점수 총합: " + score);
    }

    private static void exampleQuery (WordCard card, Scanner scanner){
        System.out.print("예문을 입력하시겠습니까? [Y/n]: ");
        String choice = scanner.nextLine();

        if (choice.equals("Y")) {
            System.out.println("예문을 입력하세요.");
            String example = scanner.nextLine();
            card.setExample(example);

            System.out.println("카드의 정보는 다음과 같습니다.");
            card.printCard();
        }
    }

    public void deleteCard(Scanner sc) {
        ArrayList<WordCard> cards = repository.findAll();

        System.out.print("삭제할 단어를 입력하세요: ");
        String target = sc.nextLine();

        boolean deleted = repository.deleteByWord(target);

        if (deleted) {
            System.out.println(target + " 카드를 삭제했습니다.");
        } else {
            System.out.println("해당 카드가 존재하지 않습니다.");
        }
    }
}
