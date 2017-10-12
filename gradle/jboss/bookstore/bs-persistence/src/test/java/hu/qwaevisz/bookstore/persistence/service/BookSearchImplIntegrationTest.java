package hu.qwaevisz.bookstore.persistence.service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import hu.qwaevisz.bookstore.persistence.entity.Book;
import hu.qwaevisz.bookstore.persistence.exception.PersistenceServiceException;

public class BookSearchImplIntegrationTest {

	private static final String PERSISTENCE_UNIT = "bs-persistence-test-unit";

	private BookSearchImpl object;

	@BeforeClass
	private void setUp() {
		Thread.currentThread().setContextClassLoader(new ClassLoader() {
			@Override
			public Enumeration<URL> getResources(String name) throws IOException {
				if (name.equals("META-INF/persistence.xml")) {
					return Collections.enumeration(Arrays.asList(new File("src/test/resources/persistence.xml").toURI().toURL()));
				}
				return super.getResources(name);
			}
		});

		final EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		final EntityManager entityManager = factory.createEntityManager();

		this.object = new BookSearchImpl();
		this.object.setEntityManager(entityManager);
	}

	@Test(groups = "integration")
	private void testCriteriaIsbnEquals() throws PersistenceServiceException {
		List<Book> books = this.object.find("978-0441172719", null, null, null, null, null, null);
		Assert.assertEquals(books.size(), 1);
		Assert.assertEquals(books.get(0).getTitle(), "Dune");
	}

	@Test(groups = "integration")
	private void testCriteriaAuthorLike() throws PersistenceServiceException {
		List<Book> books = this.object.find(null, "Paulo", null, null, null, null, null);
		Assert.assertEquals(books.size(), 1);
		Assert.assertEquals(books.get(0).getTitle(), "Veronika Decides to Die");
	}

	@AfterClass
	private void tearDown() {
		this.object.getEntityManager().close();
	}

}
