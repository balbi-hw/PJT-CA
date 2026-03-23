package foundation;

import java.util.Scanner;

public class CardMain {

    public static void main(String[] args) {
        //카드 생성
        WordCard card1 = new WordCard("apple", "사과");
        WordCard card2 = new WordCard("banana", "바나나");
        WordCard card3 = new WordCard("cherry", "체리");

        WordCard[] cards = {card1, card2, card3};

        Scanner scanner = new Scanner(System.in);

        int score = 0;

        for (int i = 0; i < cards.length; i++) {
            String word = cards[i].getWord();

            System.out.println(word);
            System.out.print("뜻을 입력하세요: ");
            String answer = scanner.nextLine();

            if (answer.equals(cards[i].getMeaning())) {
                System.out.println("정답입니다!");
                score ++;
            } else {
                System.out.println("오답입니다. 정답은 " + cards[i].getMeaning() + "입니다.");
                System.out.print("예문을 입력하시겠습니까? [Y/n]: ");
                String choice = scanner.nextLine();

                if (choice.equals("Y")) {
                    System.out.println("예문을 입력하세요.");
                    String example = scanner.nextLine();
                    cards[i].setExample(example);

                    System.out.println("카드의 정보는 다음과 같습니다.");
                    cards[i].printCard();
                }
            }
        }

        System.out.println("점수 합계: " + score);
    }
}
