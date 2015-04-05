package entities;

import core.gamestates.Play;

/**
 * Created by Francis O'Brien - 3/10/2015 - 10:45 AM
 */

public class Player {

    private int currency;
    private int lives;
    Play playState;

    public Player(int currency, int lives, Play playstate){
        this.currency = currency;
        this.lives = lives;
        this.playState = playstate;
    }

    public void updateCurrency(int value){
        currency += value;
    }

    public void removeLife(){
        if (--lives <= 0){
            playState.lostGame();
        }
    }

    public int getCurrency(){
        return currency;
    }

    public int getLives(){
        return lives;
    }

}
