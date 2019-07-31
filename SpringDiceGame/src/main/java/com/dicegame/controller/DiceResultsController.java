package com.dicegame.controller;

import com.dicegame.model.DiceResults;
import com.dicegame.repository.DiceResultsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
public class DiceResultsController
{

    @Autowired
    private DiceResultsRepository repository;

    @GetMapping("/dicegames")
    public List<DiceResults> getAllDiceResults()
    {
        return repository.findAll();
    }

}
