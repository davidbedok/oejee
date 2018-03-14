package hu.qwaevisz.inventory.ejbservice.event;

import java.io.Serializable;

import hu.qwaevisz.inventory.ejbservice.domain.InternationalInventoryItemBean;

public class NotifierEvent implements Serializable {

	private static final long serialVersionUID = 1L;

	private InternationalInventoryItemBean bean;

	public NotifierEvent() {
	}

	public NotifierEvent(InternationalInventoryItemBean bean) {
		this.bean = bean;
	}

	public InternationalInventoryItemBean getBean() {
		return this.bean;
	}

	@Override
	public String toString() {
		return "NotifierEvent [bean=" + this.bean + "]";
	}

}
