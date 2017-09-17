package hu.qwaevisz.booklight.ejbservice.domain;

public class BookCriteria {

    private String author;
    private String title;
    private BookCategoryStub category;
    private int minimumPrice;
    private int maximumPrice;

    public BookCriteria() {
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

    public int getMinimumPrice() {
        return minimumPrice;
    }

    public void setMinimumPrice(int minimumPrice) {
        this.minimumPrice = minimumPrice;
    }

    public int getMaximumPrice() {
        return maximumPrice;
    }

    public void setMaximumPrice(int maximumPrice) {
        this.maximumPrice = maximumPrice;
    }

    @Override
    public String toString() {
        return "BookCriteriaStub [author=" + author + ", title=" + title + ", category=" + category + ", minimumPrice=" + minimumPrice + ", maximumPrice=" + maximumPrice + "]";
    }

}
