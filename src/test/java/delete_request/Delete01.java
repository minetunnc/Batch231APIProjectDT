package delete_requests;

import baseUrl.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Delete01 extends JsonPlaceHolderBaseUrl {
     /*
        Given
            https://jsonplaceholder.typicode.com/todos/198
        When
            I send DELETE Request to the Url
        Then
            Status code is 200
        And Response body is { }
    */

    @Test
    public void test01() {
        //set the url
        spec.pathParams("first","todos","second",198);

        //set the expected data
        Map<String,Object> expectedData = new HashMap<>();
        System.out.println("expectedData = " + expectedData);

        //send request get response
        Response response = given(spec).when().delete("{first}/{second}");

        //do assertion
        //1. YOL
        Map<String,Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);
        assertEquals(expectedData,actualData);

        //2. YOL
        assertTrue(actualData.isEmpty());

        //3. YOL
        assertEquals(0,actualData.size());

    }
}