import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static javax.swing.UIManager.get;
import static org.hamcrest.Matchers.equalTo;


public class TestGET {

    @Test
    private void test_GetAPI(){

        Response response = RestAssured.get("https://api.zippopotam.us/us/94105");

        System.out.println("Response is :\n");
        System.out.println("==============================");
        System.out.println(response.asString());
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());
        System.out.println(response.getBody().asString());
        System.out.println(response.getHeader("content-type"));
        System.out.println(response.getTime());

    }

    @Test
    private void test_GetPlaceName(){

        String expectedPlaceName="San Francisco";

        String places= given()
                        .when()
                        .get("https://api.zippopotam.us/us/94105")
                        .then()
                        .extract()
                        .path("places").toString();


        Map<String, String> myMap = new HashMap<String, String>();
        String s = places;
        String[] pairs = s.split(",");
        for (int i=0;i<pairs.length;i++) {
            String pair = pairs[i];
            String[] keyValue = pair.split("=");
            myMap.put(keyValue[0], keyValue[1]);
        }

        String currentPlaceName=myMap.get("[{place name");
        System.out.println("Expected place name: " +expectedPlaceName);
        System.out.println("Current place name: " +currentPlaceName);

        Assert.assertEquals(expectedPlaceName,currentPlaceName);


    }
}
