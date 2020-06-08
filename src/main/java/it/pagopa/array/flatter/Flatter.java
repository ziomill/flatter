package it.pagopa.array.flatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Flatter
{

    public Integer[] flat(Object[] input) throws Exception
    {
        // Check Size
        if(input == null || input.length == 0){
            return new Integer[0];
        }

        List<Integer> result = new ArrayList();
        for(Object element : input)
        {
            if(element == null)
            {
                continue;
            }
            else if(element instanceof Integer)
            {
               result.add((Integer)element);
            }
            else if(element instanceof Object[])
            {
              result.addAll(Arrays.asList(flat((Object[]) element)));
            }
            else
            {
               throw new Exception("Element is not an Integer or Array of Objects");
            }
       }
       return result.toArray(Integer[]::new);
    }




}
