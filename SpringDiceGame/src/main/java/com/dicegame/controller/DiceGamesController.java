package com.dicegame.controller;

import com.dicegame.model.DiceGames;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dicegame.repository.DiceGamesRepository;
import com.dicegame.repository.PlayersRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
@RestController
public class DiceGamesController
{

    @Autowired
    private DiceGamesRepository diceRepo;
    @Autowired
    private PlayersRepository playerRepo;

    @GetMapping("/dicerolls")
    public List<DiceGames> getAlldiceRolls()
    {
        return diceRepo.findAll();
    }

    //TODO: POST /players/{id}/games/ : un jugador específic realitza una tirada dels daus.
    @RequestMapping(value = "/players{id}/games", headers = "content-type=application/json")
    @ResponseBody
    public ResponseEntity<?> deletePlayer(@PathVariable(value = "id") String idPlayer)
    {
        //crear una jugada

        //guardar la jugada
        DiceGames diceGame = new DiceGames(); // to delete
//        Optional<Players> player = repository.findById(idPlayer);
//        repository.delete(player.get());
        diceRepo.save(diceGame);
        return ResponseEntity.ok().build();
    }

}
