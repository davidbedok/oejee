package hu.qwaevisz.inventory.ejbservice.service;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import hu.qwaevisz.inventory.ejbservice.qualifier.MaxNumber;

@ApplicationScoped
public class MaxNumberFactory {

	private static final int MAX_VALUE = 100;

	@Produces
	@MaxNumber
	public int getMaxNumber() {
		return MAX_VALUE;
	}

}
