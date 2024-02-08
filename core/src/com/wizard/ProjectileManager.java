package com.wizard;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

public class ProjectileManager {
    //manages projectiles and their trajectories
    public static World stage;

    public static class Projectile extends Entity {
        public float dx, dy, height, width;
        public float range;
        public float distanceTraveled = 0;
        boolean hitsEnemies;
        public long timeCreated;
        public long lifeTime;

        public Projectile(String textureName) {
            super(0, 0, textureName);
            timeCreated = TimeUtils.millis();
            //i am just making a default lifetime of 15 seconds this can be changed by different projetiles however
            lifeTime = 3 * 1000;
        }

        public void act(float deltaTime) {
            if (hitsEnemies) {
                Enemy collided = CollisionManager.isCollidingEnemy(this);

                if (collided != null) {
                    //here I would subtract the hp
                    collided.updateHP(collided.HP - 1);
                    World.currentLevel.removeActor(this);
                    return;
                }
            }

            if (TimeUtils.timeSinceMillis(timeCreated)>= lifeTime){
                World.currentLevel.removeActor(this);
            }

            this.interpolateMotion(deltaTime);
        }

        public void interpolateMotion(float deltaTime) {
            int interpolationConstant = (int) (Math.sqrt(dx * dx + dy * dy));
            for (int i = 0; i < interpolationConstant; i++) {
                this.moveBy(deltaTime * dx / interpolationConstant, deltaTime * dy / interpolationConstant);
                if (CollisionManager.isHittingBlock(this)) {
                    this.getParent().removeActor(this);
                    return;
                }

            }
            distanceTraveled += Math.sqrt(dx * dx + dy * dy) * deltaTime;
            if (distanceTraveled > range) {
                World.currentLevel.removeActor(this);
                return;
            }
        }

    }

    public static void createMint(float dx, float dy, float x, float y) {
        //mint should have a speed of like 20
        float currSpeed = (float) Math.sqrt(dx * dx + dy * dy);
        float speedRatio = 20 / currSpeed;

        Projectile out = new Projectile("Projectile.png");
        out.range = 5;
        out.dx = dx * speedRatio;
        out.dy = dy * speedRatio;
        out.setBounds(x, y, 0.5F, 0.5F);
        out.hitsEnemies = true;

        World.currentLevel.addActor(out);
    }

    public static void createTicTac(float dx, float dy, float x, float y) {
        //mint should have a speed of like 20
        float currSpeed = (float) Math.sqrt(dx * dx + dy * dy);
        float speedRatio = 10 / currSpeed;


        Projectile out = new Projectile("tictac.png");
        out.range = 3;
        out.dx = dx * speedRatio;
        out.dy = dy * speedRatio;
        out.setBounds(x, y, 0.3F, 0.3F);
        out.setOrigin(out.getWidth() / 2, out.getHeight() / 2);
        out.rotate((new Vector2(dx, dy)).angleDeg(new Vector2(1, 0)));
        out.hitsEnemies = true;

        World.currentLevel.addActor(out);
    }

    public static void createLollipop(float dx, float dy, float x, float y) {
        float currSpeed = (float) Math.sqrt((double) (dx * dx + dy * dy));
        float speedRatio = 10.0F / currSpeed;
        Projectile out = new Projectile("LolipopWHitBox.png");
        out.range = 10.0F;
        out.dx = dx * speedRatio;
        out.dy = dy * speedRatio;
        out.setBounds(x, y, 1.0F, 2.0F);
        out.setOrigin(out.getWidth() / 2.0F, out.getHeight() / 2.0F);
        out.rotate((new Vector2(dx, dy)).angleDeg(new Vector2(1.0F, 0.0F)));
        out.hitsEnemies = true;
        World.currentLevel.addActor(out);
    }

    public static void createCandyBlast(float dx, float dy, float x, float y) {
        float currSpeed = (float) Math.sqrt((double) (dx * dx + dy * dy));
        float speedRatio = 5.0F / currSpeed;

        for (int i = 0; i < 12; ++i) {
            Projectile out;
            if (Math.random() < 0.5) {
                out = new Projectile("GreenCandyWHitBox.png");
                out.range = 4.0F;
                out.dx = dx + (float) ((int) (Math.random() * 4.0 - 2.0)) * speedRatio;
                out.dy = dy + (float) ((int) (Math.random() * 4.0 - 2.0)) * speedRatio;
                out.setBounds(x, y, 1.0F, 0.7F);
                out.setOrigin(out.getWidth() / 2.0F, out.getHeight() / 2.0F);
                out.rotate((new Vector2(dx, dy)).angleDeg(new Vector2((float) ((int) (Math.random() * 10.0 - 5.0)), (float) ((int) (Math.random() * 10.0 - 5.0)))));
                out.hitsEnemies = true;
                World.currentLevel.addActor(out);
            } else {
                out = new Projectile("YellowCandyWHitBox.png");
                out.range = 4.0F;
                out.dx = dx + (float) ((int) (Math.random() * 4.0 - 2.0)) * speedRatio;
                out.dy = dy + (float) ((int) (Math.random() * 4.0 - 2.0)) * speedRatio;
                out.setBounds(x, y, 1.0F, 0.7F);
                out.setOrigin(out.getWidth() / 2.0F, out.getHeight() / 2.0F);
                out.rotate((new Vector2(dx, dy)).angleDeg(new Vector2((float) ((int) (Math.random() * 10.0 - 5.0)), (float) ((int) (Math.random() * 10.0 - 5.0)))));
                out.hitsEnemies = true;
                World.currentLevel.addActor(out);
            }
        }

    }
    public static void createScaredGhost(float x, float y){
        int dx = MathUtils.random(1,100)-50;
        int dy = MathUtils.random(1,100)-50;
        float currSpeed = (float) Math.sqrt((double) (dx * dx + dy * dy));
        float speedRatio = 10.0F / currSpeed;
        Projectile out = new Projectile("WhiteGhost.png"){
            @Override
            public void getHitbox(Rectangle outRect) {
                outRect.set(0,0,0,0);
            }
        };
        out.range = 10.0F;
        out.dx = dx * speedRatio;
        out.dy = dy * speedRatio;
        out.setBounds(x, y, 1.0F, 1.2F);
        World.currentLevel.addActor(out);
    }
}