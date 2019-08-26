package com.dice.api;

import com.dice.model.Game;
import com.dice.model.Player;
import com.dice.repository.PlayerRepository;
import com.dice.tool.ErrorValueException;
import com.dice.tool.GameMaker;
import com.dice.tool.RateDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    /*
    POST /players/{id}/games/ : un jugador específic realitza una tirada dels daus.
    localhost:8080/players/150db883-c08b-4a0d-aa0c-c8607f3f2c93/games/
     */
    @RequestMapping(value = "/players/{id}/games/", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Player playGame(@PathVariable UUID id) throws ErrorValueException
    {
        if (id == null)
        {
            throw new ErrorValueException();
        }
        try
        {
            //Verify if player exists
            Optional<Player> playerToPlay = playerRepo.findById(id);
            //play and save the game
            GameMaker game = new GameMaker();
            return playerRepo.save(game.playGame(playerToPlay.get()));
        }
        catch (Exception e)
        {
            throw new ErrorValueException();
        }
    }

    /*
    DELETE /players/{id} elimina el jugador.   
    localhost:8080/players/84cbe2c8-9ab6-466d-ab62-8d2a735d48ec
     */
    @DeleteMapping(value = "/players/{id}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<?> deletePlayer(@PathVariable(value = "id") UUID idPlayer) throws ErrorValueException
    {
        if (idPlayer == null)
        {
            throw new ErrorValueException();
        }
        try
        {
            Optional<Player> player = playerRepo.findById(idPlayer);
            playerRepo.delete(player.get());
            return ResponseEntity.ok().build();
        }
        catch (Exception e)
        {
            throw new ErrorValueException();
        }
    }

    /*
    DELETE /players/{id}/games: elimina les tirades del jugador.
     */
    @DeleteMapping(value = "/players/{id}/games", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Player deleteGames(@PathVariable(value = "id") UUID idPlayer) throws ErrorValueException
    {
        if (idPlayer == null)
        {
            throw new ErrorValueException();
        }
        try
        {
            Optional<Player> player = playerRepo.findById(idPlayer);
            //Delete the player's games
            player.get().getListGame().clear();
            return playerRepo.save(player.get());
        }
        catch (Exception e)
        {
            throw new ErrorValueException();
        }
    }

    /*
    GET /players/: retorna el llistat de tots els jugadors del sistema
    amb el seu percentatge mig d’èxits
    localhost:8080/players/
     */
    @GetMapping("/players/")
    public List<RateDTO> getListRatePlayers() throws ErrorValueException
    {
        List<RateDTO> outputDTO = new ArrayList<>();
        //Select the players
        Iterable<Player> listPlayer = playerRepo.findAll();
        if (listPlayer == null)
        {
            throw new ErrorValueException();
        }
        for (Player player : listPlayer)
        {
            //Count the games
            double games = player.getListGame().size();
            //Count the wins
            List<Game> listGame = player.getListGame();
            double wins = 0;
            if (listGame.size() != 0)
            {
                for (Game game : listGame)
                {
                    if (game.getIsWinner())
                    {
                        wins++;
                    }
                }
                //Calculate and output the results
                double result = (wins / games) * 100;
                RateDTO resultDTO = new RateDTO(player.getIdPlayer(), result);
                outputDTO.add(resultDTO);
            }
            else
            {
                //No games rate 0%
                RateDTO resultDTO = new RateDTO(player.getIdPlayer(), 0);
                outputDTO.add(resultDTO);
            }
        }
        return outputDTO;
    }

    /**
     * *************************************************************************
     * POST: create a player (Only needs the name) localhost:8080/new { "name":
     * "Foo" }
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
