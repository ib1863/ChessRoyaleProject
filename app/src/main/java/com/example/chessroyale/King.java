package com.example.chessroyale;

class King extends Piece {
    @Override
    public boolean isValidMove(int newX, int newY, ChessBoard board) {
        return false;
    }
}
