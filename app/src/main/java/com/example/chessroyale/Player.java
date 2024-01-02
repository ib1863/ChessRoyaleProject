package com.example.chessroyale;

import java.util.LinkedList;
import java.util.List;

public class Player {
    private String name;
    private int color;
    private List<Piece> pieces;

    public Player(String name, int color){
        this.name = name;
        this.color = color;
        this.pieces = new LinkedList<>();
    }
}
