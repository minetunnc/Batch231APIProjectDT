package get_requests;

import baseUrl.HerOkuAppBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Get10 extends HerOkuAppBaseUrl {

/*
 Given
     https://restful-booker.herokuapp.com/booking/91
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
        spec.pathParams("first","booking","second",3111);

        //set the expected data

        //Once inner map olusturulur
        Map<String,String> bookingDatesData = new HashMap<>();
        bookingDatesData.put("checkin","2018-01-01");
        bookingDatesData.put("checkout","2019-01-01");

        Map<String,Object> expectedData = new HashMap<>();

        expectedData.put("firstname","John");
        expectedData.put("lastname","Smith");
        expectedData.put("totalprice",111);
        expectedData.put("depositpaid",true);
        expectedData.put("bookingdates", bookingDatesData );
        expectedData.put("additionalneeds", "Breakfast" );
        System.out.println("expectedData = " + expectedData);

        String str =   (String)  expectedData.get("firstname");
        Object obj = "Ali Can";
        ( (String) obj).contains("Can");

        //send request get response
        Response response = given(spec).when().get("{first}/{second}");


        //do assertion
        Map<String,Object> actualData = response.as(HashMap.class);//De-serialization

        assertEquals(expectedData.get("firstname"),actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"),actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"),actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"),actualData.get("depositpaid"));
        assertEquals(expectedData.get("additionalneeds"),actualData.get("additionalneeds"));


        //1. Yol Map ile doğrulama ==>TAVSIYE EDILMEZ
           /*
        Ic ice JSON datalar ile doğrulama yaparken datalari Map e dönüstürmek gerekir, çünkü Assertion methodu icinde
        actualData object data tipinde oldugu icin get methodunu kullanamayiz, Get method Map classinda
        bulundugundan Object data tipini Map data tipine cevirmemiz gerekiyor.(Type Casting)
         */
        assertEquals(bookingDatesData.get("checkin"), ((Map) actualData.get("bookingdates") ).get("checkin") );
        assertEquals(bookingDatesData.get("checkout"), ((Map) actualData.get("bookingdates") ).get("checkout"));

        //2. Yol JSON PATH ile doğrulama ==> Nested Datalar ile calisirken tavsiye edilir
        JsonPath jsonPath = response.jsonPath();
        assertEquals(bookingDatesData.get("checkin"),jsonPath.getString("bookingdates.checkin"));
        assertEquals(bookingDatesData.get("checkout"),jsonPath.getString("bookingdates.checkout"));


    }
}