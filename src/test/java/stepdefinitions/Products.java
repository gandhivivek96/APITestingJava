package stepdefinitions;


import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Products {

    public RequestSpecification httprequest;
    public Response response;
    public int responseCode;
    public ResponseBody body;

    @Given("the base API URL")
    public void the_base_api_url() {

        RestAssured.baseURI="https://fakestoreapi.com/";

     }
    @When("I send a GET request to {string}")
    public void i_send_a_get_request_to(String string) {

        httprequest = RestAssured.given();
        response = httprequest.get("products");
        System.out.println(" String entered = "+string);
    }
    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer int1) {
        responseCode = response.getStatusCode();
        assertEquals(200, responseCode);
        System.out.println("Response code = "+responseCode);
    }


    @Then("the rate of the first product in the response should be {double}")
    public void theRateOfTheFirstProductInTheResponseShouldBeDouble1(double expectedRate ) {

        JsonPath jsonPath = response.jsonPath();
        double actualRate = jsonPath.getDouble("[0].rating.rate");
        assertEquals(expectedRate,actualRate);
    }


    @When("I send a POST request to {string}")
    public void iSendAPOSTRequestTo(String productTitle) {

    }

    @And("I pass the request body of product title {string}")
    public void iPassTheRequestBodyOfProductTitle(String productTitle) {
        httprequest = RestAssured.given();
        // Serialization -> Jackson Lib will convert this java object into JSON
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("title", productTitle);
        requestBody.put("price", 13.5); // Use the correct data type (Double or Float)
        requestBody.put("description", "lorem ipsum set");
        requestBody.put("image", "https://i.pravatar.cc");
        requestBody.put("category", "electronic");

        httprequest.body(requestBody);

        response = httprequest.post("products");
//        Deserialization
        System.out.println("Response Body:" + response.getBody().asPrettyString());

//        System.out.println("ID of new product = "+response.jsonPath().getString("id"));
//        System.out.println(" response after adding code = "+response.getStatusCode());

    }


    @When("I send a PUT request to {int}")
    public void iSendAPUTRequestToProductID(int id) {
        httprequest = RestAssured.given();
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("title", "Macbook");
        requestBody.put("price", 13.5); // Use the correct data type (Double or Float)
        requestBody.put("description", "lorem ipsum set");
        requestBody.put("image", "https://i.pravatar.cc");
        requestBody.put("category", "electronic");

        httprequest.body(requestBody);

        response = httprequest.put("products/"+id);
//        System.out.println("Response Body:" + response.getBody().asPrettyString());
//        System.out.println(" response after adding code = "+response.getStatusCode());


    }

    @When("I send a DELETE request to {int}")
    public void i_send_a_delete_request_to(int id) {
          httprequest = RestAssured.given();
          response = httprequest.delete("products/"+id);
//        System.out.println("Response Body:" + response.getBody().asPrettyString());
//        System.out.println(" response after adding code = "+response.getStatusCode());

    }
}
