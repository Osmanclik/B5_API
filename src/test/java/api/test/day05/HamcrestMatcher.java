package api.test.day05;

import io.restassured.http.ContentType;

import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class HamcrestMatcher {
    @BeforeClass
    public void beforeClass() {
        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void t1_getOneUser() {
        /** CT D05 TC01
         * given accept type is JSON
         * And path param id is 111
         * When user send a get request to /allusers/getbyid/{id}
         * Then status code should be 200
         * And content type  should be application/json; charset=UTF-8
         */

        given().accept(ContentType.JSON)
                .pathParam("id", 111)
                .when()
                .get("/allusers/getbyid/{id}")
                .then()
                .statusCode(200)
                .and()
                .assertThat()
                .contentType("application/json; charset=UTF-8");
    }

    @Test
    public void t2_getOneUser_with_HamcrestMatcher() {
        //CT D05 TC02
        given().accept(ContentType.JSON)
                .and()
                .pathParam("id", 111)
                .when()
                .get("/allusers/getbyid/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .assertThat()
                .contentType("application/json; charset=UTF-8")
                .and()
                .body("id[0]", Matchers.equalTo(111),
                        "name[0]", Matchers.equalTo("Thomas Eduson"),
                        "job[0]", equalTo("Developer"),
                        "[0].skills[2]", equalTo("Selenium"),
                        "skills[0][3]", equalTo("Cypress"),
                        "education[0].school[1]", equalTo("Delft University"),
                        "education[0][1].school", equalTo("Delft University"),
                        "[0].education[1].school", equalTo("Delft University"),
                        "education.school[0][1]", equalTo("Delft University")
                );
    }

    @Test
    public void t3_oneUser_verifyHeaderContent_with_HamcrestMatcher() {
        /**CT D05 TC03
         * given accept type is JSON
         * And path param id is 111
         * When user send a get request to /allusers/getbyid/{id}
         * Then status code 200
         * And content Type application/json; charset=UTF-8
         * And response header Content-Type should be application/json; charset=UTF-8
         * And response header Content-Length should be 606
         * And json data should have email equal "thomas@test.com"
         * And json data should have company equal "GHAN Software"
         */
        given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .pathParam("id", 111)
                .when()
                .get("/allusers/getbyid/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .assertThat()
                .contentType("application/json; charset=UTF-8")
                .and()
                .header("Content-Type", equalTo("application/json; charset=UTF-8"))
                .and()
                .header("Content-Length", equalTo("606"))
                .and()
                .header("Date", notNullValue())
                .assertThat()
                .body("email[0]", equalTo("thomas@test.com"),
                        "[0].company", equalTo("GHAN Software"));
    }

    @Test
    public void t4_moreThanOneUser_hasItem_with_hamcrest() {
        //CT D05 TC04
        given().accept(ContentType.JSON)
                .and()
                .log().all() // to get request body
                .contentType(ContentType.JSON)
                .and()
                .queryParam("page", 1)
                .and()
                .queryParam("pagesize", 50)
                .when()
                .get("/allusers/alluser")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType("application/json; charset=UTF-8")
                .and()
                .body("email", hasItem("ghan@krafttechexlab.com"))
                .log().all(); // to get response header and body

    }

    @Test
    public void t5_moreThanOneUser_hasItems_with_hamcrest() {
        /** CT D05 TC05
         * given accept type is JSON
         * And query param pagesize is 50
         * And query param page is 1
         * When user sends a get request to /allusers/alluser
         * Then status code 200
         * And content Type application/json; charset=UTF-8
         * And response header Content-Type should be application/json; charset=UTF-8
         * And response header Content-Length should be 9090 // Content-Length is null in headers
         * And response header Server should be Apache/2
         * And response header has Date
         * And json data should have name equal "GHAN","Aegon Targaryen HTU","Mansimmo"
         * And json data should have  "bilkent" for 6. user's school
         * And json data should have  "Junior Developer1" for first user's first experience
         */
        given().accept(ContentType.JSON)
                .and()
                .log().all() // to get request body
                .contentType(ContentType.JSON)
                .and()
                .queryParam("page", 1)
                .and()
                .queryParam("pagesize", 50)
                .when()
                .get("/allusers/alluser")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(equalTo("application/json; charset=UTF-8"))
                .and()
                .header("Server", equalTo("Apache/2"))
                .and()
                .headers("Date", notNullValue(),
                        "Connection", equalTo("Upgrade, Keep-Alive"),
                        "Keep-Alive",equalTo("timeout=2, max=100"))
                .and()
                .body("name",hasItems("GHAN","Aegon Targaryen HTU","Mansimmo"),
                        "[5].education[0].school",equalTo("bilkent"),
                        "[0].experience[0].job",equalTo("Junior Developer1"));
    }
}