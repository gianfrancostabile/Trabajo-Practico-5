package com.utn.tssi.tp5.Models.model;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class StateTest extends TestCase{

    State state;
    State otherState;

    @Before
    public void setUp() {

        Country country = new Country(1, "Argentina", "ARG");
        this.state = new State(1, "Buenos Aires", "BA", country);
        this.otherState = new State("Córdoba", "COR", country);
    }

    @Test
    public void testToStringOK() {
        String value = this.state.toString();
        assertEquals("Checking toString", value, "{name='Buenos Aires', iataCode='BA', country={name='Argentina', isoCode='ARG'}}");
    }

    @Test
    public void testToStringNull() {
        this.state.setCountry(null);
        String value = this.state.toString();

        assertEquals("Checking toString", value, "{name='Buenos Aires', iataCode='BA', country=null}");
    }

    @Test
    public void testEqualsNull(){
        boolean value = this.state.equals(null);
        assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsOtherObject(){
        boolean value = this.state.equals("String");
        assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsNullAttributes(){

        boolean value = this.state.equals(new State());
        assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsDifferentAttributes(){
        boolean value = this.state.equals(this.otherState);
        assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsOK(){
        this.otherState = this.state;

        boolean value = this.state.equals(this.otherState);
        assertEquals("Checking equals", value, true);
    }

    @Test
    public void testHashCodeOK() {
        int value = this.state.hashCode();
        assertEquals("Checking hashCode", value, 1707458729);
    }

    @Test
    public void testHashCodeOneNull() {
        this.state.setIataCode(null);
        int value = this.state.hashCode();
        assertEquals("Checking hashCode", value, 1707393288);
    }

    @Test
    public void testHashCodeAllNull() {
        this.state = new State();

        int value = this.state.hashCode();
        assertEquals("Checking hashCode", value, 11082252);
    }

    @Test
    public void testValidateNullEmptyOK() {
        boolean value = this.state.validateNullEmpty();
        assertFalse("Checking validateNullEmpty", value);
    }

    @Test
    public void testValidateNullEmptyAttributeNull() {
        this.state.setName(null);

        boolean value = this.state.validateNullEmpty();
        assertTrue("Checking validateNullEmpty", value);
    }

    @Test
    public void testValidateNullEmptyAttributeEmpty() {
        this.state.setName("");

        boolean value = this.state.validateNullEmpty();
        assertTrue("Checking validateNullEmpty", value);
    }

    @Test
    public void testValidateNullEmptyIdentifierOK() {
        boolean value = this.state.validateNullEmptyIdentifier();
        assertFalse("Checking validateNullEmptyIdentifier", value);
    }

    @Test
    public void testValidateNullEmptyIdentifierAttributeNull() {
        this.state.setIataCode(null);

        boolean value = this.state.validateNullEmptyIdentifier();
        assertTrue("Checking validateNullEmptyIdentifier", value);
    }

    @Test
    public void testValidateNullEmptyIdentifierAttributeEmpty() {
        this.state.setIataCode("");

        boolean value = this.state.validateNullEmptyIdentifier();
        assertTrue("Checking validateNullEmptyIdentifier", value);
    }
}
