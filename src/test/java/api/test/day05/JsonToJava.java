package api.test.day05;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.*;

public class JsonToJava {
    String exlabUrl = "https://www.krafttechexlab.com/sw/api/v1";

    @Test
    public void t1_allUsersToList() {
        /**CT D05 TC06
         * given accept type is JSON
         * And query param pagesize is 50
         * And query param page is 1
         * When user sends a get request to /allusers/alluser
         * Then status code 200
         * put all response body inside a list by as() method
         * make several verifications
         */
        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .queryParam("page", 1)
                .and()
                .queryParam("pagesize", 50)
                .when()
                .get(exlabUrl+"/allusers/alluser");
        assertEquals(response.statusCode(),200);

        //put all body inside the json body into a list of map
        //convert json Body to Java Collection => De-serialization
        List<Map<String,Object>> allUser=response.as(List.class);
        /* Cannot parse object because no JSON deserializer found in classpath.
            Please put either Jackson (Databind) or Gson in the classpath.
         */

        // verify that the email of first user is "afmercan@gmail.com"
        String actualEmail= (String) allUser.get(0).get("email");
        String ExpectedEmail="afmercan@gmail.com";
        assertEquals(actualEmail,actualEmail);

        // verify that the job of first user is "Manual Tester"
        String actualJob= (String) allUser.get(0).get("job");
        assertEquals(actualJob,"Manual Tester");

        // verify that the second skills of first user is "Java"
        List<String> skills= (List<String>) allUser.get(0).get("skills");
        String actualSkills=skills.get(1);
        assertEquals(actualSkills,"Java");

        // verify that the third id of experience of the first user is 189
        List<Map<String,Object>> experience= (List<Map<String, Object>>) allUser.get(0).get("experience");
        double actualID= (double) experience.get(2).get("id");
        assertEquals(actualID,189.0);

    }
    //HW - https://bookstore.toolsqa.com/Account/v1/User/{UUID}
    //HW -https://bookstore.toolsqa.com/BookStore/v1/Books
}