package com.dicegame.repository;

import com.dicegame.model.DiceResults;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
public interface DiceResultsRepository extends JpaRepository<DiceResults, Long>
{

}
