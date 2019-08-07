package com.dicegame.repository;

import com.dicegame.model.Players;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
@Repository
public interface PlayersRepository extends JpaRepository<Players, UUID>
{

}
