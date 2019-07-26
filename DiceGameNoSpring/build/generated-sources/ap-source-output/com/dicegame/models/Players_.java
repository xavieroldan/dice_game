package com.dicegame.models;

import com.dicegame.models.DiceRolls;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-07-26T10:58:13")
@StaticMetamodel(Players.class)
public class Players_ { 

    public static volatile ListAttribute<Players, DiceRolls> diceRollsList;
    public static volatile SingularAttribute<Players, String> name;
    public static volatile SingularAttribute<Players, Date> regDate;
    public static volatile SingularAttribute<Players, Integer> idplayers;

}