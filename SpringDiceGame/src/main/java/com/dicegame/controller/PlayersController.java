package com.dicegame.controller;

import com.dicegame.model.Players;
import com.dicegame.repository.PlayersRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
