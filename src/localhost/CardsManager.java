package localhost;

import java.util.Random;

public class CardsManager {

	public static Random rand = new Random();

	public static boolean inRect(int x, int y, int rectX, int rectY, int rectW, int rectH) {
		// 必须要有等号，否则触摸在相邻牌的同一边上会出错
		if (x <= rectX || x >= rectX + rectW || y <= rectY || y >= rectY + rectH) {
			return false;
		}
		return true;
	}

	// 洗牌，cards[0]~cards[53]表示54张牌:3333444455556666...KKKKAAAA2222小王大王
	public static void shuffle(int[] cards) {
		int len = cards.length;
		// 对于54张牌中的任何一张，都随机找一张和它互换，将牌顺序打乱。
		for (int l = 0; l < len; l++) {
			int des = rand.nextInt(54);
			int temp = cards[l];
			cards[l] = cards[des];
			cards[des] = temp;
		}
	}

	// 随机选择地主
	public static int getBoss() {
		return rand.nextInt(3);
	}

	// 对牌进行从大到小排序，冒泡排序
	public static void sort(int[] cards) {
		for (int i = 0; i < cards.length; i++) {
			for (int j = i + 1; j < cards.length; j++) {
				if (cards[i] < cards[j]) {
					int temp = cards[i];
					cards[i] = cards[j];
					cards[j] = temp;
				}
			}
		}
	}

	public static int getImageRow(int poke) {
		return poke / 4;
	}

	public static int getImageCol(int poke) {
		return poke % 4;
	}

	// 获取某张牌的大小
	public static int getCardNumber(int card) {
		// 当扑克值为52时，是小王
		if (card == 52) {
			return 16;
		}
		// 当扑克值为53时，是大王
		if (card == 53) {
			return 17;
		}
		// 其它情况下返回相应的值(3,4,5,6,7,8,9,10,11(J),12(Q),13(K),14(A),15(2))
		return getImageRow(card) + 3;
	}

	// 判断是否单顺
	public static boolean isDanShun(int[] cards) {
		int start = getCardNumber(cards[0]);
		// 单顺最大一张不能大于A
		if (start > 14) {
			return false;
		}
		int next;
		for (int i = 1; i < cards.length; i++) {
			next = getCardNumber(cards[i]);
			if (start - next != 1) {
				return false;
			}
			start = next;
		}
		return true;
	}

	// 判断是否双顺
	public static boolean isShuangShun(int[] cards) {
		int start = getCardNumber(cards[0]);
		// 双顺最大一张不能大于A
		if (start > 14) {
			return false;
		}
		// 奇数张牌不可能是双顺
		if (cards.length % 2 != 0) {
			return false;
		}
		int next;
		for (int i = 2; i < cards.length; i += 2) {
			next = getCardNumber(cards[i]);
			if (start != getCardNumber(cards[i - 1])) {
				return false;
			}
			if (next != getCardNumber(cards[i + 1])) {
				return false;
			}
			if (start - next != 1) {
				return false;
			}
			start = next;
		}
		return true;
	}

	// 判断是否三顺
	public static boolean isSanShun(int[] cards) {
		int start = getCardNumber(cards[0]);
		// 三顺最大一张不能大于A
		if (start > 14) {
			return false;
		}
		// 三顺牌是3的倍数
		if (cards.length % 3 != 0) {
			return false;
		}
		int next;
		for (int i = 3; i < cards.length; i += 3) {
			next = getCardNumber(cards[i]);
			if (start != getCardNumber(cards[i - 1])) {
				return false;
			}
			if (next != getCardNumber(cards[i + 2])) {
				return false;
			}
			if (start - next != 1) {
				return false;
			}
			start = next;
		}
		return true;
	}
}
