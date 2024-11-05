package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.keksipurkki.demos.api.HelloResponse;
import support.RestAssuredHelper;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static support.Utils.randomString;

public class Steps {

    private final ScenarioContext context;

    public Steps(ScenarioContext context) {
        this.context = context;
    }

    @Given("hello request with message {string}")
    public void requestWithMessage(String message) {

        var resp = RestAssuredHelper.givenApiRequest()
                .get("hello?message={message}", message);

        context.responses.addLast(resp);

    }

    @Given("request with some random path")
    public void someRandomPath() {
        var randomPath = randomString(10);

        var resp = RestAssuredHelper.givenApiRequest()
                .get(randomPath);

        context.responses.addLast(resp);

    }

    @Given("hello request")
    public void request() {

        var resp = RestAssuredHelper.givenApiRequest()
                .get("hello");

        context.responses.addLast(resp);
    }

    @Then("^(\\d+) .+ response$")
    public void response(int statusCode) {
        var last = context.responses.getLast();
        assertThat(last.statusCode(), is(statusCode));
    }

    @And("message is {string}")
    public void messageIs(String message) {
        var response = context.responses.getLast().as(HelloResponse.class);
        assertThat(response.getMessage(), is(message));
    }

}
