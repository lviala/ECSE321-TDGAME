package entities;

/**
 * Created by Francis O'Brien - 3/10/2015 - 10:45 AM
 */

public class Player {

    private int currency;
    private int lives;

    public Player(int currency, int lives){
        this.currency = currency;
        this.lives = lives;
    }

    public void updateCurrency(int value){
        currency += value;
    }

    public void removeLife(){
        lives--;
    }

    public int getCurrency(){
        return currency;
    }

    public int getLives(){
        return lives;
    }

}
