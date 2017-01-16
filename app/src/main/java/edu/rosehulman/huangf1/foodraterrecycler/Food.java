package edu.rosehulman.huangf1.foodraterrecycler;

/**
 * Created by huangf1 on 1/6/2017.
 */

public class Food {

    private String mName;
    private int resourceID;
    private float rating;

    public Food(String mName, int resourceID, float rating) {
        this.mName = mName;
        this.resourceID = resourceID;
        this.rating = rating;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public int getResourceID() {
        return resourceID;
    }

    public void setResourceID(int resourceID) {
        this.resourceID = resourceID;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
