package com.example.chessroyale;

class ChessBoard implements ChessRules {
    private Piece[][] board;
    public ChessBoard(){

    }

    public boolean isMoveValid(Move move){
        return false;
    }

    public void makeMove(Move move){

    }

    public boolean isCheckmate(){
        return false;
    }
    public boolean isSelfCheck(){
        return false;
    }
}
