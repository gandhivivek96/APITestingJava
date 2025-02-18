package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Products {

    public RequestSpecification httprequest;
    public Response response;
    public int responseCode;
    public ResponseBody body;

    @Given("the base API URL")
    public void the_base_api_url() {

        RestAssured.baseURI="https://fakestoreapi.com/";
      httprequest = RestAssured.given();
       response = httprequest.get("products");


    }
    @When("I send a GET request to {string}")
    public void i_send_a_get_request_to(String string) {
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
    public void iSendAPOSTRequestTo(String arg0) {
        
    }

    @And("I pass the request body of product title {string}")
    public void iPassTheRequestBodyOfProductTitle(String arg0) {
    }
}
