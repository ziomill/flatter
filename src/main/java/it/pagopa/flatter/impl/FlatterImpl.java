package it.pagopa.flatter.impl;

import it.pagopa.flatter.specs.Flattable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlatterImpl implements Flattable
{

    @Override
    public Object[] flat(Object input) {
        List<Object> result = new ArrayList();

        // Check Size
        if(input == null){
            return new Object[0];
        }

        // Base: current element is a simple one.
        if(!input.getClass().isArray())
        {
            result.add(input);
        }
        // Complex: current element is a nested array. Recursion will be applied on every element of array.
        else
        {
            for(Object element : (Object[]) input)
            {
                result.addAll(Arrays.asList(flat(element)));
            }
        }
        return result.toArray(Object[]::new);
    }

    @Override
    public Object[] flat(Object input,
                         Class typeToFlat) throws Exception {
        List<Object> result = new ArrayList();

        // Check Size
        if(input == null){
            return new Object[0];
        }

        // Base: current element is a simple one.
        if(!input.getClass().isArray())
        {
            // Check Type
            if(!typeToFlat.isInstance(input))
            {
                throw new Exception("Invalid values into input. Only: " + typeToFlat.getName() + " are allowed...");
            }
            result.add(input);
        }
        // Complex: current element is a nested array. Recursion will be applied on every element of array.
        else
        {
            for(Object element : (Object[]) input)
            {
                result.addAll(Arrays.asList(flat(element,
                        typeToFlat)));
            }
        }
        return result.toArray(Object[]::new);
    }
}
