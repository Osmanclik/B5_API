package api.test.day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimpleGetRequest {
    String petStoreURL = "https://petstore.swagger.io/v2/";
    String bookCartURL="https://bookcart.azurewebsites.net";

    @Test
    public void basicGetRequest() {

        Response response = RestAssured.get(petStoreURL + "/store/inventory");
        //print status code
        System.out.println("response.statusCode() = " + response.statusCode());
        //print body
        response.prettyPrint();

    }

    @Test
    public void t_getRequestWithBody() {
    /*
    Given accept type is Json
    When user sends a GET request
    Then verify that status code is 200
    And body is Json Format
     */
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get(petStoreURL + "/store/inventory");
        //verify that status code
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json");

    }

    @Test

    public void t_getRequest_WitRestAssuredVerify() {
        // verify using restAssured
        RestAssured
                .given()
                .accept(ContentType.JSON)
                .when()
                .get(petStoreURL+ "/store/inventory")
                .then() //validateableResponse
                .assertThat()
                .statusCode(200)
                .and()
                .assertThat()
                .contentType("application/json");

    }
    @Test
    public void t_getRequest_withContainsMethod(){
        //verify that body has
        Response response=RestAssured
                .given()
                .accept(ContentType.JSON)
                .when()
                .get(bookCartURL+"/api/Book");
        //verify status code
        Assert.assertEquals(response.statusCode(),200);
        //response.prettyPrint();

        //response validation method  --->> contains

        Assert.assertTrue(response.prettyPrint().contains("Jonathan Maberry"));
        //secondway
         Assert.assertTrue(response.body().asString().contains("Jonathan Maberry"));
    }
}
