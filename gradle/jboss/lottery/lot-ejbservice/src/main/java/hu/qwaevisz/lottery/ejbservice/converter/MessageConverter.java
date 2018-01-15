package hu.qwaevisz.lottery.ejbservice.converter;

import javax.ejb.Local;

@Local
public interface MessageConverter {

	int[] toNumbers(String content);

}
