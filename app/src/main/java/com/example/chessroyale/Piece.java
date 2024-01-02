package com.example.chessroyale;

abstract class Piece {
    protected int color;
    protected int x,y;
    protected String name;

    public abstract boolean isValidMove(int newX, int newY, ChessBoard board);
}
