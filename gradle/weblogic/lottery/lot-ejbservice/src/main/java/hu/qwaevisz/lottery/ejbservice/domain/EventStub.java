package hu.qwaevisz.lottery.ejbservice.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@XmlRootElement
public class EventStub {

	@XmlElement
	private final String puller;

	@XmlElement
	private final int prizePool;

	@XmlElement
	private final Date date;

	@XmlElement
	private final List<Integer> numbers;

	public EventStub() {
		this(null, 0, null);
	}

	public EventStub(String puller, int prizePool, Date date) {
		super();
		this.puller = puller;
		this.prizePool = prizePool;
		this.date = date;
		this.numbers = new ArrayList<Integer>();
	}

	public void addNumber(int number) {
		this.numbers.add(number);
	}

	public String getPuller() {
		return this.puller;
	}

	public int getPrizePool() {
		return this.prizePool;
	}

	public Date getDate() {
		return this.date;
	}

	public List<Integer> getNumbers() {
		return this.numbers;
	}

	@Override
	public String toString() {
		return "EventStub [puller=" + this.puller + ", prizePool=" + this.prizePool + ", date=" + this.date + ", numbers: "
				+ Arrays.toString(this.numbers.toArray(new Integer[] {})) + "]";
	}

}
