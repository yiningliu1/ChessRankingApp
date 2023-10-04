package com.example.chessapp;

public class Game {
    private String loser;
    private String winner;

    public Game(String win, String los){
        loser = los;
        winner = win;
    }

    public String getLoser() {
        return loser;
    }

    public String getWinner() {
        return winner;
    }
    public void setLoser(String loser) {
        this.loser = loser;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

}
