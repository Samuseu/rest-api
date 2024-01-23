package hw;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegresTests {
    /**
     * https://reqres.in/api/users?page=2
     */
    @Test
    void listUsersTests() {
        String expectedEmail = "michael.lawson@reqres.in";

        String actualEmail = given()
                .log().uri()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().path("data[0].email");

        assertEquals(expectedEmail, actualEmail);
    }

    @Test
    void listUsersTests2() {
        given()
                .log().uri()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data[1].first_name", is("Lindsay"));
    }

    @Test
    void listUsersHasKey1() {
        given()
                .log().uri()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data[0]", hasKey("id"));
    }

    /**
     * https://reqres.in/api/unknown/2
     */

    @Test
    void singleResourceTest() {
        given()
                .log().uri()
                .when()
                .get("https://reqres.in/api/unknown/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200);
    }

    @Test
    void singleResourceTest1() {
        given()
                .log().uri()
                .when()
                .get("https://reqres.in/api/unknown/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data", hasKey("id"));
    }

    @Test
    void singleResourceTest2() {
        given()
                .log().uri()
                .when()
                .get("https://reqres.in/api/unknown/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.year", is(2001));
    }

    /**
     * https://reqres.in/api/users
     */
    @Test
    void postUsersCreateTest() {
        String data = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";

        Response response = given()
                .log().uri()
                .body(data)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .extract().response();

        response.then().body("id", notNullValue());
        response.then().body("createdAt", notNullValue());


    }

    @Test
    void postUsersUpdateTest() {
        String data = "{ \"name\": \"morpheus\", \"job\": \"zion resident\" }";

        Response response = given()
                .log().uri()
                .body(data)
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().response();

        response.then().body("updatedAt", notNullValue());


    }

    @Test
    void postUsersDeleteTest() {
        given()
                .log().uri()
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(204);


    }
}
