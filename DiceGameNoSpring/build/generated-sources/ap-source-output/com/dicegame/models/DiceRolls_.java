package com.dicegame.models;

import com.dicegame.models.DiceResults;
import com.dicegame.models.Players;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-07-30T11:52:26")
@StaticMetamodel(DiceRolls.class)
public class DiceRolls_ { 

    public static volatile ListAttribute<DiceRolls, DiceResults> diceResultsList;
    public static volatile SingularAttribute<DiceRolls, Boolean> isWinner;
    public static volatile SingularAttribute<DiceRolls, Players> playersIdplayers;
    public static volatile SingularAttribute<DiceRolls, String> idRoll;
    public static volatile SingularAttribute<DiceRolls, Boolean> isAnonim;

}