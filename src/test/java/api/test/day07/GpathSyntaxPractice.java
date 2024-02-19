package api.test.day07;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class GpathSyntaxPractice {
    private String kraftBaseUrl="https://www.krafttechexlab.com/sw/api/v1";

    @Test
    public void gpathPractice(){

        Response response = RestAssured
                .given().accept(ContentType.JSON)
                .queryParam("page", 1)
                .queryParam("pagesize", 50)
                .when()
                .get(kraftBaseUrl + "/allusers/alluser");

        //get name of first user
        String nameOffirstUser=response.path("name[0]");
        System.out.println("nameOffirstUser = " + nameOffirstUser);

        //get id of second user
        Integer secondUserId = response.path("id[1]");
        System.out.println("secondUserId = " + secondUserId);

        //get email of third user
        JsonPath jsonPath = response.jsonPath();
        String email = jsonPath.getString("email[2]");
        System.out.println("email = " + email);

        //get all names
        List<String> list = response.path("name");
       // System.out.println("list = " + list);
        Assert.assertEquals(list.size(),50);

        //get all names
        List<String > name = jsonPath.getList("name");
       // System.out.println("name = " + name);

        //get skills of first user
        List<String> skills= response.path("skills[0]");
        System.out.println("skills = " + skills);

        //second way
        List<String> skills_1 = jsonPath.getList("skills[0]");
        System.out.println("skills_1 = " + skills_1);

        //get first skill of first user
        String arraySkill1 = response.path("skills[0][0]");
        System.out.println("arraySkill1 = " + arraySkill1);

        //get second skills of first user
        String arraySkill2 = response.path("skills[0][1]");
        System.out.println("arraySkill2 = " + arraySkill2);

        // get education of first user
        List<Map<String,Object>> educationList = response.path("education[0]");
        Integer id1 = (Integer) educationList.get(0).get("id");
        System.out.println("id1 = " + id1);

        //get second id of education of first user


    }
}
