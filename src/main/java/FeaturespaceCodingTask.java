import services.IInputValidationService;
import services.Implementation.CommandLineInputValidationService;
import services.PostCodeService;

/**
 * The {@code FeaturespaceCodingTask} class is the main method class
 * for executing the task.
 */

public class FeaturespaceCodingTask {

    /**
     * The main method takes input argument. Validate the input
     * then prints ValidPostCodeResult, post code Country Region
     * and Nearest post codes Country Region
     */
    public static void main(String[] args) {
        IInputValidationService input = new CommandLineInputValidationService(args);
        if (input.validInput()) {
            PostCodeService postCode = new PostCodeService(args[0]);
            postCode.printValidPostCodeResult();
            postCode.printCountryAndRegion();
            postCode.printNearestCountryAndRegion();

        }
    }
}
