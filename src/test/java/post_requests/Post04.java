package post_requests;

import baseUrl.JsonPlaceHolderBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Post04 extends JsonPlaceHolderBaseUrl {
       /*
        Given
            1) https://jsonplaceholder.typicode.com/todos
            2) {"userId": 55,"title": "Tidy your room","completed": false}
        When
            I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like
            {
                "userId": 55,
                "title": "Tidy your room",
                "completed": false,
                "id": 201
            }
     */

    @Test
    public void test01() throws JsonProcessingException {
        //set the url
        spec.pathParam("first","todos");

        //set the expected data / payload
        String json ="{\"userId\": 55,\"title\": \"Tidy your room\",\"completed\": false}";
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,Object> payload = objectMapper.readValue(json, HashMap.class);
/*
ObjectMapper objecti ile bu classta bulunan readValue methodu sayesinde birinci parametre
olarak verdigimiz String tipindeki json datayi ikinci parametre olarak verdigimiz classa (=>HashMap)
cevirir.Böylece datalari manual olarak girmemize gerek kalmaz
 */

        //send request get response
        Response response = given(spec).body(payload).when().post("{first}");

        //do assertion
        Map<String,Object> actualData = response.as(HashMap.class);
        // Map<String,Object> actualData1 = objectMapper.readValue(response.asString(),HashMap.class);

        assertEquals(201,response.statusCode());
        assertEquals(payload.get("userId"),actualData.get("userId"));
        assertEquals(payload.get("title"),actualData.get("title"));
        assertEquals(payload.get("completed"),actualData.get("completed"));
        assertEquals(201,actualData.get("id"));



    }
}