/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dice.tools;

import com.dice.models.RateDTO;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
public class SortRanking
{
//https://stackoverflow.com/questions/27530332/compareto-method-with-doublesdouble-cannot-be-dereferenced

    public List<RateDTO> orderListRateDTO(List<RateDTO> players)
    {
        Collections.sort(players);
        return players;
    }

}
