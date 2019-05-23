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
class RouletteTile extends Button {
    int n,x,y, rand;
    boolean isMine;

    RouletteTile(int n, int rand, int x, int y) {
        super(n + "");
        this.setPrefHeight(100);
        this.setPrefWidth(100);
        this.n = n;
        this.rand = rand;
        this.x = x;
        this.y = y;
        isMine = rand > 75;
    }

}
