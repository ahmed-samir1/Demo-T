package API_Task;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

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
        //long startTime = System.currentTimeMillis();
        Response resp = RestAssured.get("https://api.agify.io/?name=meelad");
        long responseTimeInSeconds = resp.getTimeIn(TimeUnit.SECONDS);
        System.out.println("Response time in seconds using getTimeIn():" + responseTimeInSeconds);
        System.out.println("Response Time : " + resp.getTime());

        // Getting ValidatableResponse type
        ValidatableResponse valRes = resp.then();
        // Asserting response time is less than 5000 milliseconds
        // L just represent long. It is in millisecond by default.
        valRes.time(Matchers.lessThan(5000L));
        // Asserting response time is greater than 2000 milliseconds
        //valRes.time(Matchers.greaterThan(2000L));
        // Asserting response time in between some values
        //valRes.time(Matchers.both(Matchers.greaterThanOrEqualTo(4000L)).and(Matchers.lessThanOrEqualTo(1000L)));

        // If we want to assert in different time units
        //valRes.time(Matchers.lessThan(2L), TimeUnit.SECONDS);

        // Print response time
        // System.out.println("Response Time : " + resp.getTime());

        // If we want to get elapsed time in different time units

        // long elapsedTime = System.currentTimeMillis() - startTime;
        // System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);
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