package rest.api.groovy;

import org.junit.jupiter.api.Test;
import rest.api.groovy.models.UserData;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    @Test
    void singUser() {
        Specs.request
                .when()
                .get("/users/2")
                .then()
                .spec(Specs.responseSpec)
                .log().body();
    }
    @Test
    void listOfUsers(){
        Specs.request
                .when()
                .get("/users?page=2")
                .then()
                .log().body();
    }

    @Test
    void singUserWithModel() {
        UserData data = Specs.request
                .when()
                .get("/users/2")
                .then()
                .spec(Specs.responseSpec)
                .log().body()
                .extract().as(UserData.class);

//        assertEquals(3,data.getData().getId());
//        System.out.println("__________"+data.getData());
    }
}
