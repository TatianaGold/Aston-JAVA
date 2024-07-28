
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostmanEchoPage {

    public PostmanEchoPage() {
        RestAssured.baseURI = "https://postman-echo.com";
    }

    public Response getRequest(String foo1, String foo2) {
        return given()
                .queryParam("foo1", foo1)
                .queryParam("foo2", foo2)
                .when()
                .get("/get")
                .then()
                .extract().response();
    }

    public Response postRawText(String text) {
        return given()
                .header("Content-Type", "text/plain")
                .body(text)
                .when()
                .post("/post")
                .then()
                .extract().response();
    }

    public Response postFormData(String foo1, String foo2) {
        return given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .formParam("foo1", foo1)
                .formParam("foo2", foo2)
                .when()
                .post("/post")
                .then()
                .extract().response();
    }

    public Response putRequest(String text) {
        return given()
                .header("Content-Type", "text/plain")
                .body(text)
                .when()
                .put("/put")
                .then()
                .extract().response();
    }

    public Response patchRequest(String text) {
        return given()
                .header("Content-Type", "text/plain")
                .body(text)
                .when()
                .patch("/patch")
                .then()
                .extract().response();
    }

    public Response deleteRequest(String text) {
        return given()
                .header("Content-Type", "text/plain")
                .body(text)
                .when()
                .delete("/delete")
                .then()
                .extract().response();
    }

    public void checkResponse(Response response, String data) {
        response.then()
                .statusCode(200)
                .body("data", equalTo(data))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.'content-type'", containsString("text/plain"))
                .body("args", anEmptyMap())
                .body("files", anEmptyMap())
                .body("form", anEmptyMap());

        String requestStart = response.path("headers.x-request-start");
        String traceId = response.path("headers.x-amzn-trace-id");

        assert requestStart != null && !requestStart.isEmpty();
        assert traceId != null && !traceId.isEmpty();
    }

    public void checkFormResponse(Response response, String foo1, String foo2) {
        response.then()
                .statusCode(200)
                .body("form.foo1", equalTo(foo1))
                .body("form.foo2", equalTo(foo2))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.'content-type'", containsString("application/x-www-form-urlencoded"))
                .body("args", anEmptyMap())
                .body("files", anEmptyMap())
                .body("data", equalTo(""));

        String requestStart = response.path("headers.x-request-start");
        String traceId = response.path("headers.x-amzn-trace-id");

        assert requestStart != null && !requestStart.isEmpty();
        assert traceId != null && !traceId.isEmpty();
    }
}
