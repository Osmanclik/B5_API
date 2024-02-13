package api.test.day03;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ResponseHeaders5 {
    @BeforeClass
    public void beforeClass() {
        RestAssured.baseURI = "https://www.krafttechexlab.com/sw/api/v1/allusers/getbyid/1";
    }

    @Test
    public void headersTest() {
/**
 * HEADERS VALİDATİON
 * Send GET request to kraftexlab
 * Get all information of a specific user whose id is 111
 * Validate that status code is 200
 * Validate that content-length is 606
 * Validate that content-type is "application/json; charset=UTF-8"
 * Validate that response headers have "Date"
 */

        Response response = RestAssured
                .given()
                .accept(ContentType.JSON)
                .when()
                .get("/allusers/getbyid/111");
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.header("Content-Length"), "606");
        Assert.assertEquals(response.contentType(), "application/json; charset=UTF-8");
        Assert.assertEquals(response.header("Content-Type"), "application/json; charset=UTF-8");
        Assert.assertTrue(response.headers().hasHeaderWithName("Date"));
    }
}
