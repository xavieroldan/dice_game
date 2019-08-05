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
import org.springframework.web.bind.annotation.PostMapping;
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
    @RequestMapping(value = "/players/games", headers = "content-type=application/json")
    @ResponseBody
    public DiceGames playGame(@RequestBody Players player)
    {
        System.out.println("Estoy en playGame de DiceGames Controller");
        DiceGames diceGame = new DiceGames();
        //crear una jugada
        GameMaker gameMaker = new GameMaker();
        diceGame = gameMaker.playGame(playerRepo.findById(player.getIdPlayers()).get());
        System.out.println("Voy a guardar la juagada: "
                + diceGame.toString());
        //guardar la jugada  
//        diceRepo.save(diceGame);
//        return ResponseEntity.ok().build();
        return diceRepo.save(diceGame);

    }

    @RequestMapping(value = "/savegame", headers = "content-type=application/json")
    @ResponseBody
    public DiceGames saveDiceResult(@RequestBody DiceGames diceGame)
    {
        System.out.println("Voy a guardar el diceGame " + diceGame.toString());
        return diceRepo.save(diceGame);
    }

    @RequestMapping(value = "/savegamenodata")
    @ResponseBody
    public DiceGames saveNoData(String id)
    {
        Set<DiceGames> diceGames = new HashSet<>();
        DiceGames diceGame = new DiceGames();
        Optional<Players> player = playerRepo.findById("ed55edff1997406da1be45c99517871e");
        diceGame.setIdGames(UUID.randomUUID().toString().replace("-", ""));
        diceGame.setIsAnonim(true);
        diceGame.setIsDeleted(false);
        diceGame.setIsWinner(false);
        diceGame.setPlayers(player.get());
        player.get().setDiceGames(diceGames);
        System.out.println("Voy a guardar este juego: " + diceGame.toString());
        System.out.println("De este jugador: " + player.get().toString());
        return diceRepo.save(diceGame);
    }
}
