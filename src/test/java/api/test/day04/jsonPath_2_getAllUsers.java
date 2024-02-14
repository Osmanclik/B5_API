package api.test.day04;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.*;

public class jsonPath_2_getAllUsers {
    @BeforeClass
    public void beforeClass() {
        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void t_getAllUsers_and_verify_with_jsonPath() {
         /*
            CT D04 TC02
            Given accept type is json
            When user sends a GET request to /allusers/alluser
            Then the status Code should be 200
            And Content type json should be "application/json; charset=UTF-8"
         */
        Response response = given().accept(ContentType.JSON)
                .and()
                .log().all()
                .and()
                .queryParam("pagesize", 5)
                .and()
                .queryParam("page", 1)
                .when()
                .get("/allusers/alluser");
        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json; charset=UTF-8");        Assert.assertEquals(response.header("Content-Type"),"application/json; charset=UTF-8");

        JsonPath jsonPath=response.jsonPath();

        //verify that first id is equal to 1
        int actualId = jsonPath.get("id[0]");
        int expectedId=1;
        assertEquals(actualId,expectedId);

        //verify that fifth id is equal to 33
        int fifthActualId = jsonPath.get("id[4]");
        int fifthExpectedId=33;
        assertEquals(fifthActualId,fifthExpectedId);

        //verify that fifth nae is equal to Sebastian
        String fifthActualName=jsonPath.get("name[4]");
        String fifthExpectedName="Sebastian";
        assertEquals(fifthActualName,fifthExpectedName);

        //verify that ids are 1,5,24,29,33
        System.out.println("jsonPath.getList(\"id\") = " + jsonPath.getList("id"));
        List<Integer> actualID_List = jsonPath.getList("id");
        List<Integer> expectedID_List=new ArrayList<>();
        expectedID_List.add(1);
        expectedID_List.add(5);
        expectedID_List.add(24);
        expectedID_List.add(29);
        expectedID_List.add(33);
        assertEquals(actualID_List,expectedID_List);
        //second way for espected list
        List<Integer> expectedID_List2=new ArrayList<>(Arrays.asList(1,5,24,29,33));
        assertEquals(actualID_List,expectedID_List2);

        //iterate all ids and print
        for (Integer integer : actualID_List){
            System.out.println("integer = " + integer);
        }

        //verify that first skills of first user is PHP
        //firstway

        String actualFirstSkillOfFirstUser=jsonPath.getString("skills[0][0]");
        String expectedFirstSkillOfFirstUser="PHP";
        assertEquals(actualFirstSkillOfFirstUser,expectedFirstSkillOfFirstUser);
        //second way
       List<String> firstUserSkills= jsonPath.get("skills[0]");
        System.out.println("firstUserSkills = " + firstUserSkills);
        String actualFirstUserSkillOfFirstUserWithJsonPath=firstUserSkills.get(0);
        assertEquals(actualFirstUserSkillOfFirstUserWithJsonPath,expectedFirstSkillOfFirstUser);


        // verify that first user's school is "School or Bootcamp"
        //first way
        Map<String,Object> map=jsonPath.get("education[0][0]");
        System.out.println("map.get(\"school\") = " + map.get("school"));
        String actualEducation= (String) map.get("school");
        String expectedEducation="School or Bootcamp";
        assertEquals(actualEducation,expectedEducation);

        //second way
        String alternativeActualEdu=jsonPath.get("education[0].school[0]");
        System.out.println("alternativeActualEdu = " + alternativeActualEdu);
        assertEquals(alternativeActualEdu,expectedEducation);

        //third way
        List<Map<String,Object>> listOfMap=jsonPath.getList("education[0]");
        System.out.println("listOfMap.get(0).get(\"school\") = " + listOfMap.get(0).get("school"));
        String thirdWayOfActualEdu= (String) listOfMap.get(0).get("school");
        assertEquals(thirdWayOfActualEdu,expectedEducation);

        System.out.println("listOfMap.get(3).get(\"date\") = " + listOfMap.get(3).get("date"));


    }
      /*
            HWT D04 TC03
            Given accept type json
            When user sends a get request to https://bookstore.toolsqa.com/BookStore/v1/Books
            Then status code should be 200
            And content type should be application/json; charset=utf-8
            And the first book isbn should be 9781449325862
            And the first book publisher should be O'Reilly Media

            example with path method and jsonPath method
     */

    }

