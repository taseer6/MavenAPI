package configurations;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import javax.security.sasl.AuthenticationException;
import java.io.IOException;
/**
 * RestAPIErrorHandler class defines api response codes
 */
public class RestAPIErrorHandler implements ResponseErrorHandler {


    @Override
    public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
        return (
                httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR
                        || httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse clienthttpresponse) throws IOException {
        if (clienthttpresponse.getStatusCode() != HttpStatus.OK) {
            HttpStatus statusCode=clienthttpresponse.getStatusCode();
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            System.out.println(stackTraceElements[7]);
            switch (statusCode) {
                case REQUEST_TIMEOUT:
                    System.out.println("Call returned a error 408 forbidden resposne ");
                    break;
                case INTERNAL_SERVER_ERROR:
                    System.out.println("Call returned a error 500 INTERNAL_SERVER_ERROR ");
                    break;
                case GATEWAY_TIMEOUT:
                    System.out.println("Call returned a error 504 Gateway Timeout ");
                    break;
                default:
                    System.out.println("API Failed with Status Code =="+statusCode);
            }
        }
    }
}