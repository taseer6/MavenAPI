package services.Implementation;

import services.IInputValidationService;


/**
 * CommandLineInputValidationService validates the command line arguments
 * and print error code on console.
 */


public class CommandLineInputValidationService implements IInputValidationService {
    String[] args;
    private int ERROR_CODE = 0;

    /**
     *  command line argument as parameter
     */
    public CommandLineInputValidationService(String[] args) {
        this.args = args;
        init();
    }

    /**
     *  checks if the input is valid
     *  prints the error message if
     *  any error code found
     */
    protected void init() {
        checkErrorCode();
        printErrorMessage();
    }


    /**
     * returns Boolean for the command line input
     */
    @Override
    public Boolean validInput() {
        return ERROR_CODE == 0;
    }


    /**
     *  checks the error type
     */
    private void checkErrorCode() {
        if (args.length == 0) ERROR_CODE = 1;
        else if (args[0].length() == 0) ERROR_CODE = 2;
    }



    /**
     *  prints the error message if
     */
    @Override
    public void printErrorMessage() {
        switch (ERROR_CODE) {
            case 1:
                System.out.println("You didn't enter any argument");
                break;
            case 2:
                System.out.println("You have entered empty argument");
                break;
        }
    }
}
