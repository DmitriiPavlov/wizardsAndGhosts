package com.wizard.staffs;

import com.badlogic.gdx.utils.TimeUtils;
import com.wizard.ProjectileManager;

public class CandyStaff extends Staff {
    private long timeLastFired;
    public CandyStaff(){
        super.itemTexture = "GreenCandyWHitBox.png";
    }
    public void fire(float x, float y, float dx, float dy){
        timeLastFired = TimeUtils.millis();
        ProjectileManager.createCandyBlast(dx,dy,x,y);
    }
    public boolean canFire(){
        return (TimeUtils.timeSinceMillis(timeLastFired) > 100);
    }

    public String getItemTexture(){
        System.out.println(itemTexture);
        return itemTexture;
    }
}
