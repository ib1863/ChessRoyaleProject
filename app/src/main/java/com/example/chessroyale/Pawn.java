package com.example.chessroyale;

class Pawn extends Piece {
    @Override
    public boolean isValidMove(int newX, int newY, ChessBoard board) {
        return false;
    }
}
