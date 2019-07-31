/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dicegame.controller;

import com.dicegame.model.DiceGames;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dicegame.repository.DiceGamesRepository;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
@RestController
public class DiceGamesController
{

    @Autowired
    private DiceGamesRepository repository;

    @GetMapping("/dicerolls")
    public List<DiceGames> getAlldiceRolls()
    {
        return repository.findAll();
    }

}
