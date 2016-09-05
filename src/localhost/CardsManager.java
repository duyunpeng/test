package localhost;

import java.util.Random;

public class CardsManager {

	public static Random rand = new Random();

	public static boolean inRect(int x, int y, int rectX, int rectY, int rectW, int rectH) {
		// ����Ҫ�еȺţ��������������Ƶ�ͬһ���ϻ����
		if (x <= rectX || x >= rectX + rectW || y <= rectY || y >= rectY + rectH) {
			return false;
		}
		return true;
	}

	// ϴ�ƣ�cards[0]~cards[53]��ʾ54����:3333444455556666...KKKKAAAA2222С������
	public static void shuffle(int[] cards) {
		int len = cards.length;
		// ����54�����е��κ�һ�ţ��������һ�ź�������������˳����ҡ�
		for (int l = 0; l < len; l++) {
			int des = rand.nextInt(54);
			int temp = cards[l];
			cards[l] = cards[des];
			cards[des] = temp;
		}
	}

	// ���ѡ�����
	public static int getBoss() {
		return rand.nextInt(3);
	}

	// ���ƽ��дӴ�С����ð������
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

	// ��ȡĳ���ƵĴ�С
	public static int getCardNumber(int card) {
		// ���˿�ֵΪ52ʱ����С��
		if (card == 52) {
			return 16;
		}
		// ���˿�ֵΪ53ʱ���Ǵ���
		if (card == 53) {
			return 17;
		}
		// ��������·�����Ӧ��ֵ(3,4,5,6,7,8,9,10,11(J),12(Q),13(K),14(A),15(2))
		return getImageRow(card) + 3;
	}

	// �ж��Ƿ�˳
	public static boolean isDanShun(int[] cards) {
		int start = getCardNumber(cards[0]);
		// ��˳���һ�Ų��ܴ���A
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

	// �ж��Ƿ�˫˳
	public static boolean isShuangShun(int[] cards) {
		int start = getCardNumber(cards[0]);
		// ˫˳���һ�Ų��ܴ���A
		if (start > 14) {
			return false;
		}
		// �������Ʋ�������˫˳
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

	// �ж��Ƿ���˳
	public static boolean isSanShun(int[] cards) {
		int start = getCardNumber(cards[0]);
		// ��˳���һ�Ų��ܴ���A
		if (start > 14) {
			return false;
		}
		// ��˳����3�ı���
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
