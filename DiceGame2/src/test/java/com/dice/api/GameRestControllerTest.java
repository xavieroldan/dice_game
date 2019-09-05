/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dice.api;

import com.dice.model.Game;
import com.dice.tool.ErrorValueException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
public class GameRestControllerTest
{

    public GameRestControllerTest()
    {
    }

    @BeforeClass
    public static void setUpClass()
    {
    }

    @AfterClass
    public static void tearDownClass()
    {
    }

    @Before
    public void setUp()
    {
    }

    @After
    public void tearDown()
    {
    }

    /**
     * Test of return401 method, of class GameRestController.
     */
    @Test
    public void testReturn401()
    {
        System.out.println("return401");
        ErrorValueException ex = null;
        GameRestController instance = new GameRestController();
        String expResult = "";
        String result = instance.return401(ex);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAvgRate method, of class GameRestController.
     */
    @Test
    public void testGetAvgRate() throws Exception
    {
        System.out.println("getAvgRate");
        GameRestController instance = new GameRestController();
        double expResult = 0.0;
        double result = instance.getAvgRate();
        assertEquals(expResult, result, 0.0);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllGame method, of class GameRestController.
     */
    @Test
    public void testGetAllGame()
    {
        System.out.println("getAllGame");
        GameRestController instance = new GameRestController();
        Iterable<Game> expResult = null;
        Iterable<Game> result = instance.getAllGame();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

}
