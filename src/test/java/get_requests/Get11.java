package get_requests;

import baseUrl.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import pojos.HerOkuAppBookingDatesPojo;
import pojos.HerOkuAppGetResponesPojo;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Get11 extends HerOkuAppBaseUrl {

/*
 Given
     https://restful-booker.herokuapp.com/booking/722
 When
     I send GET Request to the url
 Then
     Response body should be like that;
     {
         "firstname": "John",
         "lastname": "Smith",
         "totalprice": 111,
         "depositpaid": true,
         "bookingdates": {
             "checkin": "2018-01-01",
             "checkout": "2019-01-01"
         },
         "additionalneeds": "Breakfast"
     }
 */

    @Test
    public void test01() {

        //set the url
        spec.pathParams("first","booking","second",4475);

        //set the expected data
        HerOkuAppBookingDatesPojo bookingDatesPojo = new HerOkuAppBookingDatesPojo("2018-01-01","2019-01-01");
        HerOkuAppGetResponesPojo expectedData = new HerOkuAppGetResponesPojo("John","Smith",111,true,bookingDatesPojo,"Breakfast");

        //send request get response
        Response response = given(spec).when().get("{first}/{second}");

        //do assertion
        HerOkuAppGetResponesPojo actualData = response.as(HerOkuAppGetResponesPojo.class);

        assertEquals(expectedData.getFirstname(),actualData.getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getLastname());
        assertEquals(expectedData.getTotalprice(),actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(expectedData.getBookingdates().getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(expectedData.getBookingdates().getCheckout(),actualData.getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getAdditionalneeds());


    }
}