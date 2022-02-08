package API_Task;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class REST_API {
    @Test
    public void testResponsecode() {


        // Sample way to verify if status code 200
        Response resp = RestAssured.get("https://api.agify.io/?name=meelad");
        System.out.println("Status code is " + resp.getStatusCode());
        Assert.assertEquals(resp.getStatusCode(), 200);
    }

    @Test
    public void test_time() {


        // Sample way to check the time of response which measure performance.
        long startTime = System.currentTimeMillis();
        Response resp = RestAssured.get("https://api.agify.io/?name=meelad");
        System.out.println("Status code is " + resp.getStatusCode());
        long elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);
    }

    @Test
    public void Verify_Name_age_count_InJsonResponse() {
        {
// another way I used based URL and extract and RequestSpecification of the request that we want to sent
            RestAssured.baseURI = "https://api.agify.io";
            RequestSpecification httpRequest = RestAssured.given();
            Response response = httpRequest.get("/?name=meelad");
            JsonPath jsonPathEvaluator = response.jsonPath();

            // Then simply query the JsonPath object to get a String value of the node
            String name = jsonPathEvaluator.get("name");
            int age = jsonPathEvaluator.get("age");
            int count = jsonPathEvaluator.get("count");


            // Let us print the Name, age and count variable to see what we got
            System.out.println("Name received from Response " + name + "|" + age + "|" + count);

            // Validate the response
            Assert.assertEquals(name, "meelad", "Correct name received in the Response");
            Assert.assertEquals(age, 29, "Correct age received in the Response");
            Assert.assertEquals(count, 21, "Correct count received in the Response");


        }

    }
}