package com.dice.apiRest;

import com.dice.model.Player;
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
public class PlayerRestController
{
    @Autowired
    PlayerRepository playerRepo;

    //POST: create a player
    /*(Only needs the name)
    localhost:8080/new    
    {    
    "name": "Foo"
    }
     */
    @PostMapping("/new")
    @ResponseBody
    public Player createPlayer(@RequestBody Player player)
    {
        return playerRepo.save(player);
    }

    //GET: List all Players
    /*
    localhost:8080/getall
     */
    @GetMapping("/getall")
    public Iterable<Player> getAllPlayer()
    {
        return playerRepo.findAll();
    }
}
