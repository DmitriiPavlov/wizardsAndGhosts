package com.wizard.staffs;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.wizard.ProjectileManager;

public class GhostStaff extends Staff{
    public long timeLastFired;
    @Override
    public void fire(float x, float y, float targetX, float targetY) {
        timeLastFired = TimeUtils.millis();
        float dx = targetX - x;
        float dy = targetY - y;
        Vector2 direction = new Vector2(dx,dy);
        direction.nor();
        ProjectileManager.createTicTac(direction.x,direction.y,x,y);
        direction.rotateDeg(-10);
        ProjectileManager.createTicTac(direction.x,direction.y,x,y);
        direction.rotateDeg(20);
        ProjectileManager.createTicTac(direction.x,direction.y,x,y);

    }

    @Override
    public boolean canFire() {
        return TimeUtils.timeSinceMillis(timeLastFired) > 500;
    }
}
