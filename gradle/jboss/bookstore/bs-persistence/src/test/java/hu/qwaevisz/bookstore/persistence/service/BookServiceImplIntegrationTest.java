package hu.qwaevisz.bookstore.persistence.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import hu.qwaevisz.bookstore.persistence.entity.Book;
import hu.qwaevisz.bookstore.persistence.entity.trunk.BookCategory;
import hu.qwaevisz.bookstore.persistence.exception.PersistenceServiceException;

public class BookServiceImplIntegrationTest {

	private static final String PERSISTENCE_UNIT = "bs-persistence-test-unit";

	private static final String NEW_BOOK_ISBN = "42-NEW-BOOK-ISBN";

	private BookServiceImpl object;

	@BeforeClass
	private void setUp() {
		// Thread.currentThread().setContextClassLoader(new ClassLoader() {
		// @Override
		// public Enumeration<URL> getResources(String name) throws IOException {
		// if (name.equals("META-INF/persistence.xml")) {
		// return Collections.enumeration(Arrays.asList(new
		// File("src/test/resources/persistence.xml").toURI().toURL()));
		// }
		// return super.getResources(name);
		// }
		// });

		final EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		final EntityManager entityManager = factory.createEntityManager();

		this.object = new BookServiceImpl();
		this.object.setEntityManager(entityManager);
	}

	@Test(groups = "integration")
	private void readSampleBookFromDatabase() throws PersistenceServiceException {
		final Book book = this.object.read("978-0441172719");
		this.assertBook(book, "978-0441172719", "Frank Herbert", "Dune", BookCategory.SCIFI, 896, 3500d);
	}

	private void assertBook(final Book book, final String isbn, final String author, final String title, final BookCategory category,
			final Integer numberOfPages, final double price) {
		Assert.assertEquals(book.getIsbn(), isbn);
		Assert.assertEquals(book.getAuthor(), author);
		Assert.assertEquals(book.getTitle(), title);
		Assert.assertEquals(book.getCategory(), category);
		Assert.assertEquals(book.getNumberOfPages(), numberOfPages);
		Assert.assertEquals(book.getPrice(), price);
	}

	@Test(groups = "integration")
	private void createABook() throws PersistenceServiceException {
		if (this.object.exists(NEW_BOOK_ISBN)) {
			this.object.getEntityManager().getTransaction().begin();
			this.object.delete(NEW_BOOK_ISBN);
			this.object.getEntityManager().getTransaction().commit();
		}

		this.object.getEntityManager().getTransaction().begin();
		this.object.create(NEW_BOOK_ISBN, "Lorem Ipsum", "Sample book", 142, 999, BookCategory.HISTORICAL);
		this.object.getEntityManager().getTransaction().commit();

		final Book book = this.object.read(NEW_BOOK_ISBN);
		this.assertBook(book, NEW_BOOK_ISBN, "Lorem Ipsum", "Sample book", BookCategory.HISTORICAL, 142, 999);
	}

	@AfterClass
	private void tearDown() {
		this.object.getEntityManager().close();
	}

}
