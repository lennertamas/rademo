import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;

import java.util.List;
import java.util.Map;

public class webServiceTest {

    @Test
    public void postResponseCodeTest()
    {
        Response response = RestAssured
                .when()
                .get("https://jsonplaceholder.typicode.com/posts")
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .response();

        int code = response.statusCode();

        Assertions.assertEquals(200, code);
    }

    @Test
    public void postResponseSizeTest()
    {
        Response response = RestAssured
                .when()
                .get("https://jsonplaceholder.typicode.com/posts")
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .response();

        List<Map<String, ?>> list = response.jsonPath().getList("");

        Assertions.assertEquals(100, list.size());
    }

    @Test
    public void postResponseContentTest()
    {
        String expected = "qui est esse";
        String actual = "";

        Response response = RestAssured
                .when()
                .get("https://jsonplaceholder.typicode.com/posts")
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .response();

        List<Map<String, ?>> list = response.jsonPath().getList("");

        for(Map<String, ?> item: list)
        {
            if ((int)item.get("id")==2)
            {
                actual = (String)item.get("title");
                break;
            }
        }
        Assertions.assertEquals(expected, actual);
    }
}
