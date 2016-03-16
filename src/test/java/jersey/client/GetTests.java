package jersey.client;

import jersey.client.RestClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class GetTests {
    private RestClient restClient;
    private static final String BASE_URL = "http://jsonplaceholder.typicode.com/";

    @Before
    public void init(){
        restClient = new RestClient(BASE_URL);
    }

    @Test
    public void getWithPath(){
        String PATH = "users";
        String responseStr = restClient.get(PATH).readEntity(String.class);
        Assert.assertEquals(true, responseStr.startsWith("[\n  {\n    \"id\": 1"));
    }

    @Test
    public void getWithLongPath(){
        String PATH = "posts/1/comments";
        String responseStr = restClient.get(PATH).readEntity(String.class);
        Assert.assertEquals(true, responseStr.startsWith("[\n  {\n    \"postId\": 1"));
    }

    @Test
    public void getWithPathAndParam(){
        String PATH = "comments";
        Map<String, String> params = new HashMap<String, String>();
        params.put("postId", "1");
        String responseStr = restClient.get(PATH, params).readEntity(String.class);
        Assert.assertEquals(true, responseStr.startsWith("[\n  {\n    \"postId\": 1"));
    }
}
