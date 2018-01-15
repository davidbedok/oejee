package hu.qwaevisz.lottery.ejbservice.converter;

import java.util.Arrays;

import javax.ejb.Stateless;

import org.apache.log4j.Logger;

@Stateless
public class MessageConverterImpl implements MessageConverter {

	private static final Logger LOGGER = Logger.getLogger(MessageConverterImpl.class);

	@Override
	public int[] toNumbers(String content) {
		content = content.replace("[", "");
		content = content.replace("]", "");
		final String[] numbersStr = content.split(",");
		final int[] numbers = new int[numbersStr.length];
		int i = 0;
		for (final String numberStr : numbersStr) {
			numbers[i++] = Integer.valueOf(numberStr.trim());
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Parsed content: " + Arrays.toString(numbers));
		}
		return numbers;
	}

}
