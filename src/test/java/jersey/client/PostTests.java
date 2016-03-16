package jersey.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import jersey.client.RestClient;
import jersey.client.struct.PostRequest;
import jersey.client.struct.PostResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PostTests {
    private RestClient restClient;
    private static final String BASE_URL = "http://jsonplaceholder.typicode.com/";
    private ObjectMapper mapper = new ObjectMapper();
    @Before
    public void init(){
        restClient = new RestClient(BASE_URL);
    }

    @Test
    public void postJson() throws Exception{
        String PATH = "posts";
        PostRequest postData = new PostRequest();
        postData.title = "Title";
        postData.body = "This is body";
        postData.userId = 10435;
        PostResponse responseActual = restClient.postJson(PATH, postData).readEntity(PostResponse.class);

        PostResponse responseCorrect = new PostResponse();
        responseCorrect.id = 101;
        responseCorrect.title = postData.title;
        responseCorrect.body = postData.body;
        responseCorrect.userId = postData.userId;

        Assert.assertEquals(mapper.writeValueAsString(responseCorrect), mapper.writeValueAsString(responseActual));
    }

    // todo
    @Test
    public void postForm() throws Exception{
        String PATH = "posts";
        PostRequest postData = new PostRequest();
        postData.title = "Title";
        postData.body = "This is body";
        postData.userId = 10435;
        PostResponse responseActual = restClient.postJson(PATH, postData).readEntity(PostResponse.class);

        PostResponse responseCorrect = new PostResponse();
        responseCorrect.id = 101;
        responseCorrect.title = postData.title;
        responseCorrect.body = postData.body;
        responseCorrect.userId = postData.userId;

        Assert.assertEquals(mapper.writeValueAsString(responseCorrect), mapper.writeValueAsString(responseActual));
    }

}
