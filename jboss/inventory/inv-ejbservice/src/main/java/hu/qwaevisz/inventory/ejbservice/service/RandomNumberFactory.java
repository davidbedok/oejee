package hu.qwaevisz.inventory.ejbservice.service;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import hu.qwaevisz.inventory.ejbservice.interceptor.Logged;
import hu.qwaevisz.inventory.ejbservice.qualifier.MaxNumber;
import hu.qwaevisz.inventory.ejbservice.qualifier.Random;

@ApplicationScoped
public class RandomNumberFactory {

	private final java.util.Random random = new java.util.Random(System.currentTimeMillis());

	@Inject
	@MaxNumber
	private int maxNumber;

	@Logged
	@Produces
	@Random
	public int next() {
		return this.random.nextInt(this.maxNumber);
	}

}
