package com.example.spaceexplorerandroidapp.Model;

public abstract class GameObject {
    private int row;
    private int col;
    private int image;

    public int getRow() {
        return row;
    }

    public GameObject setRow(int row) {
        this.row = row;
        return this;
    }

    public int getCol() {
        return col;
    }

    public GameObject setCol(int col) {
        this.col = col;
        return this;
    }

    public int getImage() {
        return image;
    }

    public GameObject setImage(int image) {
        this.image = image;
        return this;
    }
}
