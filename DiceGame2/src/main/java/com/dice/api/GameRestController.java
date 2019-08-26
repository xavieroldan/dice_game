package com.dice.api;

import com.dice.model.Game;
import com.dice.repository.GameRepository;
import com.dice.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
@RestController
public class GameRestController
{
    @Autowired
    GameRepository gameRepo;
    @Autowired
    PlayerRepository playerRepo;

//POST: create a game
    /*
    localhost:8080/newgame    
    {
        "isAnonim": "false",
        "isWinner": "false",
        "isDeleted": "false"
    }
     */
    @PostMapping("/newgame")
    @ResponseBody
    public Game createPlayer(@RequestBody Game game)
    {
        return gameRepo.save(game);
    }

    //GET: List all Games
    /*
    localhost:8080/getallgame
     */
    @GetMapping("/getallgame")
    public Iterable<Game> getAllGame()
    {
        return gameRepo.findAll();
    }
}
