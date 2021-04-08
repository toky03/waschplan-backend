package ch.bfh;

import ch.bfh.dto.Termin;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class WaschplanResourceTest {

    private static final LocalDateTime DATE_TIME_1 = LocalDateTime.of(2020,1,1,10,0);
    private static final LocalDateTime DATE_TIME_2 = LocalDateTime.of(2020,1,1,12,0);
    private static final LocalDateTime DATE_TIME_3 = LocalDateTime.of(2020,1,1,13,0);
    private static final LocalDateTime DATE_TIME_4 = LocalDateTime.of(2020,1,1,14,0);
    private static final LocalDateTime DATE_TIME_5 = LocalDateTime.of(2020,1,1,15,0);

//    @BeforeEach
//    public void cleanUp(){
//        Response response = given().header("Content-Type", "Application/Json").when().delete("/termine/1ad2c269-87bd-4344-b72a-769485d3b583").then().extract().response();
//    }
//
//
//    @Test
//    public void testHelloEndpoint() {
//        given()
//          .when().get("/termine")
//          .then()
//             .statusCode(200);
//    }
//
//    @Test
//    public void exactSameDateShouldOverlap(){
//        Termin termin = createTermin(DATE_TIME_1, DATE_TIME_2);
//        //
//        postTermin(termin, 201);
//        postTermin(termin, 400);
//    }
//
//    @Test
//    public void overlappingFromEnd(){
//        Termin oldTermin = createTermin(DATE_TIME_2, DATE_TIME_4);
//        Termin newTermin = createTermin(DATE_TIME_1, DATE_TIME_3);
//        //
//        postTermin(oldTermin, 201);
//        postTermin(newTermin, 400);
//    }
//
//    @Test
//    public void overlappingFromBeginning(){
//        Termin oldTermin = createTermin(DATE_TIME_1, DATE_TIME_3);
//        Termin newTermin = createTermin(DATE_TIME_2, DATE_TIME_5);
//        //
//        postTermin(oldTermin, 201);
//        postTermin(newTermin, 400);
//    }
//
//    @Test
//    public void overlappingInside(){
//        Termin oldTermin = createTermin(DATE_TIME_1, DATE_TIME_5);
//        Termin newTermin = createTermin(DATE_TIME_2, DATE_TIME_3);
//        //
//        postTermin(oldTermin, 201);
//        postTermin(newTermin, 400);
//    }
//
//    @Test
//    public void overlappingIsideFromExisting(){
//        Termin oldTermin = createTermin(DATE_TIME_2, DATE_TIME_3);
//        Termin newTermin = createTermin(DATE_TIME_1, DATE_TIME_5);
//        //
//        postTermin(oldTermin, 201);
//        postTermin(newTermin, 400);
//    }
//
//    @Test
//    public void successfullIfSameExactEndAndBeginning(){
//        Termin oldTermin = createTermin(DATE_TIME_1, DATE_TIME_2);
//        Termin newTermin = createTermin(DATE_TIME_2, DATE_TIME_3);
//        //
//        postTermin(oldTermin, 201);
//        postTermin(newTermin, 201);
//    }
//
//    @Test
//    public void successfullIfSameExactEndAndBeginning_fromEnd(){
//        Termin oldTermin = createTermin(DATE_TIME_2, DATE_TIME_3);
//        Termin newTermin = createTermin(DATE_TIME_1, DATE_TIME_2);
//        //
//        postTermin(oldTermin, 201);
//        postTermin(newTermin, 201);
//    }
//
//
//    private void postTermin(Termin termin, int expectedStatusCode) {
//        given().header("Content-type", "application/json")
//                .and().body(termin.toJson()).when().post("/termine" ).then().statusCode(expectedStatusCode);
//    }
//
//    private Termin createTermin(LocalDateTime start, LocalDateTime end) {
//        return Termin.builder().parteiId(UUID.fromString("1ad2c269-87bd-4344-b72a-769485d3b583")).terminBeginn(start).terminEnde(end).build();
//    }

}