package localhost;

public class Desk {
	public static int winId = -1;
	private int[] scores = new int[3];
	private int[] threeCards = new int[3];// 三张底牌
	private boolean[] canPass = new boolean[3];
	private int[][] playerCards = new int[3][17];
	private int[] allCards = new int[54];// 一副扑克牌
	private int currentId = 0;// 当前操作的人
	// 存储胜负得分信息
	private int result[] = new int[3];
	/**
	 * * -1:重新开始 0:游戏中 1:本局结束
	 */
	public static int multiple = 1;// 当前倍数
	public static int boss = 0;// 地主



	// 初始化游戏
	public void init() {
		allCards = new int[54];
		playerCards = new int[3][17];
		threeCards = new int[3];
		winId = -1;
		multiple = 1;
		currentId = 0;
		for (int i = 0; i < 3; i++) {
			scores[i] = 50;
		}
		for (int i = 0; i < 3; i++) {
			canPass[i] = false;
		}
		for (int i = 0; i < allCards.length; i++) {
			allCards[i] = i;
		}
		CardsManager.shuffle(allCards);
		fapai(allCards);
		chooseBoss();
		CardsManager.sort(playerCards[0]);
		CardsManager.sort(playerCards[1]);
		CardsManager.sort(playerCards[2]);

	}

	// 发牌
	public void fapai(int[] cards) {
		for (int i = 0; i < 51; i++) {
			playerCards[i / 17][i % 17] = cards[i];
		}
		threeCards[0] = cards[51];
		threeCards[1] = cards[52];
		threeCards[2] = cards[53];
	}

	// 随机地主，将三张底牌给地主
	private void chooseBoss() {
		// boss = CardsManager.getBoss();
		currentId = boss;
		int[] diZhuCards = new int[20];
		for (int i = 0; i < 17; i++) {
			diZhuCards[i] = playerCards[boss][i];
		}
		diZhuCards[17] = threeCards[0];
		diZhuCards[18] = threeCards[1];
		diZhuCards[19] = threeCards[2];
		playerCards[boss] = diZhuCards;
	}
}
