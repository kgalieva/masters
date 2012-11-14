import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/* The idea was taken from Peter Norvig's course CS212 at {@link http://www.udacity.com}
 * */
public class Poker {

	class Hand {
		private int[] cards;
		private int rank;

		public int[] getCards() {
			return cards;
		}

		public void setCards(int[] cards) {
			this.cards = cards;
		}

		public int getRank() {
			return rank;
		}

		public void setRank(int rank) {
			this.rank = rank;
		}
	}

	public static List<String[]> poker(List<String[]> strings) {
		// TODO Реализовать метод, который возвращает выигрышные комбинации карт
		// из переданного набора комбинаций.
		return null;
	}

	public static int[] cardRanks(String[] hand) {
		// TODO заменяем буквы числами и сортируем карты в порядке убывания
		return null;
	}

	public static Hand handRanks(String[] hand) {
		// TODO метод, который вычисляет число очков(от 0 до 8) по данной комбинации карт
		return null;
	}

	/*
	 * реализовать методы, которые проверяют тип комбинации карт(Роял-флаш,
	 * Стрейт-флаш, Фул-хаус и т.д.) Например:
	 */
	public static boolean isStaight(int[] hand) {
		for (int i = 0; i < hand.length - 1; i++) {
			if (hand[i] != hand[i + 1] - 1) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Test cases
		String[] sf1 = { "6C", "7C", "8C", "9C", "TC" }; // Straight Flush
		String[] sf2 = { "6D", "7D", "8D", "9D", "TD" }; // Straight Flush
		String[] fk = { "9D", "9H", "9S", "9C", "7D" }; // Four of a Kind
		String[] fh = { "TD", "TC", "TH", "7C", "7D" }; // Full House
		List<String[]> hands = new ArrayList<String[]>();
		hands.add(sf1);
		hands.add(sf2);
		hands.add(fk);
		hands.add(fh);
		List<String[]> winners = poker(hands);
		assertTrue(winners.contains(sf1));
		assertTrue(winners.contains(sf1));
		assertFalse(winners.contains(fk));
		assertFalse(winners.contains(fh));
		// TODO дописать свои тесты для всех реализованых методов
	}

}
