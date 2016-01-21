package hu.qwaevisz.lottery.jmsclient.generator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class LotteryMessageGenerator {

	private static final int NUMBER_OF_NUMBERS = 5;
	private static final int MAXIMUM_VALUE = 90;

	private final Random random;

	public LotteryMessageGenerator(Random random) {
		this.random = random;
	}

	public String generate() {
		final List<Integer> numbers = new ArrayList<Integer>();
		for (int i = 0; i < NUMBER_OF_NUMBERS; i++) {
			int current = 0;
			do {
				current = this.random.nextInt(MAXIMUM_VALUE) + 1;
			} while (numbers.contains(current));
			numbers.add(current);
		}
		return Arrays.toString(numbers.toArray());
	}

}
