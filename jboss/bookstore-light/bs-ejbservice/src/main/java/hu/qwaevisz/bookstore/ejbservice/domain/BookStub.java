package hu.qwaevisz.bookstore.ejbservice.domain;

public class BookStub {

    private String isbn;
    private String author;
    private String title;
    private BookCategoryStub category;
    private double price;
    private int numberOfPages;

    public BookStub() {
        this(null, null, null, null, 0, 0);
    }

    public BookStub(String isbn, String author, String title, BookCategoryStub category, double price, int numberOfPages) {
        super();
        this.isbn = isbn;
        this.author = author;
        this.title = title;
        this.category = category;
        this.price = price;
        this.numberOfPages = numberOfPages;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BookCategoryStub getCategory() {
        return category;
    }

    public void setCategory(BookCategoryStub category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    @Override
    public String toString() {
        return "BookStub [isbn=" + isbn + ", author=" + author + ", title=" + title + ", category=" + category + ", price=" + price + ", numberOfPages=" + numberOfPages + "]";
    }

}
