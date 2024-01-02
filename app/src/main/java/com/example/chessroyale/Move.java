package com.example.chessroyale;

class Move {
    private Piece piece;
    private int startX, startY, endX, endY;
    public Move(Piece piece, int startX, int startY, int endX, int endY){
        this.piece = piece;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }
}
