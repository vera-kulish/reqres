package adapters;

import models.*;

import static io.restassured.RestAssured.given;

public class ResourceAdapter {

    public ResourceList listResource() {
        return given().
                when().
                        get("https://reqres.in/api/unknown").
                then().
                        log().all().
                        statusCode(200).extract().as(ResourceList.class);
    }

    public ResourceResponse getResource() {
        return given().
                when().
                        get("https://reqres.in/api/unknown/2").
                then().
                        log().all().
                        statusCode(200).
                        extract().body().as(ResourceResponse.class);
    }

    public ResourceResponse getResourceNotFound() {
        return given().
                when().
                        get("https://reqres.in/api/unknown/23").
                then().
                        log().all().
                        statusCode(404).
                        extract().body().as(ResourceResponse.class);
    }
}
