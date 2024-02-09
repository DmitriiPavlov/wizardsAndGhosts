package com.wizard.staffs;

import com.badlogic.gdx.utils.TimeUtils;
import com.wizard.ProjectileManager;

public class CandyStaff extends Staff {
    private long timeLastFired;
    public CandyStaff(){
        super.itemTexture = "CandyBlastScroll.png";
    }
    public void fire(float x, float y, float targetX, float targetY){
        timeLastFired = TimeUtils.millis();
        float dx = targetX - x;
        float dy = targetY - y;
        ProjectileManager.createCandyBlast(dx,dy,x,y);
    }
    public boolean canFire(){
        return (TimeUtils.timeSinceMillis(timeLastFired) > 1200);
    }

    public String getItemTexture(){
        System.out.println(itemTexture);
        return itemTexture;
    }
}
