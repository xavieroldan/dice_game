package com.dice.repository;

import com.dice.model.Game;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
public interface GameRepository extends CrudRepository<Game, UUID>
{
    @Query(value = "SELECT * FROM game", nativeQuery = true)
    List<Game> findAllGames();

    @Query(value = "SELECT COUNT(idgame) FROM game", nativeQuery = true)
    double countAllGames();

    @Query(value = "SELECT * FROM game WHERE winner = true", nativeQuery = true)
    List<Game> findAllWinGames();

    @Query(value = "SELECT COUNT(idgame) FROM game WHERE winner = true", nativeQuery = true)
    double countAllWinGames();
}
