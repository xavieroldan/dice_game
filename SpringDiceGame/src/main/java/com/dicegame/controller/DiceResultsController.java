package com.dicegame.controller;

import com.dicegame.model.DiceGames;
import com.dicegame.model.DiceResults;
import com.dicegame.repository.DiceGamesRepository;
import com.dicegame.repository.DiceResultsRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
@RestController
public class DiceResultsController
{
    @Autowired
    private DiceResultsRepository repository;

    @Autowired
    private DiceGamesRepository gamesRepo;

    @GetMapping("/dicegames")
    public List<DiceResults> getAllDiceResults()
    {
        return repository.findAll();
    }

    @RequestMapping(value = "/saveresult", headers = "content-type=application/json")
    @ResponseBody
    public DiceResults saveDiceResult(@RequestBody DiceResults diceResult)
    {
        System.out.println("Voy a guardar el diceResult " + diceResult.toString());
        return repository.save(diceResult);
    }

    @RequestMapping(value = "/saveresultnodata")
    @ResponseBody
    public DiceResults saveNoData(String id)
    {
        DiceResults diceResults = new DiceResults();
        Optional<DiceGames> diceGame = gamesRepo.findById("64bb48a7004c4e2bb1e8d13c04b3b340");
        diceResults.setIdResults(UUID.randomUUID());
        diceResults.setDiceGames(diceGame.get());
        diceResults.setIdDice(1);
        diceResults.setResult(3);
        System.out.println("Voy a guardar esta jugada: " + diceResults.toString());
        System.out.println("De este juego: " + diceGame.get().toString());
        return repository.save(diceResults);
    }

    public DiceResults saveResult(DiceGames diceGame, DiceResults diceResult)
    {
        diceResult.setDiceGames(diceGame);
        return repository.save(diceResult);
    }
}
