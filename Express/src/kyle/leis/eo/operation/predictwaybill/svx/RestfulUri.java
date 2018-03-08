package kyle.leis.eo.operation.predictwaybill.svx;

import org.junit.Before;
import org.junit.Test;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class RestfulUri {

    Client client = null;
    
    @Before
    public void before(){
       
        ClientConfig config = new DefaultClientConfig();
        config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE); 
        client = Client.create(config);
    }
    
    @Test
    public void query() {
       System.out.println("http://localhost:8080/express/rest/order/PredictwaybillQuery"); 
       WebResource resource =  client.resource("http://localhost:8080/express/rest/order/PredictwaybillQuery");      
       //ClientResponse response = resource.get(ClientResponse.class);  
    }
}