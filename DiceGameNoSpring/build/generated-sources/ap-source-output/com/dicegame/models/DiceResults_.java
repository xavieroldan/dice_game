package com.dicegame.models;

import com.dicegame.models.DiceResultsPK;
import com.dicegame.models.DiceRolls;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-07-26T10:58:13")
@StaticMetamodel(DiceResults.class)
public class DiceResults_ { 

    public static volatile SingularAttribute<DiceResults, Integer> rollResult;
    public static volatile SingularAttribute<DiceResults, DiceResultsPK> diceResultsPK;
    public static volatile SingularAttribute<DiceResults, DiceRolls> diceRolls;

}