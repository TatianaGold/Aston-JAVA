import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

public class PostmanEchoTests {

    private PostmanEchoPage postmanEcho;

    @BeforeEach
    public void setup() {
        postmanEcho = new PostmanEchoPage();
    }

    @Test
    public void testGetRequest() {
        Response response = postmanEcho.getRequest("bar1", "bar2");
        response.then()
                .statusCode(200)
                .body("args.foo1", equalTo("bar1"))
                .body("args.foo2", equalTo("bar2"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("url", equalTo("https://postman-echo.com/get?foo1=bar1&foo2=bar2"));
    }

    @Test
    public void testRawTextPost() {
        String requestBody = "This is expected to be sent back as part of response body.";
        Response response = postmanEcho.postRawText(requestBody);
        postmanEcho.checkResponse(response, requestBody);
    }

    @Test
    public void testFormDataPost() {
        Response response = postmanEcho.postFormData("bar1", "bar2");
        postmanEcho.checkFormResponse(response, "bar1", "bar2");
    }

    @Test
    public void testPutRequest() {
        String requestBody = "This is expected to be sent back as part of response body.";
        Response response = postmanEcho.putRequest(requestBody);
        postmanEcho.checkResponse(response, requestBody);
    }

    @Test
    public void testPatchRequest() {
        String requestBody = "This is expected to be sent back as part of response body.";
        Response response = postmanEcho.patchRequest(requestBody);
        postmanEcho.checkResponse(response, requestBody);
    }

    @Test
    public void testDeleteRequest() {
        String requestBody = "This is expected to be sent back as part of response body.";
        Response response = postmanEcho.deleteRequest(requestBody);
        postmanEcho.checkResponse(response, requestBody);
    }
}
