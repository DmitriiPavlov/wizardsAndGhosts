package com.wizard.enemies;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.wizard.Enemy;
import com.wizard.Wizard;
import com.wizard.staffs.Staff;

public class EnemyShooter extends Enemy {
    public Staff weapon;

    public EnemyShooter(float x, float y, float width, float height, Staff weapon) {
        super(x, y, width, height);
        this.weapon = weapon;
    }

    public EnemyShooter(float x, float y, float width, float height) {
        super(x, y, width, height);
        this.weapon = null;
    }
    @Override
    public void act(float deltaTime) {
        super.act(deltaTime);
        float xDist = this.getX() - Wizard.w.player.getX();
        float yDist = this.getY() - Wizard.w.player.getY();
        if (lockOnRange*lockOnRange > xDist*xDist + yDist*yDist) {
            if (weapon != null && weapon.canFire()) {
                Rectangle target = new Rectangle();
                Wizard.w.player.getHitbox(target);
                Vector2 targetVec = new Vector2();
                target.getCenter(targetVec);


                weapon.fire(this.centerX(), this.centerY(), Wizard.w.player.centerX(), Wizard.w.player.centerY());
            }
        }
    }
}
