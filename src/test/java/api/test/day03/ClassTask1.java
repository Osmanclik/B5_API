package api.test.day03;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ClassTask1 {
    private static String BOOKSTOREBASEURL ="https://bookstore.toolsqa.com";

    @Test
    public void test_GetAllBooks(){
        /**
         * Send GET request to bookstore
         * Endpoint: "/Books"
         * Validate that status code 200
         * Validate that response body is return as JSON format
         * Validate that response body has "Eric Elliott"
         */

        Response response = RestAssured
                .given()
                .accept(ContentType.JSON)
                .when()
                .get(BOOKSTOREBASEURL + "/BookStore/v1/Books");

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=utf-8");
        Assert.assertTrue(response.asString().contains("Eric Elliott"));

    }
}
