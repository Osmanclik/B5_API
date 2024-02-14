package api.test.day04;


import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.assertEquals;

public class jsonPath_1_getOneUser {

    @BeforeClass
    public void beforeClass() {
        baseURI = "https://www.krafttechexlab.com/sw/api/v1/allusers/getbyid/1";
    }

    @Test
    public void t_getUser_and_verify_jsonPath() {
         /*
            CT D04 TC01
            When user sends a GET request to /allusers/getbyid/{id}
            Given accept type is json
            And Path param user id is 111

            Then the status Code should be 200
            And Content type json should be "application/json; charset=UTF-8"
            And user's name should be Thomas Eduson
            And user's id should be 111
            And user's email should be thomas@test.com
         */
        Response response = given().accept(ContentType.JSON)
                .and()
                .pathParam("id", 111)
                .when()
                .get("/allusers/getbyid/{id}");
        //Then the status Code should be 200
        assertEquals(response.statusCode(), 200);

        //And Content type json should be "application/json; charset=UTF-8"
        assertEquals(response.contentType(), "application/json; charset=UTF-8");

        //And user's name should be Thomas Eduson
        String actualName = response.path("name[0]");
        String expectedName = "Thomas Eduson";
        assertEquals(actualName,expectedName);
        //And user's id should be 111
        int actualId=response.path("id[0]");
        int expectedId=111;
        assertEquals(actualId,expectedId);

        //And user's email should be thomas@test.com
        String actualEmail=response.path("email[0]");
        String expectedEmail="thomas@test.com";
        assertEquals(actualEmail,expectedEmail);

        //Make same verification with JsonPath
        //create a jsonpath  object with response
        JsonPath jsonPath=response.jsonPath();

        actualId=jsonPath.get("id[0]");
        assertEquals(actualId,expectedId);

        actualName=jsonPath.get("name[0]");
        assertEquals(actualEmail,expectedEmail);

        actualEmail=jsonPath.get("email[0]");
        assertEquals(actualEmail,expectedEmail);

    }
}
