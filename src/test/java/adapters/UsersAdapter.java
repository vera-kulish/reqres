package adapters;

import models.CreatedUser;
import models.UpdatedUser;
import models.UserList;
import models.UserResponse;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;

public class UsersAdapter {

    public UserList getList() {
        return given().
               when().
                       get("https://reqres.in/api/users?page=2").
               then().
                       log().all().
                       statusCode(200).extract().as(UserList.class);
    }

    public UserResponse getUser() {
        return given().
                when().
                        get("https://reqres.in/api/users/2").
                then().
                        log().all().
                        statusCode(200).
                        extract().body().as(UserResponse.class);
    }

    public UserResponse getUserNotFound() {
        return given().
        when().
                get("https://reqres.in/api/users/23").
        then().
                log().all().
                statusCode(404).
                extract().body().as(UserResponse.class);
    }

    public CreatedUser create(String name, String job) {
        return given().
                body("{\n" +
                        "    \"name\": \"" + name + "\",\n" +
                        "    \"job\": \"" + job + "\"\n" +
                        "}").
                header("Content-Type", "application/json").
        when().
                post("https://reqres.in/api/users").
        then().
                log().all().
                statusCode(201).
                extract().body().as(CreatedUser.class);
    }

    public UpdatedUser updatePut(String name, String job) {
        return given().
                body("{\n" +
                        "    \"name\": \"" + name + "\",\n" +
                        "    \"job\": \"" + job + "\"\n" +
                        "}").
                header("Content-Type", "application/json").
        when().
                put("https://reqres.in/api/users/2").
        then().
                log().all().
                statusCode(200).
                extract().body().as(UpdatedUser.class);
    }

    public UpdatedUser updatePatch(String name, String job) {
        return given().
                body("{\n" +
                        "    \"name\": \"" + name + "\",\n" +
                        "    \"job\": \"" + job + "\"\n" +
                        "}").
                header("Content-Type", "application/json").
        when().
                patch("https://reqres.in/api/users/2").
        then().
                log().all().
                statusCode(200).
                extract().body().as(UpdatedUser.class);
    }

    public void delete() {
        given().
        when().
                delete("https://reqres.in/api/users/2").
        then().
                log().all().
                statusCode(204);
    }

    public UserList getListWithDelay() {
        return given().
        when().
                get("https://reqres.in/api/users?delay=3").
        then().
                log().all().
                statusCode(200).
                time(greaterThan(3000L)).
                extract().as(UserList.class);
    }
}
