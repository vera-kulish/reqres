package adapters;

import models.LoginResponse;
import models.RegisterResponse;

import static io.restassured.RestAssured.given;

public class LoginAdapter {

    public LoginResponse login() {
        return given().
                body("{\n" +
                        "    \"email\": \"eve.holt@reqres.in\",\n" +
                        "    \"password\": \"cityslicka\"\n" +
                        "}").
                header("Content-Type", "application/json").
        when().
                post("https://reqres.in/api/login").
        then().
                log().all().
                statusCode(200).
                extract().body().as(LoginResponse.class);
    }

    public String loginUnsuccessful() {
        return given().
                body("{\n" +
                        "    \"email\": \"peter@klaven\"\n" +
                        "}").
                header("Content-Type", "application/json").
        when().
                post("https://reqres.in/api/login").
        then().
                log().all().
                statusCode(400).
                extract().body().asString();
    }
}
