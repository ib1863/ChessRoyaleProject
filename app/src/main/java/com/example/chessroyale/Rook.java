package com.example.chessroyale;

class Rook extends Piece {
    public Rook(int color, int x, int y){
        this.color = color;
        this.x = x;
        this.y = y;
        this.name = "Rook";
    }


    @Override
    public boolean isValidMove(int newX, int newY, ChessBoard board) {
        return false;
    }
}
