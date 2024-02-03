package com.wizard.staffs;

public abstract class Staff {
    // in milliseconds (1/1000 of a second)
    public long timeLastFired;
    //in fires per second
    public long fireRate;

    public abstract void fire(float x, float y, float targetX, float targetY);
}
