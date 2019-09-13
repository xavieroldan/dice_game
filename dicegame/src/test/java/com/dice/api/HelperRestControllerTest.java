/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dice.api;

import com.dice.model.Player;
import com.dice.repository.GameRepository;
import com.dice.repository.PlayerRepository;
import com.dice.tool.ErrorTransactionException;
import com.dice.tool.ErrorValueException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
public class HelperRestControllerTest
{
    public HelperRestControllerTest()
    {
    }

    @Autowired
    PlayerRepository playerRepo;
    @Autowired
    GameRepository gameRepo;

    @Test(expected = ErrorTransactionException.class)
    public void testVerifyListPlayersListVoid() throws Exception
    {
        System.out.println("verifyListPlayers/ List void");
        List<Player> listPlayer = new ArrayList<>();
        HelperRestController instance = new HelperRestController();
        instance.verifyListPlayers(listPlayer);
        fail("The test case is a prototype.");
    }

    @Test(expected = ErrorTransactionException.class)
    public void testVerifyListPlayersListNull() throws Exception
    {
        System.out.println("verifyListPlayers/ List null");
        List<Player> listPlayer = null;
        HelperRestController instance = new HelperRestController();
        instance.verifyListPlayers(listPlayer);
        fail("The test case is a prototype.");
    }

    @Test(expected = ErrorValueException.class)
    public void testVerifyNameNull() throws Exception
    {
        System.out.println("verifyName/ Player Null");
        Player player = null;
        HelperRestController instance = new HelperRestController();
        instance.verifyName(player);
        fail("The test case is a prototype.");
    }

    @Test(expected = ErrorValueException.class)
    public void testVerifyNameTrimIsEmpty() throws Exception
    {
        System.out.println("verifyName/ Name trimed void");
        Player player = new Player();
        player.setName("       ");
        HelperRestController instance = new HelperRestController();
        instance.verifyName(player);
        fail("The test case is a prototype.");
    }

    @Test(expected = ErrorValueException.class)
    public void testVerifyNameNameNull() throws Exception
    {
        System.out.println("verifyName/ Player name null");
        Player player = new Player();
        HelperRestController instance = new HelperRestController();
        instance.verifyName(player);
        fail("The test case is a prototype.");
    }

}
