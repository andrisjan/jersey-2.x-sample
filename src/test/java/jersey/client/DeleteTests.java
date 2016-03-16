package jersey.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import jersey.client.RestClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;

public class DeleteTests {
    private RestClient restClient;
    private static final String BASE_URL = "http://jsonplaceholder.typicode.com/";
    private ObjectMapper mapper = new ObjectMapper();
    @Before
    public void init(){
        restClient = new RestClient(BASE_URL);
    }

    @Test
    public void deleteWrongPath() throws Exception{
        String PATH = "wrong";
        Response response = restClient.delete(PATH);
        Assert.assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    @Test
    public void deletePost() throws Exception{
        String PATH = "posts/1";
        Response response = restClient.delete(PATH);
        Assert.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        String responseStr = response.readEntity(String.class);
        Assert.assertEquals("{}", responseStr);
    }



}
