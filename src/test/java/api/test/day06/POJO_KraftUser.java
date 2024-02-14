package api.test.day06;

import api.test.POJOTEMPLATES.Education;
import api.test.POJOTEMPLATES.Experience;
import api.test.POJOTEMPLATES.KraftUser;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class POJO_KraftUser {

    private String KRAFTBASEURL = "https://www.krafttechexlab.com/sw/api/v1";

    @Test
    public void getSpecificUser() {

        Response response = RestAssured
                .given().accept(ContentType.JSON)
                .when()
                .get(KRAFTBASEURL + "/allusers/getbyid/1");


        KraftUser[] kraftUsers = response.as(KraftUser[].class);

        //get the length of array
        System.out.println("kraftUsers.length = " + kraftUsers.length);

        //validate that name is "MercanS"
        String expectedName = "MercanS";
        String actualName = kraftUsers[0].getName();
        Assert.assertEquals(actualName, expectedName);

        //validate that email is "afmercan@gmail.com"
        String expectedEmail = "afmercan@gmail.com";
        String actualEmail = kraftUsers[0].getEmail();
        Assert.assertEquals(actualEmail, expectedEmail);

        //get skills
        //validate that second skills is "Java"
        List<String> skills = kraftUsers[0].getSkills();
        String expectedSkill = "Java";
        String actualSkill = skills.get(1);
        Assert.assertEquals(actualSkill, expectedSkill);

        //education
        List<Education> education = kraftUsers[0].getEducation();
        //validate that id of first education is 44
        Education education1 = education.get(0);
        Integer expectedId = 44;
        Integer actualId = education1.getId();
        Assert.assertEquals(actualId, expectedId);

        //validate that  school of second education is "School"
        Education education2 = education.get(1);
        String expectedSchool = "School";
        String actualSchool = education2.getSchool();
        Assert.assertEquals(actualSchool, expectedSchool);

        //experience
        List<Experience> experience = kraftUsers[0].getExperience();

        //validate that company of first experience is "Kraft Techex1"
        Experience experience1 = experience.get(0);
        String expectedCompany = "Kraft Techex1";
        String actualCompany = experience1.getCompany();
        Assert.assertEquals(actualCompany, expectedCompany);


    }
}
