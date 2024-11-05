package support;

import io.restassured.RestAssured;
import io.restassured.filter.Filter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.FilterableRequestSpecification;

public final class RestAssuredHelper {

    private static final Filter responseLogging = new ResponseLoggingFilter(System.out);

    public static final String API_BASE_LOCALHOST_URI = "http://localhost:8080/api/v1";
    public static final String API_BASE_URI = systemUnderTest(System.getProperty("app.sut"));

    private static String systemUnderTest(String sutUrl) {
        return switch (sutUrl) {
            case null -> API_BASE_LOCALHOST_URI;
            case String s when s.isBlank() -> API_BASE_LOCALHOST_URI;
            default -> sutUrl;
        };
    }

    private RestAssuredHelper() {

    }

    public static FilterableRequestSpecification givenApiRequest() {
        return (FilterableRequestSpecification) RestAssured.given()
            .log().everything()
            .baseUri(API_BASE_URI)
            .filters(responseLogging);
    }
}
