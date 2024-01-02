package com.example.chessroyale;

class Queen extends Piece {
    @Override
    public boolean isValidMove(int newX, int newY, ChessBoard board) {
        return false;
    }
}
