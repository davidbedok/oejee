package hu.qwaevisz.inventory.ejbservice.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import hu.qwaevisz.inventory.ejbservice.converter.InternationalInventoryItemConverter;
import hu.qwaevisz.inventory.ejbservice.converter.InventoryItemBeanConverter;
import hu.qwaevisz.inventory.ejbservice.cost.CostService;
import hu.qwaevisz.inventory.ejbservice.domain.InternationalInventoryItemBean;
import hu.qwaevisz.inventory.ejbservice.domain.InventoryItemBean;
import hu.qwaevisz.inventory.ejbservice.domain.PricingStrategy;
import hu.qwaevisz.inventory.ejbservice.event.NotifierEvent;
import hu.qwaevisz.inventory.ejbservice.exception.AdaptorException;
import hu.qwaevisz.inventory.ejbservice.interceptor.CopyrightInterceptor;
import hu.qwaevisz.inventory.ejbservice.interceptor.Logged;
import hu.qwaevisz.inventory.ejbservice.qualifier.Discount;
import hu.qwaevisz.inventory.ejbservice.qualifier.DiscountQualifier;
import hu.qwaevisz.inventory.ejbservice.qualifier.Random;
import hu.qwaevisz.inventory.ejbservice.qualifier.Standard;
import hu.qwaevisz.inventory.ejbservice.service.InventoryConfiguration;
import hu.qwaevisz.inventory.persistence.domain.InventoryType;
import hu.qwaevisz.inventory.persistence.service.InventoryFinder;
import hu.qwaevisz.inventory.persistence.service.InventorySearch;

@Stateless(mappedName = "ejb/inventoryFacade")
public class InventoryFacadeImpl implements InventoryFacade {

	@EJB
	private InventoryFinder finder;

	@Inject
	private InventoryItemBeanConverter converter;

	@Override
	@Interceptors({ CopyrightInterceptor.class })
	public InventoryItemBean getInventoryItem(String reference) throws AdaptorException {
		return this.converter.to(this.finder.get(reference));
	}

	@Logged
	@Override
	@Interceptors({ CopyrightInterceptor.class })
	public List<InventoryItemBean> getInventoryItems(final InventoryType type) throws AdaptorException {
		return this.converter.to(this.finder.list(type));
	}

	@Inject
	private InventorySearch search;

	@Logged
	@Override
	public List<InventoryItemBean> getInventoryItems(final InventoryType type, final String nameTerm) throws AdaptorException {
		return this.converter.to(this.search.list(type, nameTerm));
	}

	@Inject
	@Discount
	private CostService costService;

	@Logged
	@Override
	public InventoryItemBean buyInventoryItem(String reference) throws AdaptorException {
		final InventoryItemBean bean = this.converter.to(this.finder.get(reference));
		bean.setValue(this.costService.getCost(bean.getValue()));
		return bean;
	}

	@Any
	@Inject
	private Instance<CostService> dynamicCostService;

	@Logged
	@Override
	public InventoryItemBean buyInventoryItem(String reference, PricingStrategy pricing) throws AdaptorException {
		final InventoryItemBean bean = this.converter.to(this.finder.get(reference));
		bean.setValue(this.getCostService(pricing).getCost(bean.getValue()));
		return bean;
	}

	private CostService getCostService(PricingStrategy pricing) {
		CostService service = null;
		switch (pricing) {
			case STANDARD:
				service = this.dynamicCostService.select(new AnnotationLiteral<Standard>() {
					private static final long serialVersionUID = 1L;
				}).get();
				break;
			case DISCOUNT:
				service = this.dynamicCostService.select(new DiscountQualifier()).get();
				break;
		}
		return service;
	}

	@Inject
	private InternationalInventoryItemConverter internationalConverter;

	@Logged
	@Override
	public InternationalInventoryItemBean getInternationalInventoryItem(final String reference) throws AdaptorException {
		return this.internationalConverter.to(this.finder.get(reference));
	}

	@Inject
	private Event<NotifierEvent> notifier;

	@Override
	public InternationalInventoryItemBean getInternationalInventoryItemWithEvent(String reference) throws AdaptorException {
		final InternationalInventoryItemBean bean = this.internationalConverter.to(this.finder.get(reference));
		this.notifier.fire(new NotifierEvent(bean));
		return bean;
	}

	@Inject
	@Random
	private Instance<Integer> randomNumber;

	@Logged
	@Override
	public List<Integer> getRandomNumbers(final int quantity) throws AdaptorException {
		final List<Integer> result = new ArrayList<>();
		for (int i = 0; i < quantity; i++) {
			result.add(this.randomNumber.get());
		}
		return result;
	}

	@Inject
	private InventoryConfiguration configuration;

	@Override
	public String getHost() throws AdaptorException {
		return this.configuration.getHost();
	}

}
