/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dicegame.controller;

import com.dicegame.model.Players;
import com.dicegame.repository.PlayersRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
@RestController
public class PlayersController
{
    @Autowired
    private PlayersRepository repository;

//    GET /players/: retorna el llistat de tots els jugadors del sistema 
//    amb el seu percentatge mig d’èxits
    @GetMapping("/players")
    public List<Players> getAllPlayers()
    {
        return repository.findAll();
    }

////    POST: /players : crea un jugador
//    @PostMapping("/players")
//    public Players createPlayers(Players player)
//    {
//        return repository.save(player);
//    }
}
