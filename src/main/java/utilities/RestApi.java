package utilities;



import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import configurations.IRestAPIConfigs;

/**
 *  RestApi class is a utility for
 *  calling rest api using rest template
 */
public class RestApi {
    IRestAPIConfigs apiCredentials;
    RestTemplate restClient;

    /**
     *  takes IRestAPIConfigs as parameter which
     *  defines the necessary requirements
     */
    public RestApi(IRestAPIConfigs apiCredentials) {
        this.apiCredentials = apiCredentials;
        init();
    }

    private void init() {
        restClient = new RestTemplate();
        restClient.setErrorHandler(apiCredentials.restErrorHandler());
    }


    /**
     *  method for get request
     *  returns response of the request.
     */
    public ResponseEntity<String> getRequest(String endPoint)   {
        String fooResourceUrl
                = apiCredentials.baseUrl();
        return restClient.getForEntity(fooResourceUrl + endPoint, String.class);

    }}
