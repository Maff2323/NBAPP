package com.iesfranciscodelosrios.informatica.nbapp;

import com.iesfranciscodelosrios.informatica.nbapp.models.PersonR;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    PersonR p=new PersonR();
    @Test
    public void nEquipo() {
        //assertEquals(4, 2 + 2);
        assertEquals(true, p.setnEquipo("Lakers"));
        assertEquals(true, p.setnEquipo("Philadelphia 76 Sixers"));
        assertEquals(false, p.setnEquipo(""));


    }
    public void tSalarial() {

        assertEquals(false, p.settSalarial((long) -100));
        assertEquals(true, p.settSalarial((long) 40000000));
        assertEquals(false, p.settSalarial(Long.parseLong("mil euros")));


    }
    public void fecha() {

        assertEquals(true, p.setFecha("11/03/2003"));
        assertEquals(false, p.setFecha("29/02/2020"));
        assertEquals(false, p.setFecha("30/02/2020"));
        assertEquals(false, p.setFecha("32/05/1995"));
        assertEquals(false, p.setFecha("32/13/1995"));
        assertEquals(false, p.setFecha("29-2-2020"));
        assertEquals(false, p.setFecha("11/3/2003"));
        assertEquals(false, p.setFecha("11/03"));
        assertEquals(false, p.setFecha("11"));
        assertEquals(false, p.setFecha("11/3/95"));
        assertEquals(false, p.setFecha("2003"));
        assertEquals(false, p.setFecha(""));

    }
    public void nDueño() {
        //assertEquals(4, 2 + 2);
        assertEquals(true, p.setnEquipo("Mark Cuban"));
        assertEquals(true, p.setnEquipo("Phillips Jr III"));
        assertEquals(true, p.setnEquipo("Ray 3º"));
        assertEquals(false, p.setnEquipo(""));

    }
    public void spinner() {
        //assertEquals(4, 2 + 2);
        assertEquals(true, p.setSpinner("Apple"));
        assertEquals(true, p.setSpinner("Samsung"));
        assertEquals(true, p.setSpinner("Carl's Jr 3"));
        assertEquals(false, p.setSpinner(" "));
    }

}