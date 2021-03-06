package org.wahlzeit.extension;

/**
 * Provides common assertion Functions to guard the entry of a method
 */
public class Guard {
    /**
     * Asserts that an argument lies within a certain range
     * @param argument argument to check
     * @param min minimum of the range
     * @param max maximum of the range
     * @param argumentName name of the argument, used for the exception message
     */
    public static void assertArgumentInRange(double argument, double min, double max, String argumentName) throws IllegalArgumentException{
        assertDoubleArgumentValid(argument, InvalidDoubleValues.NAN, argumentName);
        if(max < min){
            throw new IllegalArgumentException("max must be greater than min");
        }
        if(argument < min || argument > max){
            String message = createArgumentExceptionMessage(argumentName, "Argument value must be between "+min+" and "+ max);
            throw new IllegalArgumentException(message);
        }
    }
 
    /**
     * Asserts that an argument lies above a certain value
     * @param argument argument to check
     * @param min lowest value possible
     * @param allowPositiveInfinity true if positive inifnity should be a valid value
     * @param argumentName name of the argument, used for the exception message
     */
    public static void assertArgumentGreaterOrEqualThan(double argument, double min, boolean allowPositiveInfinity, String argumentName){
        assertDoubleArgumentValid(min, InvalidDoubleValues.NAN_INFINITY, "min");

        if(allowPositiveInfinity){
            assertDoubleArgumentValid(argument, InvalidDoubleValues.NAN, argumentName);
        } else {
            assertDoubleArgumentValid(argument, InvalidDoubleValues.NAN_INFINITY, argumentName);
        }
        
        if(argument < min){
            String message = createArgumentExceptionMessage(argumentName, "Argument value must be greater than "+min);
            throw new IllegalArgumentException(message);
        }
    } 
    
    /**
     * Asserts that an argument lies above a certain value
     * @param argument argument to check
     * @param max highest value possible
     * @param allowNegativeInfinity true if negative inifnity should be a valid value
     * @param argumentName name of the argument, used for the exception message
     */
    public static void assertArgumentLessOrEqualThan(double argument, double max, boolean allowNegativeInfinity, String argumentName) {
        assertDoubleArgumentValid(max, InvalidDoubleValues.NAN_INFINITY, "max");

        if (allowNegativeInfinity) {
            assertDoubleArgumentValid(argument, InvalidDoubleValues.NAN, argumentName);
        } else {
            assertDoubleArgumentValid(argument, InvalidDoubleValues.NAN_INFINITY, argumentName);
        }

        if (argument > max) {
            String message = createArgumentExceptionMessage(argumentName, "Argument value must be less than " + max);
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Asserts that an argument is not null
     * @param argument argument to check
     * @param argumentName name of the argument, used for the exception message
     */
    public static void assertArgumentNotNull(Object argument, String argumentName){
        if(argument == null){
            String message = createArgumentExceptionMessage(argumentName , "Argument must not be null");
            throw new IllegalArgumentException(message);
        }
    }
    /**
     * Asserts that an argument is of the specified type
     * @param argument argument to check
     * @param type expected type
     * @param argumentName name of the argument
     */
    public static void assertArgumentOfType(Object argument, Class type, String argumentName){
        if(argument == null || argument.getClass() != type){
            String message = createArgumentExceptionMessage(argumentName, "Argument must be of type "+type.getName());
            throw new IllegalArgumentException(message);
        }
    }
    /**
     * Asserts that a double value is valid
     * @param value value to check
     * @param invalidDoubleValues Determines, which values are considered invalid
     * @param argumentName name of the argument, used for the exception message
     */
    public static void assertDoubleArgumentValid(double value, InvalidDoubleValues invalidDoubleValues, String argumentName){
        switch(invalidDoubleValues){
            case NAN:
                if(Double.isNaN(value)){
                    throw new IllegalArgumentException(createArgumentExceptionMessage(argumentName, "Argument must not be NaN"));
                }
                break;
            case NAN_INFINITY:
                if(Double.isNaN(value) || Double.isInfinite(value)){
                    throw new IllegalArgumentException(createArgumentExceptionMessage(argumentName, "Argument must not be NaN or infinite"));
                }
                break;
        }
    }

    private static String createArgumentExceptionMessage(String argumentName, String message){
        String prefix = (argumentName == null)? "" : (argumentName + ": ");
        return prefix + message;
    }
    public enum InvalidDoubleValues{
        NAN, NAN_INFINITY
    }
}