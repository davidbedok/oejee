package hu.qwaevisz.lottery.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import hu.qwaevisz.lottery.persistence.query.EventQuery;

@Entity
@Table(name = "event")
@NamedQueries(value = { //
		@NamedQuery(name = EventQuery.GET_ALL, query = "SELECT e FROM Event e JOIN FETCH e.numbers ORDER BY e.prizePool"),
		@NamedQuery(name = EventQuery.GET_LATEST, query = "SELECT e FROM Event e JOIN FETCH e.numbers ORDER BY e.date DESC")
		//
})
public class Event implements Serializable {

	private static final long serialVersionUID = 8643163200023485701L;

	@Id
	@SequenceGenerator(name = "generatorEvent", sequenceName = "event_event_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generatorEvent")
	@Column(name = "event_id", nullable = false)
	private Long id;

	@Column(name = "event_puller", nullable = false)
	private String puller;

	@Column(name = "event_prizepool", nullable = false)
	private Integer prizePool;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "event_date", nullable = false)
	private Date date;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = DrawnNumber.class, mappedBy = "event")
	private Set<DrawnNumber> numbers;

	public Event() {
		this(null, null);
	}

	public Event(String puller, Integer prizePool) {
		this.puller = puller;
		this.prizePool = prizePool;
		this.date = new Date();
		this.numbers = new HashSet<DrawnNumber>();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPuller() {
		return this.puller;
	}

	public void setPuller(String puller) {
		this.puller = puller;
	}

	public Integer getPrizePool() {
		return this.prizePool;
	}

	public void setPrizePool(Integer prizePool) {
		this.prizePool = prizePool;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Set<DrawnNumber> getNumbers() {
		return this.numbers;
	}

	public void setNumbers(Set<DrawnNumber> numbers) {
		this.numbers = numbers;
	}

	public void addNumber(Integer number) {
		this.numbers.add(new DrawnNumber(number, this));
	}

	@Override
	public String toString() {
		return "Event [id=" + this.id + ", puller=" + this.puller + ", prizePool=" + this.prizePool + ", date=" + this.date + "]";
	}

}
