package api.test.POJOTEMPLATES;

public class BookCart {

    private int bookId;
    private String title;
    private String author;
    private String category;
    private double price;
    private String coverFileName;

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public String getCoverFileName() {
        return coverFileName;
    }

    public BookCart(int bookId, String title, String author, String category, double price, String coverFileName) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.price = price;
        this.coverFileName = coverFileName;
    }

    @Override
    public String toString() {
        return "BookCart{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", coverFileName='" + coverFileName + '\'' +
                '}';
    }
}
