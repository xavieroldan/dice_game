package com.dicegame.controller;

import com.dicegame.model.DiceGames;
import com.dicegame.model.Players;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dicegame.repository.DiceGamesRepository;
import com.dicegame.repository.PlayersRepository;
import com.dicegame.tools.GameMaker;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
    @RequestMapping(value = "/players/{id}/games/", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public DiceGames playNewGame(@PathVariable UUID id)
    {
        DiceGames diceGame = new DiceGames();
        //create a new game
        GameMaker gameMaker = new GameMaker();
        diceGame = gameMaker.playGame(playerRepo.findById(id).get());
        return diceRepo.save(diceGame); //save the game
    }

    @RequestMapping(value = "/savegame", headers = "content-type=application/json")
    @ResponseBody
    public DiceGames saveDiceResult(@RequestBody DiceGames diceGame)
    {
        System.out.println("Voy a guardar el diceGame " + diceGame.toString());
        return diceRepo.save(diceGame);
    }
//TODO: delete
//    @RequestMapping(value = "/savegamenodata")
//    @ResponseBody
//    public DiceGames saveNoData(String id)
//    {
//        Set<DiceGames> diceGames = new HashSet<>();
//        DiceGames diceGame = new DiceGames();
//        Optional<Players> player = playerRepo.findById("ed55edff1997406da1be45c99517871e");
//        diceGame.setIdGames(UUID.randomUUID().toString().replace("-", ""));
//        diceGame.setIsAnonim(true);
//        diceGame.setIsDeleted(false);
//        diceGame.setIsWinner(false);
//        diceGame.setPlayers(player.get());
//        player.get().setDiceGames(diceGames);
//        System.out.println("Voy a guardar este juego: " + diceGame.toString());
//        System.out.println("De este jugador: " + player.get().toString());
//        return diceRepo.save(diceGame);
//    }
}
