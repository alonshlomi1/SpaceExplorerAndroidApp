package com.example.spaceexplorerandroidapp.Logic;

import android.util.Log;

import com.example.spaceexplorerandroidapp.Model.Asteroid;
import com.example.spaceexplorerandroidapp.Model.Coin;
import com.example.spaceexplorerandroidapp.Model.GameObject;
import com.example.spaceexplorerandroidapp.Model.Spaceship;
import com.example.spaceexplorerandroidapp.R;

import java.util.ArrayList;
import java.util.Random;

public class GameManager {
    private  int gridRows;
    private  int gridCols;
    private int score = 0;
    private int crushes = 0;
    private int life;
    private ArrayList<GameObject> gameObjectList;
    private Spaceship spaceship;


    public GameManager() {
        this.life = 3;
        setStart();
    }

    public GameManager(int life, int numRows, int numCols) {
        this.life = life;
        this.gridRows = numRows;
        this.gridCols = numCols;
    }

    public GameManager setStart() {
        this.spaceship = new Spaceship();
        this.spaceship.setCol(2);
        this.spaceship.setImage(R.drawable.spaceship1);
        gameObjectList = new ArrayList<>();
        return this;
    }
    public int getScore() {
        return score;
    }

    public GameManager setScore(int score) {
        this.score = score;
        return this;
    }

    public int getCrushes() {
        return crushes;
    }

    public GameManager setCrushes(int crushes) {
        this.crushes = crushes;
        return this;
    }

    public int getLife() {
        return life;
    }

    public GameManager setLife(int life) {
        this.life = life;
        return this;
    }

    public ArrayList<GameObject> getGameObjectList() {
        return gameObjectList;
    }

    public GameManager setGameObjectList(ArrayList<GameObject> gameObjectList) {
        this.gameObjectList = gameObjectList;
        return this;
    }

    public Spaceship getSpaceship() {
        return spaceship;
    }

    public GameManager setSpaceship(Spaceship spaceship) {
        this.spaceship = spaceship;
        return this;
    }

    public void moveSpaceship(int i) {
        if(spaceship.getCol() + i < gridCols && spaceship.getCol() + i >= 0 ){
            spaceship.setCol(spaceship.getCol() + i);
        }
    }
    public void randomNewAsteroid(){
        Random random = new Random();
        if(random.nextBoolean()){
            gameObjectList.add((Asteroid) new Asteroid()
                    .setRow(0)
                    .setCol(random.nextInt(gridCols))
                    .setImage(Asteroid.getSrcList()[random.nextInt(Asteroid.getSrcList().length)]));
        }
        else if(random.nextBoolean()){
            gameObjectList.add((Coin) new Coin()
                    .setRow(0)
                    .setCol(random.nextInt(gridCols))
                    .setImage(Coin.getSrc()));
        }

    }

    public void nextFrame() {
        for (int i=0; i< gameObjectList.size(); i++){
            if(gameObjectList.get(i).getRow() + 1 == gridRows) {
                if(gameObjectList.get(i) instanceof Asteroid)
                    updateScore(Asteroid.getPoints());
                gameObjectList.remove(i);
                i--;
            }
            else
                gameObjectList.get(i).setRow(gameObjectList.get(i).getRow() + 1);
        }
    }

    private void updateScore(int points) {
        score += points;
    }

    public int checkCrush() {
        for (int i=0; i< gameObjectList.size(); i++){
            if(gameObjectList.get(i).getRow() + 1 == gridRows ){
                if(gameObjectList.get(i).getCol() == spaceship.getCol()) {

                    Log.d("@@@@@@@@@@@@@@@@@@@@@@@@@", gameObjectList.get(i).getClass().getName());
                    if(gameObjectList.get(i) instanceof Asteroid)
                        crushes = crushes + 1;
                    else if (gameObjectList.get(i) instanceof Coin)
                        updateScore(Coin.getPoints());
                    int flag = gameObjectList.get(i) instanceof Asteroid? 1 : 2;
                    gameObjectList.remove(i);
                    return flag;

                }
            }
        }
        return 0;
    }


    public int getRandomCrushSrc() {
        Random random = new Random();
        return Spaceship.getCrushSrcList()[random.nextInt(Spaceship.getCrushSrcList().length)];
    }
}
