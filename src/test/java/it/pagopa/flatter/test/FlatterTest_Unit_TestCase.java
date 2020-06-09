package it.pagopa.flatter.test;

import it.pagopa.flatter.impl.FlatterImpl;
import it.pagopa.flatter.specs.Flattable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

@DisplayName("Unit Test for Flatter Services")
public class FlatterTest_Unit_TestCase
{
    @Test
    @DisplayName("With Nested Arrays")
    public void flat_WithNestedArrays_ShouldReturnFlattedArray()
    {
        try
        {
            Object[] input = {1,3,new Object[]{7,new Object[]{24,new Object[]{100,90,80},12,1},9},4};
            Flattable flatter = new FlatterImpl();
            Object[] output = flatter.flat(input);
            Assertions.assertEquals(11,output.length);
        }
        catch (Exception ex)
        {
            Assertions.fail(ex);
        }
    }

    @Test
    @DisplayName("With Nested Arrays and Null values")
    public void flat_WithNestedNull_ShouldReturnFlattedArray()
    {
        try
        {
            Object[] input = {1,3,new Object[]{null,new Object[]{24,new Object[]{100,90,80},12,1},9},4};
            Flattable flatter = new FlatterImpl();
            Object[] output = flatter.flat(input);
            Assertions.assertEquals(10,output.length);
        }
        catch (Exception ex)
        {
            Assertions.fail(ex);
        }
    }

    @Test
    @DisplayName("With Null input")
    public void flat_WithNullArrays_ShouldReturnEmptyArray()
    {
        try
        {
            Object[] input = null;
            Flattable flatter = new FlatterImpl();
            Object[] output = flatter.flat(input);
            Assertions.assertEquals(0,output.length);
        }
        catch (Exception ex)
        {
            Assertions.fail(ex);
        }
    }

    @Test
    @DisplayName("With elements of different types")
    public void flat_WithDifferentTypeItems_ShouldThrowException()
    {
        try
        {
            Object[] input = {1,12.0,new Object[]{null,new Object[]{24,new Object[]{100,90,80},"I'm a String!",1},9},new BigDecimal(10)};
            Flattable flatter = new FlatterImpl();
            Object[] output = flatter.flat(input);
            Assertions.assertEquals(10,output.length);
        }
        catch (Exception ex)
        {
            Assertions.assertTrue(true);
        }
    }

    @Test
    @DisplayName("Strongly Typed Strategy: With Nested Arrays")
    public void flat_StronglyTypedWithNestedArrays_ShouldReturnFlattedArray()
    {
        try
        {
            Object[] input = {1,3,new Object[]{7,new Object[]{24,new Object[]{100,90,80},12,1},9},4};
            Flattable flatter = new FlatterImpl();
            Object[] output = flatter.flat(input,Integer.class);
            Assertions.assertEquals(11,output.length);
        }
        catch (Exception ex)
        {
            Assertions.fail(ex);
        }
    }

    @Test
    @DisplayName("Strongly Typed Strategy: With Nested Arrays and Null values")
    public void flat_StronglyTypedWithNestedNull_ShouldReturnFlattedArray()
    {
        try
        {
            Object[] input = {1,3,new Object[]{null,new Object[]{24,new Object[]{100,90,80},12,1},9},4};
            Flattable flatter = new FlatterImpl();
            Object[] output = flatter.flat(input,Integer.class);
            Assertions.assertEquals(10,output.length);
        }
        catch (Exception ex)
        {
            Assertions.fail(ex);
        }
    }

    @Test
    @DisplayName("Strongly Typed Strategy: With Null input")
    public void flat_StronglyTypedWithNullArrays_ShouldReturnEmptyArray()
    {
        try
        {
            Object[] input = null;
            Flattable flatter = new FlatterImpl();
            Object[] output = flatter.flat(input,Integer.class);
            Assertions.assertEquals(0,output.length);
        }
        catch (Exception ex)
        {
            Assertions.fail(ex);
        }
    }

    @Test
    @DisplayName("Strongly Typed Strategy: With unallowed items")
    public void flat_StronglyTypedWithUnallowsItems_ShouldThrowException()
    {
        try
        {
            Object[] input = {1,3,new Object[]{null,new Object[]{24,new Object[]{100,90,80},"Not Allowed Value",1},9},4};
            Flattable flatter = new FlatterImpl();
            Object[] output = flatter.flat(input,Integer.class);
            Assertions.fail("Was supposed throwing an Exception...");
        }
        catch (Exception ex)
        {
            Assertions.assertTrue(true);
        }
    }

}
