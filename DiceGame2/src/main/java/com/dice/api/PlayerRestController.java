package com.dice.api;

import com.dice.model.Player;
import com.dice.repository.PlayerRepository;
import com.dice.tool.ErrorValueException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    /*
    POST: /players : crea un jugador
    {    
    "name": "Foo"
    }    
     */
    @PostMapping("/players")
    @ResponseBody
    public Player createPlayer(@RequestBody Player player) throws ErrorValueException
    {
        if (player == null)
        {
            throw new ErrorValueException();
        }
        try
        {
            return playerRepo.save(player);
        }
        catch (Exception e)
        {
            throw new ErrorValueException();
        }
    }

    /*
    PUT /players : modifica el nom del jugador
    {    
    "idPlayer" : "150db883-c08b-4a0d-aa0c-c8607f3f2c93",
    "name": "Martaaaa"
    } 
    Keep the correct format to no have format JSON problems
     */
    @PutMapping("/players")
    @ResponseBody
    public Player editName(@RequestBody Player playerToEdit) throws ErrorValueException
    {
        if (playerToEdit == null)
        {
            throw new ErrorValueException();
        }
        try
        {
            Optional<Player> player = playerRepo.findById(playerToEdit.getIdPlayer());
            player.get().setName(playerToEdit.getName());
            return playerRepo.save(player.get());
        }
        catch (Exception e)
        {
            throw new ErrorValueException();
        }
    }

    //TODO: delete
    //POST: create a player
    /*(Only needs the name)
    localhost:8080/new    
    {    
    "name": "Foo"
    }
     */
    @PostMapping("/new")
    @ResponseBody
    public Player testCreatePlayer(@RequestBody Player player)
    {
        return playerRepo.save(player);
    }

    //TODO: delete
    //GET: TEST List all Players
    /*
    localhost:8080/getall
     */
    @GetMapping("/getall")
    public Iterable<Player> testGetAllPlayer()
    {
        return playerRepo.findAll();
    }

}
