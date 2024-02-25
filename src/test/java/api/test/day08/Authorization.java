package api.test.day08;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.assertEquals;

public class Authorization {
    @BeforeClass
    public void beforeClass() {
        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void t_login() {
        String email = "osmancl@gmail.com";
        String password = "182216118Os";

        Response response = given().accept(ContentType.MULTIPART) //yada ANY
                .and()
                .formParam("email", email)
                .and()
                .formParam("password", password)
                .and()
                .when()
                .post("/allusers/login");
        assertEquals(response.statusCode(), 200);
        //response.prettyPrint();
        String token = response.path("token");
        System.out.println("token = " + token);

    }

    @Test
    public static String getToken() {
        String email = "osmancl@gmail.com";
        String password = "182216118Os";

        Response response = given().accept(ContentType.MULTIPART)
                .and()
                .formParam("email", email)
                .and()
                .formParam("password", password)
                .and()
                .when()
                .post("/allusers/login");

        String token = response.path("token");
        return token;
    }

    @Test
    public static Map<String, Object> getToken(String email, String password) {


        Response response = given().accept(ContentType.MULTIPART)
                .and()
                .formParam("email", email)
                .and()
                .formParam("password", password)
                .and()
                .when()
                .post("/allusers/login");

        String token = response.path("token");

        Map<String, Object> authorization = new HashMap<>();
        authorization.put("token", token);
        return authorization;
    }

}
