package hu.qwaevisz.lottery.persistence.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import hu.qwaevisz.lottery.persistence.query.DrawnNumberQuery;

@Entity
@Table(name = "drawnnumber")
@NamedQueries(value = { //
		@NamedQuery(name = DrawnNumberQuery.GET_ALL, query = "SELECT dn FROM DrawnNumber dn ORDER BY dn.event.id")
		//
})
public class DrawnNumber implements Serializable {

	private static final long serialVersionUID = 7213394173873548468L;

	@Id
	@SequenceGenerator(name = "generatorDrawnNumber", sequenceName = "drawnnumber_drawnnumber_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generatorDrawnNumber")
	@Column(name = "drawnnumber_id", nullable = false, updatable = false, insertable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "drawnnumber_event_id", referencedColumnName = "event_id", nullable = false)
	private Event event;

	@Column(name = "drawnnumber_value", nullable = false)
	private Integer value;

	public DrawnNumber() {
		this(null, null);
	}

	public DrawnNumber(Integer value, Event event) {
		this.value = value;
		this.event = event;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Event getEvent() {
		return this.event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Integer getValue() {
		return this.value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "DrawnNumber [id=" + this.id + ", event=" + this.event + ", value=" + this.value + "]";
	}

}
