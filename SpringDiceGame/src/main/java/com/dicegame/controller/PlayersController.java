package com.dicegame.controller;

import com.dicegame.model.DiceGames;
import com.dicegame.model.DiceResults;
import com.dicegame.model.Players;
import com.dicegame.repository.DiceGamesRepository;
import com.dicegame.repository.DiceResultsRepository;
import com.dicegame.repository.PlayersRepository;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayersController
{
    @Autowired
    private PlayersRepository repository;
    @Autowired
    private DiceGamesRepository repoGames;
    @Autowired
    private DiceResultsRepository repoResults;

    //GET /players/: retorna el llistat de tots els jugadors del sistema 
    //amb el seu percentatge mig d’èxits
//    @GetMapping("/players")
//    public List<Players> getAllPlayers()
//    {
////        return repository.findAll();
////        return repository.findAll();
//    }
    //POST: /players : crea un jugador
//    @RequestMapping(value = "/players", method = RequestMethod.POST,
//            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @RequestMapping(value = "/players", headers = "content-type=application/json")
    @ResponseBody
    public Players createPlayers(@RequestBody Players player)
    {
        System.out.println("Vamos a enviar este objeto: \n" + player.toString());
        return repository.save(player);
    }
}
