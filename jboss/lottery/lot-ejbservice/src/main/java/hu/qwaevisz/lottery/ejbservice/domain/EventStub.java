package hu.qwaevisz.lottery.ejbservice.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class EventStub {

	private final String puller;
	private final int prizePool;
	private final Date date;
	private final List<Integer> numbers;

	public EventStub(String puller, int prizePool, Date date) {
		super();
		this.puller = puller;
		this.prizePool = prizePool;
		this.date = date;
		this.numbers = new ArrayList<>();
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
