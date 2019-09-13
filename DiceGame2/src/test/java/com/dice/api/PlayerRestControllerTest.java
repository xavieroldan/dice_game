/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dice.api;

import com.dice.model.Game;
import com.dice.model.Player;
import com.dice.model.RateDTO;
import com.dice.tool.ErrorTransactionException;
import com.dice.tool.ErrorValueException;
import java.util.List;
import java.util.UUID;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
public class PlayerRestControllerTest
{

    public PlayerRestControllerTest()
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

    @Test
    public void testReturn409()
    {
        System.out.println("return409");
        ErrorTransactionException ex = null;
        PlayerRestController instance = new PlayerRestController();
        String expResult = "";
        String result = instance.return409(ex);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testReturn401()
    {
        System.out.println("return401");
        ErrorValueException ex = null;
        PlayerRestController instance = new PlayerRestController();
        String expResult = "";
        String result = instance.return401(ex);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testCreatePlayer() throws Exception
    {
        System.out.println("createPlayer");
        Player player = null;
        PlayerRestController instance = new PlayerRestController();
        Player expResult = null;
        Player result = instance.createPlayer(player);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testEditName() throws Exception
    {
        System.out.println("editName");
        Player playerToEdit = null;
        PlayerRestController instance = new PlayerRestController();
        Player expResult = null;
        Player result = instance.editName(playerToEdit);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testPlayGame() throws Exception
    {
        System.out.println("playGame");
        UUID id = null;
        PlayerRestController instance = new PlayerRestController();
        Player expResult = null;
        Player result = instance.playGame(id);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testDeletePlayer() throws Exception
    {
        System.out.println("deletePlayer");
        UUID idPlayer = null;
        PlayerRestController instance = new PlayerRestController();
        ResponseEntity expResult = null;
        ResponseEntity result = instance.deletePlayer(idPlayer);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testDeleteGames() throws Exception
    {
        System.out.println("deleteGames");
        UUID idPlayer = null;
        PlayerRestController instance = new PlayerRestController();
        Player expResult = null;
        Player result = instance.deleteGames(idPlayer);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetListRatePlayers() throws Exception
    {
        System.out.println("getListRatePlayers");
        PlayerRestController instance = new PlayerRestController();
        List<RateDTO> expResult = null;
        List<RateDTO> result = instance.getListRatePlayers();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetPlayerGames() throws Exception
    {
        System.out.println("getPlayerGames");
        UUID idPlayer = null;
        PlayerRestController instance = new PlayerRestController();
        List<Game> expResult = null;
        List<Game> result = instance.getPlayerGames(idPlayer);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetLoser() throws Exception
    {
        System.out.println("getLoser");
        PlayerRestController instance = new PlayerRestController();
        RateDTO expResult = null;
        RateDTO result = instance.getLoser();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetWinner() throws Exception
    {
        System.out.println("getWinner");
        PlayerRestController instance = new PlayerRestController();
        RateDTO expResult = null;
        RateDTO result = instance.getWinner();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testPlayGameSixDice() throws Exception
    {
        System.out.println("playGameSixDice");
        UUID id = null;
        PlayerRestController instance = new PlayerRestController();
        Player expResult = null;
        Player result = instance.playGameSixDice(id);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testTestGetAllPlayer()
    {
        System.out.println("testGetAllPlayer");
        PlayerRestController instance = new PlayerRestController();
        Iterable<Player> expResult = null;
        Iterable<Player> result = instance.testGetAllPlayer();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

}
