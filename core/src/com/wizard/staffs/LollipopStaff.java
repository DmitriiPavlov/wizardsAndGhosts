package com.wizard.staffs;

import com.badlogic.gdx.utils.TimeUtils;
import com.wizard.ProjectileManager;

public class LollipopStaff extends Staff {
    private long timeLastFired;
    public LollipopStaff(){
        super.itemTexture = "LolipopWHitBox.png";
    }
    public void fire(float x, float y, float dx, float dy){
        timeLastFired = TimeUtils.millis();
        ProjectileManager.createLollipop(dx,dy,x,y);
    }
    public boolean canFire(){
        return (TimeUtils.timeSinceMillis(timeLastFired) > 900);
    }
    public String getItemTexture() {
        System.out.println(itemTexture);
        return itemTexture;
    }

}