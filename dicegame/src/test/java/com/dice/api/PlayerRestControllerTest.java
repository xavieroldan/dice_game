/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dice.api;

import com.dice.apis.PlayerRestController;
import com.dice.apis.HelperRestController;
import com.dice.models.Player;
import com.dice.repositories.PlayerRepository;
import com.dice.tool.ErrorValueException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
public class PlayerRestControllerTest
{

    @Autowired
    PlayerRepository playerRepo;
    @Autowired
    HelperRestController helper;

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
    public void testCreatePlayerNoObject() throws Exception
    {
        //Return status: Method Not Allowed - 405
        System.out.println("createPlayer/No Object");
        //Given
        HttpUriRequest request = new HttpGet("http://localhost:8080/players");
        // When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        //Then 
        boolean exit = false;
        int expect = 405;
        int response = httpResponse.getStatusLine().getStatusCode();
        if (expect != response)
        {
            fail("The test case is a prototype. Status code:" + response + exit);
        }
    }

    @Test(expected = ErrorValueException.class)
    public void testCreatePlayerObjectNull() throws Exception
    {
        //Return status: ErrorValueException - 401
        System.out.println("createPlayer/Object null");
        //Given
        Player player = null;
        PlayerRestController instance = new PlayerRestController();
        // When
        instance.createPlayer(player);
        //Then        
        //ErrorValueException - 401
    }

    @Test(expected = ErrorValueException.class)
    public void testCreatePlayerNameVoid() throws Exception
    {
        //Return status: ErrorValueException - 401
        System.out.println("createPlayer/Object name void");
        //Given
        Player player = new Player();
        player.setName("");
        PlayerRestController instance = new PlayerRestController();
        // When
        instance.createPlayer(player);
        //Then        
        //ErrorValueException - 401
    }

    @Test(expected = DataAccessResourceFailureException.class)
    public void testCreatePlayerDataAccessFail() throws Exception
    {
        //Return status: ErrorValueException - 401
        System.out.println("createPlayer/Data Access broken");
        //Given
        Player player = null;
        PlayerRestController instance = new PlayerRestController();
        // When
        instance.createPlayer(player);
        //Then        
        //ErrorValueException - 401
    }

//    @Test
//    public void testEditName() throws Exception
//    {
//        System.out.println("editName");
//        Player playerToEdit = null;
//        PlayerRestController instance = new PlayerRestController();
//        Player expResult = null;
//        Player result = instance.editName(playerToEdit);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testPlayGame() throws Exception
//    {
//        System.out.println("playGame");
//        UUID id = null;
//        PlayerRestController instance = new PlayerRestController();
//        Player expResult = null;
//        Player result = instance.playGame(id);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testDeletePlayer() throws Exception
//    {
//        System.out.println("deletePlayer");
//        UUID idPlayer = null;
//        PlayerRestController instance = new PlayerRestController();
//        ResponseEntity expResult = null;
//        ResponseEntity result = instance.deletePlayer(idPlayer);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testDeleteGames() throws Exception
//    {
//        System.out.println("deleteGames");
//        UUID idPlayer = null;
//        PlayerRestController instance = new PlayerRestController();
//        Player expResult = null;
//        Player result = instance.deleteGames(idPlayer);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testGetListRatePlayers() throws Exception
//    {
//        System.out.println("getListRatePlayers");
//        PlayerRestController instance = new PlayerRestController();
//        List<RateDTO> expResult = null;
//        List<RateDTO> result = instance.getListRatePlayers();
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testGetPlayerGames() throws Exception
//    {
//        System.out.println("getPlayerGames");
//        UUID idPlayer = null;
//        PlayerRestController instance = new PlayerRestController();
//        List<Game> expResult = null;
//        List<Game> result = instance.getPlayerGames(idPlayer);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testGetLoser() throws Exception
//    {
//        System.out.println("getLoser");
//        PlayerRestController instance = new PlayerRestController();
//        RateDTO expResult = null;
//        RateDTO result = instance.getLoser();
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testGetWinner() throws Exception
//    {
//        System.out.println("getWinner");
//        PlayerRestController instance = new PlayerRestController();
//        RateDTO expResult = null;
//        RateDTO result = instance.getWinner();
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testPlayGameSixDice() throws Exception
//    {
//        System.out.println("playGameSixDice");
//        UUID id = null;
//        PlayerRestController instance = new PlayerRestController();
//        Player expResult = null;
//        Player result = instance.playGameSixDice(id);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testTestGetAllPlayer()
//    {
//        System.out.println("testGetAllPlayer");
//        PlayerRestController instance = new PlayerRestController();
//        Iterable<Player> expResult = null;
//        Iterable<Player> result = instance.testGetAllPlayer();
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
}
