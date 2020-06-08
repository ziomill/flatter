package it.pagopa.array.flatter.test;

import it.pagopa.array.flatter.Flatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Integration Test for Flatter")
public class FlatterTest
{
    @Test
    public void flat_WithNestedArrays_ShouldReturnFlattedArray()
    {
        try
        {
            Object[] input = {1,3,new Object[]{7,new Object[]{24,new Object[]{100,90,80},12,1},9},4};
            Flatter flatter = new Flatter();
            Integer[] output = flatter.flat(input);
            Assertions.assertEquals(11,output.length);
        }
        catch (Exception ex)
        {
            Assertions.fail(ex);
        }
    }

    @Test
    public void flat_WithNestedNull_ShouldReturnFlattedArray()
    {
        try
        {
            Object[] input = {1,3,new Object[]{null,new Object[]{24,new Object[]{100,90,80},12,1},9},4};
            Flatter flatter = new Flatter();
            Integer[] output = flatter.flat(input);
            Assertions.assertEquals(10,output.length);
        }
        catch (Exception ex)
        {
            Assertions.fail(ex);
        }
    }

    @Test
    public void flat_WithNullArrays_ShouldReturnEmptyArray()
    {
        try
        {
            Object[] input = null;
            Flatter flatter = new Flatter();
            Integer[] output = flatter.flat(input);
            Assertions.assertEquals(0,output.length);
        }
        catch (Exception ex)
        {
            Assertions.fail(ex);
        }
    }

    @Test
    public void flat_WithUnallowsItems_ShouldThrowException()
    {
        try
        {
            Object[] input = {1,3,new Object[]{null,new Object[]{24,new Object[]{100,90,80},"Not Allowed Value",1},9},4};
            Flatter flatter = new Flatter();
            Integer[] output = flatter.flat(input);
            Assertions.fail("Was supposed throwing an Exception...");
        }
        catch (Exception ex)
        {
            Assertions.assertTrue(true);
        }
    }
}
