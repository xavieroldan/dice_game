package com.dicegame.repository;

import com.dicegame.model.DiceGames;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
@Repository
public interface DiceGamesRepository extends JpaRepository<DiceGames, String>
{

}
