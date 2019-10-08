package at.htl;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

@QuarkusTest
public class MotorcycleSellerTest {

    @Test
    public void testEngineEndpoint() {
        given()
                .when().get("/API/engine/getEngine/1")
                .then()
                .statusCode(200)
                .body(is("{\"cubicCapacity\":1290,\"cylinders\":2,\"id\":1}"));
    }

    @Test
    public void testMotorcycleEndpoint() {
        given()
                .when().get("/API/motorcycle/getMotorcycle/1")
                .then()
                .statusCode(200)
                .body(is("{\"colour\":\"Orange\",\"engine\":{\"cubicCapacity\":1290,\"cylinders\":2,\"id\":1},\"hp\":160,\"id\":1,\"motorcycleType\":{\"brand\":\"KTM\",\"id\":1,\"model\":\"1290 Superduke\",\"yearOfConstruction\":\"2017-04-15\"},\"transmission\":{\"gears\":6,\"id\":1}}"));
    }

    @Test
    public void testCustomerEndpoint() {
        given()
                .when().get("/API/customer/getCustomer/1")
                .then()
                .statusCode(200)
                .body(is("{\"firstname\":\"Hans\",\"id\":1,\"lastname\":\"Huber\"}"));
    }
}
