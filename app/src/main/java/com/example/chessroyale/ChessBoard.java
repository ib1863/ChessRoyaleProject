package com.example.chessroyale;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

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

