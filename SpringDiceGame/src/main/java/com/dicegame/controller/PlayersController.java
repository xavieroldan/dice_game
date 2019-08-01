package com.dicegame.controller;

import com.dicegame.model.Players;
import com.dicegame.repository.PlayersRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayersController
{
    @Autowired
    private PlayersRepository repository;

    //GET /players/: retorna el llistat de tots els jugadors del sistema 
    //amb el seu percentatge mig d’èxits
    @GetMapping("/players")
    public List<Players> getAllPlayers()
    {
//        return repository.findAll();
        return repository.findAll();
    }

    //POST: /players : crea un jugador
    @RequestMapping(value = "/players", headers = "content-type=application/json")
    @ResponseBody
    public Players createPlayers(@RequestBody Players player)
    {
        return repository.save(player);
    }

    //PUT /players : modifica el nom del jugador
    @PutMapping("/players")
    public Players updatePlayers(@RequestBody Players player)
    {
        String idPlayer = player.getIdPlayers();
        Optional<Players> playerToUpdate = repository.findById(idPlayer);
        playerToUpdate.get().setName(idPlayer);
        Players playerUpdated = repository.save(playerToUpdate.get());

        return playerUpdated;
    }

    //DELETE /players/{id} elimina el jugador.
    @DeleteMapping("/players/{id}")
    public ResponseEntity<?> deletePlayer(@PathVariable(value = "id") String idPlayer)
    {
        Optional<Players> player = repository.findById(idPlayer);
        repository.delete(player.get());
        return ResponseEntity.ok().build();
    }

}
