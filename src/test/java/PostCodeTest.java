import org.testng.Assert;
import org.testng.annotations.Test;
import services.ResponseService;
import services.PostCodeService;

public class PostCodeTest {
    @Test
    public void invalidPostCodeStatusCodeVerification() {
        String postCode = "1234";
        PostCodeService invalidPostCode = new PostCodeService(postCode);
        ResponseService validatePostCodeResponse = invalidPostCode.getValidatePostCodeResponse();
        Assert.assertEquals(validatePostCodeResponse.getStatusCode(), 200);
    }
    @Test
    public void invalidPostCodeResultVerification(){
        String postCode = "cr77";
        PostCodeService invalidPostCode = new PostCodeService(postCode);
        ResponseService validatePostCodeResponse = invalidPostCode.getValidatePostCodeResponse();
        Assert.assertEquals(validatePostCodeResponse.getResultBody(), "false");
    }
}
