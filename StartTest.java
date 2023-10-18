package edu.ifrs;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import edu.ifrs.business.Vehicle;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class StartTest {

    // 1
    @Test
    public void testGetVehicle() {
        Response response = given()
                            .when()
                            .get("/logistics/getVehicle/0");

        response.then()
            .statusCode(200);
    }

    // 2
    @Test
    public void testGetVehicles() {
        Response response = given()
                            .when()
                            .get("/logistics/getVehicles");

        response.then()
            .statusCode(200);
    }

    // 3
    @Test
    public void testGetVehiclesHasAtLeastOne() {
        Response response = given()
                            .when()
                            .get("/logistics/getVehicles");

        response.then()
            .statusCode(200)
            .body("size()", greaterThan(1));
    }

    // 4
    @Test
    public void testGetVehiclesHaveTwo() {
        Response response = given()
                            .when()
                            .get("/logistics/getVehicles");

        response.then()
            .statusCode(200)
            .body("size()", is(2));
    }

    // 5
    @Test
    public void testGetVehiclesEquals() {
        Response response1 = given()
            .when()
            .get("/logistics/getVehicle/0");

        Response response2 = given()
            .when()
            .get("/logistics/getVehicles");

        response1.then()
            .statusCode(200);

        response2.then()
            .statusCode(200);

        response2.then()
            .body("[0].maximumWeightLimit", equalTo(response1.path("maximumWeightLimit")));
    }

}