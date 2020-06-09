package it.pagopa.flatter.test;

import it.pagopa.flatter.http.FlatterHttpServer;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.charset.Charset;

@DisplayName("Integration Test for Flatter Http Server")
public class FlatterTest_IT_TestCase
{

    private static FlatterHttpServer httpServer = new FlatterHttpServer();

    @BeforeAll
    public static void init() throws IOException
    {
        httpServer.start(10001);
    }

    @AfterAll
    public static void tearDown() throws IOException
    {
        httpServer.stop();
    }

    @Test
    @DisplayName("Http call with Nested Arrays")
    public void flat_WithNestedArrays_ShouldReturnFlattedArray()
    {
        try
        {
            HttpPost post = new HttpPost("http://localhost:10001/flat");
            post.setHeader("Content-Type", "application/json");
            post.setHeader("Accept", "application/json");
            String body = "[1,2,3,4,[5,6],7,[8,9,[10,[11,12,13,14,[15,16]]]]]";
            StringEntity entity = new StringEntity(body, Charset.availableCharsets().get("UTF-8"));
            post.setEntity(entity);
            CloseableHttpClient build = HttpClientBuilder.create().build();
            HttpResponse response = build.execute(post);
            boolean isOk = isOk(response);

            // Check Http Code
            Assertions.assertTrue(isOk);

            // Check Response
            String json = EntityUtils.toString(response.getEntity());
            JSONArray jsonArray = new JSONArray(json);
            Assertions.assertEquals(16,jsonArray.length());
        }
        catch (Exception ex)
        {
            Assertions.fail(ex);
        }
    }

    private boolean isOk(HttpResponse response) {
        int statusCode = response.getStatusLine().getStatusCode();
        return (statusCode==200);
    }

}
