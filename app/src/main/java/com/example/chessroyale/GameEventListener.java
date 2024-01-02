package com.example.chessroyale;

interface GameEventListener {
    void onCheck();
    void onCheckMate();
    void onStaleMate();
    void onEnPassent();
    void onPromotion();
    void onCastling();
    void onDraw();
}
