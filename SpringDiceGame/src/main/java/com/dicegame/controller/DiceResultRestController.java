package com.dicegame.controller;

import com.dicegame.model.DiceGame;
import com.dicegame.model.DiceResult;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.dicegame.repository.DiceResultRepository;
import com.dicegame.repository.DiceGameRepository;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
@RestController
public class DiceResultRestController
{
    @Autowired
    private DiceResultRepository resultRepo;

    @Autowired
    private DiceGameRepository gamesRepo;

    @GetMapping("/dicegames")
    public List<DiceResult> getAllDiceResult()
    {
        return resultRepo.findAll();
    }

    @RequestMapping(value = "/saveresult", headers = "content-type=application/json")
    @ResponseBody
    public DiceResult saveDiceResult(@RequestBody DiceResult diceResult)
    {
        System.out.println("Voy a guardar el diceResult " + diceResult.toString());
        return resultRepo.save(diceResult);
    }

    //TODO: delete this
    @RequestMapping(value = "/saveresultnodata")
    @ResponseBody
    public DiceResult saveNoData(String id)
    {
        DiceResult diceResult = new DiceResult();
        Optional<DiceGame> diceGame = gamesRepo.findById("64bb48a7004c4e2bb1e8d13c04b3b340");
        diceResult.setIdResult(UUID.randomUUID().toString().replace("-", ""));
        diceResult.setDiceGame(diceGame.get());
        diceResult.setIdDice(1);
        diceResult.setResult(3);
        System.out.println("Voy a guardar esta jugada: " + diceResult.toString());
        System.out.println("De este juego: " + diceGame.get().toString());
        return resultRepo.save(diceResult);
    }

    public DiceResult saveResult(DiceGame diceGame, DiceResult diceResult)
    {
        diceResult.setDiceGame(diceGame);
        return resultRepo.save(diceResult);
    }
}
