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
    void listOfUsers() {
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

        assertEquals(3, data.getData().getId());
//        System.out.println("__________"+data.getData());
    }

    @Test
    void singUserWithLombokModel() {
        rest.api.groovy.lombok.UserData data = Specs.request
                .when()
                .get("/users/2")
                .then()
                .spec(Specs.responseSpec)
                .log().body()
                .extract().as(rest.api.groovy.lombok.UserData.class);

//        User user = User.builder().id(2).email("").build(); // Билдер чтобы быстрее создавать юзеров.

        assertEquals(2, data.getUser().getId());
    }

    @Test
    void singUserFormatterOff() { // пример как работает форматер

        // @formatter:off
        Specs.request
                .when()
                    .get("/users/2")
                .then()
                    .spec(Specs.responseSpec)
                    .log().body();
        //@formatter:on
    }
}
