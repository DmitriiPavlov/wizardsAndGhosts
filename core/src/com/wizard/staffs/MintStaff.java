package com.wizard.staffs;

import com.badlogic.gdx.utils.TimeUtils;
import com.wizard.ProjectileManager;

public class MintStaff extends Staff {
    private long timeLastFired;
    public MintStaff(){
        super.itemTexture = "MintScroll.png";
    }
    public void fire(float x, float y,float targetX, float targetY){
        timeLastFired = TimeUtils.millis();
        float dx = targetX - x;
        float dy = targetY - y;
        ProjectileManager.createMint(dx,dy,x,y);
    }
    public boolean canFire(){
        return (TimeUtils.timeSinceMillis(timeLastFired) > 100);
    }
    public String getItemTexture() {
        return itemTexture;
    }

}