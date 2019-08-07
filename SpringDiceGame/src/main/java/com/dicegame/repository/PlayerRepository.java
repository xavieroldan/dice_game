package com.dicegame.repository;

import com.dicegame.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
@Repository
public interface PlayerRepository extends JpaRepository<Player, String>
{

}
