package services;

import configurations.Implementation.RestApiConfigs;
import dto.PostCodeDto;
import utilities.RestApi;

import java.util.List;
/**
 * The {@code PostCodeService} class contains all the necessary
 * for executing postcode related tasks.
 *
 * It contains methods for postcode endpoints, return
 * endpoint responses , json to Pojo and printing reuqired
 * data from postcode endpoints.
 */
public class PostCodeService {

    private String postCodeInput;
    String endPointPath;
    private Boolean validPostCode;
    private PostCodeDto postCodeDto;
    private List<PostCodeDto> nearestPostCodesDto;
    private ResponseService validatePostCodeResponse, postCodeResponse, nearestPostCodeResponse;
    private RestApi restApi;


    /**
     * Takes postCode input and assign it
     * initialize restApi utility and passess configuration
     * RestApiConfigs class.
     */
    public PostCodeService(String postCodeInput) {
        this.postCodeInput = postCodeInput;
        restApi = new RestApi(new RestApiConfigs());
    }

    /**
     * Function will call rest api get request
     * for validate post code endpoint
     *
     * @return     the {@code ResponseService} object
     */
    private ResponseService callValidatePostCode() {
        endPointPath = "postcodes/";//TODO
               return new ResponseService(restApi.getRequest(endPointPath + postCodeInput + "/validate"));
    }

    /**
     * Function will call rest api get request
     * for post code endpoint
     *
     * @return     the {@code ResponseService} object
     */
    private ResponseService callPostCode() {
        endPointPath = "postcodes/" + postCodeInput;
        return new ResponseService(restApi.getRequest(endPointPath));
    }

    /**
     * Function will call rest api get request
     * for nearest post code endpoint
     *
     * @return     the {@code ResponseService} object
     */
    public ResponseService callNearestPostCode() {
        endPointPath = "postcodes/" + postCodeInput + "/nearest";//TODO
        return new ResponseService(restApi.getRequest(endPointPath));
    }


    /**
     * It returns the ResponseService object for endpoint
     * checks if the object is initialized. if the object is empty it calls
     * validate post code api
     *
     * @return     the ResponseService object and  <code>null</code> if the api request
     * is unsuccessful
     */
    public ResponseService getValidatePostCodeResponse() {
        if (validatePostCodeResponse == null) {
            validatePostCodeResponse = callValidatePostCode();
        }
        return validatePostCodeResponse;
    }
    public ResponseService getPostCodeResponse() {
        if (postCodeResponse == null) {
            postCodeResponse = callPostCode();
        }
        return postCodeResponse;
    }

    public ResponseService getNearestPostCodeResponse() {
        if (nearestPostCodeResponse == null) {
            nearestPostCodeResponse = callNearestPostCode();
        }
        return nearestPostCodeResponse;
    }


    /**
     * checks if the post code validate
     *
     * @return     true if postcode is valid
     */
    public Boolean isValidPostCode() {
        if (validPostCode == null) {
            validPostCode = getValidatePostCodeResponse().isSuccessful() && getValidatePostCodeResponse().getResultBody().equalsIgnoreCase("true");
        }
        return this.validPostCode;
    }

    /**
     * converts postcode json  to PostCodeDto
     *
     * @return     PostCodeDto Object  and  <code>null</code> if the api request
     *      * is unsuccessful
     */
    public PostCodeDto getPostCode() {
        if (postCodeDto == null && getPostCodeResponse().isSuccessful()) {
            postCodeDto = postCodeResponse.objectMapping(postCodeResponse.getFilteredResponseBody("result"), PostCodeDto.class);
        }
        return postCodeDto;
    }


    /**
     * converts nearest postcodes  json objects  to List of PostCodeDto
     *
     * @return     List of Nearest PostCodeDto Object  and  <code>null</code> if the api request
     *      * is unsuccessful
     */
    public List<PostCodeDto> getNearestPostCode() {

        if (nearestPostCodesDto == null && getNearestPostCodeResponse().isSuccessful()) {
            nearestPostCodesDto = getNearestPostCodeResponse().listOfobjectMapping(getNearestPostCodeResponse().getFilteredResponseBody("result"), PostCodeDto.class);
        }
        return nearestPostCodesDto;
    }

    /**
     * Prints the repose of validate post code endpoint
     */
    public void printValidPostCodeResult() {
            System.out.println("API result is "+isValidPostCode().toString());
    }

    /**
     * Prints the  Country And Region of post code endpoint
     */
    public void printCountryAndRegion() {
        if (isValidPostCode()) {
            if (getPostCode() != null) {
                System.out.println("Country = " + getPostCode().getCountry() + " || Region = " + getPostCode().getRegion());
            }
        }
    }


    /**
     * Prints the  nearest post codes and their Country And Region of
     * nearest post code endpoint
     */
    public void printNearestCountryAndRegion() {
        if (isValidPostCode()) {
            if (getNearestPostCode() != null) {
                for (PostCodeDto nearestPostCodes : getNearestPostCode()) {
                    System.out.println("PostCode = " + nearestPostCodes.getPostcode() + " || Country = " + nearestPostCodes.getCountry() + " || Region = " + nearestPostCodes.getRegion());
                }
            }
        }
    }


}