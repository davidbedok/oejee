package hu.qwaevisz.magazine.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import hu.qwaevisz.magazine.persistence.entity.trunk.MagazineCategory;
import hu.qwaevisz.magazine.persistence.parameter.MagazineParameter;
import hu.qwaevisz.magazine.persistence.query.MagazineQuery;

@Entity
@Table(name = "magazine")
@NamedQueries(value = { //
		@NamedQuery(name = MagazineQuery.COUNT_BY_REFERENCE, query = "SELECT COUNT(m) FROM Magazine m WHERE m.reference=:" + MagazineParameter.REFERENCE),
		@NamedQuery(name = MagazineQuery.GET_BY_REFERENCE, query = "SELECT m FROM Magazine m WHERE m.reference=:" + MagazineParameter.REFERENCE),
		@NamedQuery(name = MagazineQuery.GET_BY_ID, query = "SELECT m FROM Magazine m WHERE m.id=:" + MagazineParameter.ID),
		@NamedQuery(name = MagazineQuery.GET_ALL, query = "SELECT m FROM Magazine m ORDER BY m.title"),
		@NamedQuery(name = MagazineQuery.GET_ALL_BY_CATEGORY, query = "SELECT m FROM Magazine m WHERE m.category=:" + MagazineParameter.CATEGORY
				+ " ORDER BY m.title"),
		@NamedQuery(name = MagazineQuery.REMOVE_BY_REFERENCE, query = "DELETE FROM Magazine m WHERE m.reference=:" + MagazineParameter.REFERENCE)
		//
})
@SequenceGenerator(name = "generatorMagazine", sequenceName = "magazine_magazine_id_seq", allocationSize = 1)
public class Magazine implements Serializable {

	private static final long serialVersionUID = 1525352421414297015L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generatorMagazine")
	@Column(name = "magazine_id", nullable = false)
	private Long id;

	@Column(name = "magazine_reference", nullable = false)
	private String reference;

	@Column(name = "magazine_publisher", nullable = false)
	private String publisher;

	@Column(name = "magazine_title", nullable = false)
	private String title;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "magazine_magazinecategory_id", nullable = false)
	private MagazineCategory category;

	@Column(name = "magazine_price", nullable = false)
	private Double price;

	@Column(name = "magazine_number_of_pages", nullable = false)
	private Integer numberOfPages;

	public Magazine() {
		this(null, null, null, 0, 0, MagazineCategory.GAMES);
	}

	public Magazine(String reference, String publisher, String title, int numberOfPages, double price, MagazineCategory category) {
		this.reference = reference;
		this.publisher = publisher;
		this.title = title;
		this.numberOfPages = numberOfPages;
		this.price = price;
		this.category = category;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReference() {
		return this.reference;
	}

	public void setReference(String isbn) {
		this.reference = isbn;
	}

	public String getPublisher() {
		return this.publisher;
	}

	public void setPublisher(String author) {
		this.publisher = author;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public MagazineCategory getCategory() {
		return this.category;
	}

	public void setCategory(MagazineCategory category) {
		this.category = category;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getNumberOfPages() {
		return this.numberOfPages;
	}

	public void setNumberOfPages(Integer numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	@Override
	public String toString() {
		return "Magazine [id=" + this.id + ", reference=" + this.reference + ", publisher=" + this.publisher + ", title=" + this.title + ", category="
				+ this.category + ", price=" + this.price + ", numberOfPages=" + this.numberOfPages + "]";
	}

}
