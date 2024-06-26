package utilities;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;

public class AuthenticateContactList {

    public static String generateToken(){

        //set the url
        String url ="https://thinking-tester-contact-list.herokuapp.com/users/login";

        //set the payload
        String body="{\n" +
                "\"email\":\"mine@tunc.com\",\n" +
                "\"password\":\"1234567\"}";
        //send rewuest get response
        Response response = given().body(body).contentType(ContentType.JSON).when().post(url);


        //response içinden tokeni alıp return ediyoruz
        return   response.jsonPath().getString("token");

    }

}