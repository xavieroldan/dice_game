package com.dicegame.controller;

import com.dicegame.model.DiceGame;
import com.dicegame.model.Player;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dicegame.tools.GameMaker;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.dicegame.repository.PlayerRepository;
import com.dicegame.repository.DiceGameRepository;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
@RestController
public class DiceGameRestController
{
    @Autowired
    private DiceGameRepository diceRepo;
    @Autowired
    private PlayerRepository playerRepo;

    @GetMapping("/dicerolls")
    public List<DiceGame> getAlldiceRoll()
    {
        return diceRepo.findAll();
    }

    //POST /players/{id}/games/ : un jugador específic realitza una tirada dels daus.
    @RequestMapping(value = "/players/{id}/games/", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public DiceGame playNewGame(@PathVariable String id)
    {
        DiceGame diceGame = new DiceGame();
        //create a new game
        GameMaker gameMaker = new GameMaker();
        diceGame = gameMaker.playGame(playerRepo.findById(id).get());
        return diceRepo.save(diceGame); //save the game
    }

    //TODO: To delete
    @RequestMapping(value = "/savegame", headers = "content-type=application/json")
    @ResponseBody
    public DiceGame saveDiceResult(@RequestBody DiceGame diceGame)
    {
        System.out.println("Voy a guardar el diceGame " + diceGame.toString());
        return diceRepo.save(diceGame);
    }

    //TODO: To delete
    @RequestMapping(value = "/savegamenodata")
    @ResponseBody
    public DiceGame saveNoData(String id)
    {
        Set<DiceGame> diceGames = new HashSet<>();
        DiceGame diceGame = new DiceGame();
        Optional<Player> player = playerRepo.findById("ed55edff1997406da1be45c99517871e");
        diceGame.setIdGame(UUID.randomUUID().toString().replace("-", ""));
        diceGame.setIsAnonim(true);
        diceGame.setIsDeleted(false);
        diceGame.setIsWinner(false);
        diceGame.setPlayer(player.get());
        player.get().setDiceGame(diceGames);
        System.out.println("Voy a guardar este juego: " + diceGame.toString());
        System.out.println("De este jugador: " + player.get().toString());
        return diceRepo.save(diceGame);
    }
}
