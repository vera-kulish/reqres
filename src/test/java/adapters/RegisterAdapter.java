package adapters;

import models.RegisterResponse;
import models.UpdatedUser;

import static io.restassured.RestAssured.given;

public class RegisterAdapter {

    public RegisterResponse register() {
        return given().
                body("{\n" +
                        "    \"email\": \"eve.holt@reqres.in\",\n" +
                        "    \"password\": \"pistol\"\n" +
                        "}").
                header("Content-Type", "application/json").
        when().
                post("https://reqres.in/api/register").
        then().
                log().all().
                statusCode(200).
                extract().body().as(RegisterResponse.class);
    }

    public String registerUnsuccessful() {
        return given().
                body("{\n" +
                        "    \"email\": \"sydney@fife\"\n" +
                        "}").
                header("Content-Type", "application/json").
        when().
                post("https://reqres.in/api/register").
        then().
                log().all().
                statusCode(400).
                extract().body().asString();
    }
}
