package apitests;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.*;

import static io.restassured.RestAssured.*;
public class calculator {
    @BeforeClass
    public void setup(){baseURI = "http://miscellaneous.yospace.com:8080";}

    @Test
    public void test1(){

        Map<String, Object> requestMap = new HashMap<>();
        List<Integer> operands = Arrays.asList(2,5);
        requestMap.put("operator","+");
        requestMap.put("operands",operands);

        Response response =
                given().accept(ContentType.JSON)
                        .and().contentType(ContentType.JSON)
                        .body(requestMap)
                        .when().post("yospace/calculator/");

        response.prettyPrint();

        Assert.assertEquals(response.statusCode(), 200);
        ResponseBody body = response.getBody();
        String bodyAsString = body.asString();
        Assert.assertTrue(bodyAsString.contains("7.0"));

    }
}

