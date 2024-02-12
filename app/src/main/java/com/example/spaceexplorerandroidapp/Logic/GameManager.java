package com.example.spaceexplorerandroidapp.Logic;

import com.example.spaceexplorerandroidapp.Model.Asteroid;
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
    private ArrayList<Asteroid> asteroidList;
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
        asteroidList = new ArrayList<>();
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

    public ArrayList<Asteroid> getAsteroidList() {
        return asteroidList;
    }

    public GameManager setAsteroidList(ArrayList<Asteroid> asteroidList) {
        this.asteroidList = asteroidList;
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
            asteroidList.add((Asteroid) new Asteroid()
                    .setRow(0)
                    .setCol(random.nextInt(gridCols))
                    .setImage(Asteroid.getSrcList()[random.nextInt(Asteroid.getSrcList().length)]));
        }
    }

    public void nextFrame() {
        for (int i=0; i< asteroidList.size(); i++){
            if(asteroidList.get(i).getRow() + 1 == gridRows) {
                updateScore(Asteroid.getPoints());
                asteroidList.remove(i);
                i--;
            }
            else
                asteroidList.get(i).setRow(asteroidList.get(i).getRow() + 1);
        }
    }

    private void updateScore(int points) {
        score += points;
    }

    public boolean checkCrush() {
        for (int i=0; i< asteroidList.size(); i++){
            if(asteroidList.get(i).getRow() + 1 == gridRows ){
                if(asteroidList.get(i).getCol() == spaceship.getCol()) {
                    crushes = crushes == (life-1)? 0 : crushes + 1;
                    asteroidList.remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    public int getRandomCrushSrc() {
        Random random = new Random();
        return Spaceship.getCrushSrcList()[random.nextInt(Spaceship.getCrushSrcList().length)];
    }
}
