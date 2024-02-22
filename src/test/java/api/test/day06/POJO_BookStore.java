package api.test.day06;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class POJO_BookStore {

    private static String BOOKSTOREBASEURL = "https://bookstore.toolsqa.com";

    @Test
    public void getAllBooks(){
        /**
         * CONVERT JSON OBJECT TO POJO(PLAIN OLD JAVA OBJECT--CUSTOM JAVA CLASS) WITH AS() METHOD (DE-SERILIZIATION)
         * Send GET request to bookstore to get all books
         * Convert response(json body) to custom java class
         * Make validations...
         */


        Response response = RestAssured
                .given().accept(ContentType.JSON)
                .when()
                .get(BOOKSTOREBASEURL + "/BookStore/v1/Books");

        BookStore bookStore = response.as(BookStore.class);

        List<Book> books = bookStore.getBooks();


        //validate that first isbn is "9781449325862"
        Book book = books.get(0);
        String firstExpectedIsbn="9781449325862";
        String firstActualIsbn= book.getIsbn();
        Assert.assertEquals(firstActualIsbn,firstExpectedIsbn);

        //validate that title of last book is "Understanding ECMAScript 6"
        Book book1=books.get(books.size()-1);
        String lastExpectedTitle="Understanding ECMAScript 6";
        String lastActualTitle= book1.getTitle();
        Assert.assertEquals(lastActualTitle,lastExpectedTitle);

    }
}
