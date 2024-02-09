package com.wizard.staffs;

import com.badlogic.gdx.utils.TimeUtils;
import com.wizard.ProjectileManager;

public class ChocBarStaff extends Staff {
    private long timeLastFired;
    public ChocBarStaff(){
        super.itemTexture = "ChocBarWHitBox.png";
    }
    public void fire(float x, float y, float dx, float dy){
        timeLastFired = TimeUtils.millis();
        ProjectileManager.createChocBar(dx,dy,x,y);
    }
    public boolean canFire(){
        return (TimeUtils.timeSinceMillis(timeLastFired) > 700);
    }

    public String getItemTexture() {
        System.out.println(itemTexture);
        return itemTexture;
    }

}