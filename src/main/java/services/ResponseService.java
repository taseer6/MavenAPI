package services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.http.ResponseEntity;


import java.util.List;

/**
 * The {@code ResponseService} implements {@code IResponseService}  interface
 * it takes restapi response object as input and manipulate the necessary
 * operations for rest response
 */
public class ResponseService   {
    ResponseEntity<String> response;

    public ResponseService(ResponseEntity<String> response) {
        this.response = response;
    }
    /**
     * gives status code.
     *
     * @return    status code of api
     */
    public int getStatusCode() {
        return response.getStatusCode().value();

    }
    /**
     * gives error message.
     *
     * @return    error message of api  as string
     */
    public String getErrorMessage() {
        return getFilteredResponseBody("error"); //TODO where to define
    }

    /**
     * validates if api call was successful.
     *
     * @return    true/false
     */

    public Boolean isSuccessful() {
        return getStatusCode() == 200;
    }

    /**
     * returns the response of api as string.
     *
     * @return    the {@code String}
     */


    public String getResponseBody() {
        return response.getBody().toString();
    }

    /**
     * filters the api response and returns the result object.
     *
     * @return    the {@code String}
     */
    public String getResultBody() {
        return getFilteredResponseBody("result");
    }

    /**
     * takes json path and filters the api response.
     *
     * @return    the {@code String}, or <code>null</code> if conversion fails
     */


    public String getFilteredResponseBody(String path) {
        ObjectMapper jsonObjectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = jsonObjectMapper.readTree(getResponseBody());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        jsonNode = jsonNode.get(path);
        return jsonNode.toString();

    }

    /**
     * This method will take json as parameter and converts into pojo.
     *
     * @return    the {@code ResponseService}, or <code>null</code> if conversion fails
     */
    public <T> T objectMapping(String jsonString, Class<T> pos) {

        T javaObject;

        ObjectMapper jsonObjectMapper = new ObjectMapper();

        try {

            javaObject = jsonObjectMapper.readValue(jsonString, pos);

            return javaObject;

        } catch (JsonProcessingException e) {

            System.out.println("JsonObject object Mapping failed");//TODO should I write it like this
            e.printStackTrace();    // TODO Auto-generated catch block

        }


        return null;
    }

    /**
     * This method will take json as parameter and converts into list of  pojo.
     *
     * @return     the {@code List<ResponseService>}, or <code>null</code> if conversion fails
     */
    public <T> List<T> listOfobjectMapping(String jsonString, Class<T> pos) {
        ObjectMapper jsonObjectMapper = new ObjectMapper();
        try {
            return jsonObjectMapper.readValue(jsonString, TypeFactory.defaultInstance().constructCollectionType(List.class, pos));

        } catch (JsonProcessingException e) {

            e.printStackTrace();  // TODO Auto-generated catch block
        }

        System.out.println("JsonObject object Mapping failed");  //TODO should I write it like this

        return null;

    }
}
