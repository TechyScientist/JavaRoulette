package net.johnnyconsole.javaroulette;

import javafx.scene.control.Button;
/**
 * @author Johnny Console
 * Project: JavaRoulette
 * Class: RouletteTile.java
 * Purrpose: ADT for the tiles,
 * holds tile data
 * Created: 16 May 2019
 */
public class RouletteTile extends Button {
    int n;
    boolean isMine;

    RouletteTile(int n, int rand) {
        super(n + "");
        this.setPrefHeight(100);
        this.setPrefHeight(100);
        this.n = n;
        isMine = rand > 75;
    }

}
