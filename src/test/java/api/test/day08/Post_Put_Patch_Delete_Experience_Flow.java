package api.test.day08;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static api.test.day08.Authorization.*;
import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.assertEquals;

public class Post_Put_Patch_Delete_Experience_Flow {
    static String email="eddiem@kraft.com";
    static String password="eddiem12";
    static Response response;
    static int experienceID;
    @BeforeClass
    public void beforeClass(){
        baseURI="https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test  (priority = 1)
    public void t_addExperience() {
        String body="{\n" +
                "  \"job\": \"SDET\",\n" +
                "  \"company\": \"TechKraft\",\n" +
                "  \"location\": \"Istanbul\",\n" +
                "  \"fromdate\": \"2023-01-11\",\n" +
                "  \"todate\": \"2024-01-11\",\n" +
                "  \"current\": \"false\",\n" +
                "  \"description\": \"Description\"\n" +
                "}";
        Response response = given().accept(ContentType.JSON)
                .and()
                .header("token", getToken())
                .and()
                .body(body)
                .when().log().all()
                .post("/experience/add")
                .prettyPeek();

        assertEquals(response.statusCode(),200);
        experienceID=response.path("id");
    }

    /*
            {
          "job": "Junior Developer",
          "company": "Kraft Techex",
          "location": "USA",
          "fromdate": "2020-01-11",
          "todate": "2020-08-11",
          "current": "false",
          "description": "Description"
}
     */

    @Test (priority = 2)
    public void t_updateExperience_PUT() {
        Map<String,Object> experienceBody=new HashMap<>();
        experienceBody.put("job","SDET");
        experienceBody.put("company","Google");
        experienceBody.put("location","Boston");
        experienceBody.put("fromdate","2016-01-11");
        experienceBody.put("todate","2016-01-11");
        experienceBody.put("current","false");
        experienceBody.put("description","SDET");

        response=given().accept(ContentType.JSON)
                .and()
                .headers(getToken(email,password))
                .and()
                .queryParam("id",experienceID)
                .and()
                .body(experienceBody)
                .when().log().all()
                .put("/experience/updateput")
                .prettyPeek();

    }

    @Test (priority = 3)
    public void t_updateExperience_Patch() {
        Map<String,Object> experienceBody=new HashMap<>();
        experienceBody.put("company","Facebook");
        experienceBody.put("location","NY");

        response=given().accept(ContentType.JSON)
                .and()
                .pathParam("id",experienceID)
                .and()
                .headers(getToken(email,password))
                .and()
                .body(experienceBody)
                .when().log().all()
                .patch("experience/updatepatch/{id}")
                .prettyPeek();
        assertEquals(response.statusCode(),200);
    }

    @Test (priority = 4)
    public void t_deleteExperience() {
        response=given().accept(ContentType.JSON)
                .and()
                .pathParam("id",experienceID)
                .and()
                .headers(getToken(email,password))
                .when()
                .delete("experience/delete/{id}")
                .prettyPeek();
        assertEquals(response.statusCode(),200);
    }
}
