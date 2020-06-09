package it.pagopa.flatter.specs;

public interface Flattable
{
    /**
     * Flat the input into an Array.
     * Accept elements of different type.
     *
     * @param input a simple Object (like an Number) or an Array of arbitrarily nested arrays.
     * @return a flattened array.
     */
    Object[] flat(Object input);

    /**
     * Flat the input into an Array.
     * Accept only elements of given type.
     *
     * @param input a simple Object (like an Number,String) or an array of arbitrarily nested arrays.
     * @param typeToFlat the Type of input element.
     * @return a flattened array.
     * @throws Exception if input simple elements differs from given type.
     */
    Object[] flat(Object input,
                  Class typeToFlat) throws Exception;
}
