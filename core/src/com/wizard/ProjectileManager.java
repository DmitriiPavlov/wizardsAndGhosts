package com.wizard;

import com.badlogic.gdx.math.Vector2;

public class ProjectileManager {
    //manages projectiles and their trajectories
    public static World stage;

    public static class Projectile extends Entity{
        public float dx,dy,height, width;
        public float range;
        public float distanceTraveled = 0;
        boolean hitsEnemies;
        public Projectile(String textureName){
            super(0,0,textureName);
        }

        public void act(float deltaTime){
            if (hitsEnemies) {
                Enemy collided = CollisionManager.isCollidingEnemy(this);

                if (collided != null){
                    //here I would subtract the hp
                    collided.updateHP(collided.HP - 1);
                    this.getParent().removeActor(this);
                    return;
                }
            }

            this.interpolateMotion(deltaTime);
        }

        public void interpolateMotion(float deltaTime){
            int interpolationConstant = (int)(Math.sqrt(dx*dx + dy*dy));
            for (int i = 0; i < interpolationConstant; i++){
                this.moveBy(deltaTime * dx/interpolationConstant,deltaTime * dy/interpolationConstant);
                if (CollisionManager.isHittingBlock(this)){
                    this.getParent().removeActor(this);
                    return;
                }

            }
            distanceTraveled += Math.sqrt(dx*dx + dy*dy) * deltaTime;
            if (distanceTraveled > range){
                this.getParent().removeActor(this);
                return;
            }
        }

    }

    public static void createMint(float dx, float dy, float x, float y){
        //mint should have a speed of like 20
        float currSpeed = (float) Math.sqrt(dx*dx + dy*dy);
        float speedRatio = 20/currSpeed;


        Projectile out = new Projectile("Projectile.png");
        out.range = 5;
        out.dx = dx * speedRatio; out.dy = dy * speedRatio;
        out.setBounds(x,y,0.5F,0.5F);
        out.hitsEnemies = true;

        stage.addActor(out);
    }

    public static void createTicTac(float dx, float dy, float x, float y){
        //mint should have a speed of like 20
        float currSpeed = (float) Math.sqrt(dx*dx + dy*dy);
        float speedRatio = 10/currSpeed;


        Projectile out = new Projectile("tictac.png");
        out.range = 3;
        out.dx = dx * speedRatio; out.dy = dy * speedRatio;
        out.setBounds(x,y,3.0F,3.0F);
        out.setOrigin(out.getWidth()/2, out.getHeight()/2);
        out.rotate((new Vector2(dx,dy)).angleDeg(new Vector2(1,0)));
        out.hitsEnemies = true;
        stage.addActor(out);


    }
}
