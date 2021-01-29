package ch.bfh;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class WaschplanResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/termine")
          .then()
             .statusCode(200);
    }

}