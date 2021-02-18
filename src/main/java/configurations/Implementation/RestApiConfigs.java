package configurations.Implementation;


import Constants.Url;
import configurations.IRestAPIConfigs;
import configurations.RestAPIErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

/**
 * PostCodeConfigs initialize all the necessary configuration
 * for postcode api configuration
 */
public class RestApiConfigs implements IRestAPIConfigs {

    @Override
    public String baseUrl() {
        return Url.POST_CODE_BASE_URL;
    }


    @Override
    public ResponseErrorHandler restErrorHandler() {
        return new RestAPIErrorHandler();

    }
}

