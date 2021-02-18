package configurations;

import org.springframework.web.client.ResponseErrorHandler;

/**
 * Interface contains the necessary operation
 * for the rest api configurations
 */
public interface IRestAPIConfigs {
     String baseUrl();
     ResponseErrorHandler restErrorHandler();

}
