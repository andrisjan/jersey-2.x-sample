package jersey.client;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.jersey.client.ClientConfig;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

public class RestClient {
    private ClientConfig config;
    private Client client;
    private WebTarget target;
    private ObjectMapper mapper;

    public RestClient(String baseUrl){
        // todo ja base url nebeidzas ar / - jāpievieno
        config = new ClientConfig();
        client = ClientBuilder.newClient(config);
        target = client.target(baseUrl) ;
        mapper = new ObjectMapper();
    }

    // todo metodes kurām var norādīt .accept(MediaType)

    public Response get(String path){
        Response response = target
                .path(path)
                .request()
                .get(Response.class);
        return response;
    }

    public Response get(String path, Map<String, String> params){
        WebTarget tmptTarget = target
                .path(path);
        for(String key : params.keySet()){
            tmptTarget = tmptTarget.queryParam(key, params.get(key));
        }
        Response response = tmptTarget
                .request()
                .get(Response.class);
        return response;
    }

    public Response postForm(String path, Map<String, String> params){
        Form form = new Form();
        for(String key : params.keySet()){
            form.param(key, params.get(key));
        }
        Response response = target
                .path(path)
                .request()
                .post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), Response.class);
        return response;
    }

    public Response postJson(String path, Object jsonObject){
        Response response;
        try {
            response = target
                    .path(path)
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(mapper.writeValueAsString(jsonObject), MediaType.APPLICATION_JSON), Response.class);
        }
        catch (JsonProcessingException e){
            throw new RuntimeException(e.getMessage(), e);
        }
        return response;
    }

    // todo
    // put

    public Response delete(String path){
        return target
                .path(path)
                .request()
                .delete();
    }

    public Response delete(String path, Map<String, String> params){
        WebTarget tmptTarget = target
                .path(path);
        for(String key : params.keySet()){
            tmptTarget = tmptTarget.queryParam(key, params.get(key));
        }
        Response response = tmptTarget
                .path(path)
                .request()
                .delete();
        return response;
    }
}
