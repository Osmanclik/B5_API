package api.test.day07;

import api.test.POJOTEMPLATES.PostKraftUser;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class PostMethod {

    private String kraftBaseUrl = "https://www.krafttechexlab.com/sw/api/v1";

    //TASK
    /*
    baseUrl = https://www.krafttechexlab.com/sw/api/v1
    endpoint = /allusers/register
    Given accept type and Content type is JSON
    And request json body is:
    {
    "name": NAME,
    "email": EMAIL,
    "password": PASSWORD
    }
    When user sends POST request
    Then status code 200
    And content type should be application/json
    And json payload/response/body should contain:
    a new generated id that is special for user
    name
    email
    ...
     */

    //FIRST WAY
    //JSON BODY
    @Test
    public void test1() {

        String name = new Faker().name().fullName();
        String email = new Faker().internet().emailAddress();
        String password = new Faker().internet().password();

        String jsonBody = "{\n" +
                "  \"name\": \"" + name + "\",\n" +
                "  \"email\": \"" + email + "\",\n" +
                "  \"password\": \"" + password + "\"\n" +
                "}";

        Response response = RestAssured
                .given()
                .accept(ContentType.JSON)
                .body(jsonBody)
                .when()
                .post(kraftBaseUrl + "/allusers/register");

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotNull(response.path("id"));

        //validate name
        String expectedName = name;
        String actualName = response.path("name");
        Assert.assertEquals(actualName,expectedName);

        //validate email
        String expectedEmail = email;
        String actualEmail = response.path("email");
        Assert.assertEquals(actualEmail,expectedEmail);
    }

    //SECOND WAY
    //MAP
    @Test
    public void test2() {

        String name = new Faker().name().fullName();
        String email = new Faker().internet().emailAddress();
        String password = new Faker().internet().password();

        Map<String,Object> map = new HashMap<>();
        map.put("name", name);
        map.put("email", email);
        map.put("password", password);

        Response response = RestAssured
                .given()
                .accept(ContentType.JSON)
                .body(map)
                .when()
                .post(kraftBaseUrl + "/allusers/register");

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotNull(response.path("id"));

        //validate name
        String expectedName = name;
        String actualName = response.path("name");
        Assert.assertEquals(actualName,expectedName);

        //validate email
        String expectedEmail = email;
        String actualEmail = response.path("email");
        Assert.assertEquals(actualEmail,expectedEmail);
    }

    //THIRD WAY
    //JAVA OBJECT
    @Test
    public void test3() {
        String name = new Faker().name().fullName();
        String email = new Faker().internet().emailAddress();
        String password = new Faker().internet().password();

        PostKraftUser postKraftUser = new PostKraftUser(name, email, password);

        Response response = RestAssured
                .given()
                .accept(ContentType.JSON)
                .body(postKraftUser)
                .when()
                .post(kraftBaseUrl + "/allusers/register");

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotNull(response.path("id"));

        //validate name
        String expectedName = name;
        String actualName = response.path("name");
        Assert.assertEquals(actualName,expectedName);

        //validate email
        String expectedEmail = email;
        String actualEmail = response.path("email");
        Assert.assertEquals(actualEmail,expectedEmail);
    }
}
