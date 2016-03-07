package hu.qwaevisz.shopping.loader.util;

import java.sql.Date;
import java.util.Random;

public class ContentGenerator {

	private final Random random;

	public ContentGenerator(final Random random) {
		this.random = random;
	}

	public Random getRandom() {
		return this.random;
	}

	public Date generateDate() {
		return new Date(this.random.nextInt(20) + 95, this.random.nextInt(12), this.random.nextInt(28));
	}

	public int generateQuantity() {
		return this.random.nextInt(2) + 1;
	}

	public int generateNumberOfitems() {
		return this.random.nextInt(15) + 5;
	}

	public String generateSentence() {
		final int numberOfWords = this.random.nextInt(5) + 2;
		final StringBuilder sentence = new StringBuilder();
		for (int i = 0; i < numberOfWords; i++) {
			final char[] word = new char[this.random.nextInt(8) + 3];
			for (int j = 0; j < word.length; j++) {
				word[j] = (char) ('a' + this.random.nextInt(26));
			}
			sentence.append(new String(word)).append(" ");
		}
		return sentence.toString();
	}

}
