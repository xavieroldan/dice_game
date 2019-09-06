package com.dice.api;

import com.dice.model.Game;
import com.dice.model.Player;
import com.dice.repository.PlayerRepository;
import com.dice.tool.ErrorValueException;
import com.dice.tool.GameMaker;
import com.dice.tool.GameMakerSixDice;
import com.dice.tool.ErrorTransactionException;
import com.dice.tool.RateDTO;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
@RestController
public class PlayerRestController
{
    @Autowired
    PlayerRepository playerRepo;
    @Autowired
    HelperRestController helper;

    //Fix the error sending to send to the client for the personalized exceptions
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ErrorTransactionException.class)
    public String return409(ErrorTransactionException ex)
    {
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ErrorValueException.class)
    public String return401(ErrorValueException ex)
    {
        return ex.getMessage();
    }

    /*
    POST: /players : crea un jugador
    localhost:8080/players
    {    
    "name": "Foo"
    }    
     */
    @PostMapping("/players")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Player createPlayer(@RequestBody Player player)
            throws ErrorValueException, ErrorTransactionException
    {
        Player playerChecked = helper.verifyName(player);

        try
        {
            //verify no duplicate name
            if (playerRepo.findByName(playerChecked.getName().trim()) == null)
            {
                //no duplicate: save it
                return playerRepo.save(playerChecked);
            }
            else
            {
                throw new ErrorValueException("El nombre ya existe.");
            }
        }
        catch (Exception e)
        {
            throw new ErrorTransactionException("No fue posible crear el jugador." + e);
        }
    }

    /*
    PUT /players : modifica el nom del jugador
    localhost:8080/players
    {    
    "idPlayer" : "150db883-c08b-4a0d-aa0c-c8607f3f2c93",
    "name": "Martaaaa"
    } 
    Keep the correct format to don't have JSON format problems
     */
    @PutMapping("/players")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public Player editName(@RequestBody Player playerToEdit)
            throws ErrorValueException, ErrorTransactionException
    {
        Player playerChecked = helper.verifyName(playerToEdit);
        try
        {
            //Verify if already exists the new name
            String nameToCheck = playerChecked.getName().trim(); //The name to find
            if (playerRepo.findByName(nameToCheck) == null)
            {
                //Correct name: change it
                Optional<Player> player = playerRepo.findById(playerChecked.getIdPlayer());
                player.get().setName(playerChecked.getName());
                return playerRepo.save(player.get());
            }
            else
            {
                throw new ErrorValueException("El nombre ya existe.");
            }
        }
        catch (Exception e)
        {
            throw new ErrorTransactionException(
                    "No fue posible modificar el jugador indicado." + e);
        }
    }

    /*
    POST /players/{id}/games/ : un jugador específic realitza una tirada dels daus.
    localhost:8080/players/150db883-c08b-4a0d-aa0c-c8607f3f2c93/games/
     */
    @PostMapping(value = "/players/{id}/games/", produces = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Player playGame(@PathVariable UUID id)
            throws ErrorTransactionException
    {
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
            throw new ErrorTransactionException(
                    "No fue posible localizar el jugador con ID: "
                    + id);
        }
    }

    /*
    DELETE /players/{id} elimina el jugador.   
    localhost:8080/players/84cbe2c8-9ab6-466d-ab62-8d2a735d48ec
     */
    @DeleteMapping(value = "/players/{id}", produces = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public ResponseEntity<?> deletePlayer(@PathVariable(value = "id") UUID idPlayer)
            throws ErrorTransactionException
    {
        try
        {
            Optional<Player> player = playerRepo.findById(idPlayer);
            playerRepo.delete(player.get());
            return ResponseEntity.ok().build();
        }
        catch (Exception e)
        {
            throw new ErrorTransactionException(
                    "No fue posible localizar el jugador con ID: "
                    + idPlayer);
        }
    }

    /*
    DELETE /players/{id}/games: elimina les tirades del jugador.
     */
    @DeleteMapping(value = "/players/{id}/games", produces = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public Player deleteGames(@PathVariable(value = "id") UUID idPlayer)
            throws ErrorTransactionException
    {
        try
        {
            Optional<Player> player = playerRepo.findById(idPlayer);
            //Delete the player's games
            player.get().getListGame().clear();
            return playerRepo.save(player.get());
        }
        catch (Exception e)
        {
            throw new ErrorTransactionException(
                    "No es posible borrar jugadas del jugador con ID: "
                    + idPlayer);
        }
    }

    /*
    GET /players/: retorna el llistat de tots els jugadors del sistema
    amb el seu percentatge mig d’èxits
    localhost:8080/players/
     */
    @GetMapping("/players/")
    @ResponseStatus(HttpStatus.OK)
    public List<RateDTO> getListRatePlayers() throws ErrorTransactionException
    {
        //Select the players
        List<Player> listPlayerChecked = helper.verifyListPlayers((List<Player>) playerRepo.findAll());
        //Get the list throw the helper class
        return helper.getListPlayersRateDTO(listPlayerChecked);
    }

    /*
    GET /players/{id}/games: retorna el llistat de jugades per un jugador.
    localhost:8080/players/2da626a9-cee0-47a8-9107-bd2961fc680f/games/
     */
    @GetMapping(value = "/players/{id}/games", produces = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Game> getPlayerGames(@PathVariable(value = "id") UUID idPlayer)
            throws ErrorTransactionException
    {
        try
        {
            return playerRepo.findById(idPlayer).get().getListGame();
        }
        catch (Exception e)
        {
            throw new ErrorTransactionException(
                    "Jugador con ID: " + idPlayer + " sin juegos.");
        }
    }

    /*
    GET /players/ranking/loser: retorna el jugador amb pitjor percentatge d’èxit.
    
     */
    @GetMapping("/players/ranking/loser")
    @ResponseStatus(HttpStatus.OK)
    public RateDTO getLoser() throws ErrorTransactionException
    {

        //find the minim rate
        List<RateDTO> listDTO = helper.sortPlayersByRate();
        double minRate = listDTO.get(0).getRate();
        //find duplicate players with equals loser ratio   
        //TODO: change fori for Iterator
        for (int i = 0; i < listDTO.size(); i++)
        {
            if (listDTO.get(i).getRate() != minRate)
            {
                //delete from the list not min rate
                listDTO.remove(i);
            }
        }
        System.out.println("Perdedores con el mismo ratio: " + listDTO.size());
        listDTO.forEach(player -> System.out.print(player.getPlayer().getIdPlayer() + "\n"));
        if (listDTO.size() > 1)
        {
            //check the player who has more games played
            int countMaxGames = 0;
            int countLoserGames = 0;
            for (RateDTO rateDTO : listDTO)
            {
                int playerGames = rateDTO.getPlayer().getListGame().size();
                if (playerGames > countMaxGames)
                {
                    //Identify the max number of games loser
                    countMaxGames = playerGames;
                    countLoserGames++; // add +1 to the number of losers
                    System.out.println(">>>>>>>>>>>>>" + rateDTO.getPlayer().getIdPlayer()
                            + "/" + countLoserGames);
                }
            }
            if (countLoserGames > 1)
            {
                //Fix the players on equals number of games
                //TODO: change fori for Iterator
                for (int i = 0; i < listDTO.size(); i++)
                {
                    if (!(listDTO.get(i).getPlayer().getListGame().size() == countMaxGames))
                    {
                        listDTO.remove(i);
                    }
                }
                //More than one loser equals game numbers
                listDTO.forEach(rateDTO -> System.out.println(rateDTO.getPlayer().getIdPlayer()));
                //Search the oldest date
                Date oldestDate = Calendar.getInstance().getTime();
                for (RateDTO rateDTO : listDTO)
                {
                    if (rateDTO.getPlayer().getRegDate().before(oldestDate))
                    {
                        //Is older
                        oldestDate = rateDTO.getPlayer().getRegDate();
                    }
                }
                Iterator<RateDTO> iteratorDTO = listDTO.iterator();
                while (iteratorDTO.hasNext())
                {
                    RateDTO iterated = iteratorDTO.next();
                    if (iterated.getPlayer().getRegDate().compareTo(oldestDate) != 0)
                    {
                        //Delete the youngers players
                        iteratorDTO.remove();
                    }
                }
                //unique loser oldest regDate
                RateDTO output = listDTO.get(0);
                return output;
            }
            else
            {
                //Only one loser max games
                System.out.println("Un único loser con " + countMaxGames + " juegos");
                RateDTO output = listDTO.get(0);
                return output;
            }
        }
        else
        {
            //Unique loser: the first on the list
            RateDTO output = helper.sortPlayersByRate().get(0);
            return output;
        }
    }

    /*
    GET /players/ranking/winner: retorna el jugador amb mijor percentatge d’èxit.
    
     */
    @GetMapping("/players/ranking/winner")
    @ResponseStatus(HttpStatus.OK)
    public RateDTO getWinner() throws ErrorTransactionException
    {
        //Find the loser
        RateDTO output = helper.sortPlayersByRate().get(helper.sortPlayersByRate().size() - 1);
        return output;
    }

    /*
    POST /players/{id}/games/six : un jugador específic realitza una tirada dels daus
    amb el joc del sis daus.
    Per poder jugar al joc de daus ara es necessiten sis daus.
    En cas que el resultat de tots els daus sigui sis o cinc, la partida és guanyada,
    sinó es perduda.
    localhost:8080/players/662213d6-b576-4ec6-8b74-79f614606b05/games/six
     */
    @PostMapping(value = "/players/{id}/games/six", produces = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.CREATED)
    public Player playGameSixDice(@PathVariable UUID id)
            throws ErrorValueException, ErrorTransactionException
    {
        try
        {
            //find the player 
            Optional<Player> playerToPlay = playerRepo.findById(id);
            //play and save the game
            GameMakerSixDice game = new GameMakerSixDice();
            return playerRepo.save(game.playGame(playerToPlay.get()));
        }
        catch (Exception e)
        {
            throw new ErrorTransactionException(
                    "No fue posible localizar el jugador con ID: " + id);
        }
    }

    /*
     **************************************************************************
     TODO: delete //GET: TEST List all Players /* localhost:8080/getall
     */
    @GetMapping("/getall")
    public Iterable<Player> testGetAllPlayer()
    {
        return playerRepo.findAll();
    }
}
