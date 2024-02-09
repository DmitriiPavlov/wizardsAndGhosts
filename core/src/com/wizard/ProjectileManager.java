package com.wizard;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.wizard.staffs.Staff;

public class ProjectileManager {
    //manages projectiles and their trajectories
    public static World stage;
    public static long projectileImmunityWindow = 100;

    public static class Projectile extends Entity {
        public float dx, dy, height, width;
        public float range;
        public float distanceTraveled = 0;
        public boolean hitsPlayer = false;
        boolean hitsEnemies;
        public long timeCreated;
        public long lifeTime;

        public int damage; // this is to store a dammage value in projectiles

        public String proName;// this will be used to call back to the staff class it originated from

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
                    collided.updateHP(collided.HP - damage);
                    Staff.ability(proName, dx, dy, this.getX(), this.getY());
                    World.currentLevel.removeActor(this);
                    return;
                }
            }

            if (hitsPlayer) {
                boolean hit = CollisionManager.isCollidingPlayer(this);
                if (hit) {
                    //here I would subtract the hp
                    Wizard.w.player.updateHP(Wizard.w.player.HP - 1);
                    System.out.println("here");
                    World.currentLevel.removeActor(this);
                    return;
                }
            }


            if (TimeUtils.timeSinceMillis(timeCreated)>= lifeTime){
                World.currentLevel.removeActor(this);
                return;
            }

            this.interpolateMotion(deltaTime);
        }

        public void interpolateMotion(float deltaTime) {
            int interpolationConstant = (int) (Math.sqrt(dx * dx + dy * dy));
            for (int i = 0; i < interpolationConstant; i++) {
                this.moveBy(deltaTime * dx / interpolationConstant, deltaTime * dy / interpolationConstant);
                if (CollisionManager.isHittingBlock(this) && TimeUtils.timeSinceMillis(timeCreated) > projectileImmunityWindow ) {
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
        out.setBounds(x, y, 0.8F, 0.8F);
        out.hitsEnemies = true;

        out.damage = 2;
        out.proName = "Mint";

        World.currentLevel.addActor(out);
    }

    public static void createTicTac(float dx, float dy, float x, float y) {
        //mint should have a speed of like 20
        float currSpeed = (float) Math.sqrt(dx * dx + dy * dy);
        float speedRatio = 15 / currSpeed;


        Projectile out = new Projectile("tictac.png");
        out.range = 5;
        out.dx = dx * speedRatio;
        out.dy = dy * speedRatio;
        out.setBounds(x, y, 0.3F, 0.3F);
        out.setOrigin(out.getWidth() / 2, out.getHeight() / 2);
        out.rotate((new Vector2(dx, dy)).angleDeg(new Vector2(1, 0)));
        out.hitsEnemies = false;
        out.hitsPlayer = true;
        out.damage = 3;
        out.proName = "TicTac";
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
        out.rotate((new Vector2(dx, dy)).angleDeg(new Vector2(0.0F, 1.0F)));
        out.hitsEnemies = true;

        out.damage = 8;
        out.proName = "Lollipop";

        World.currentLevel.addActor(out);
    }

    public static void createCandyBlast(float dx, float dy, float x, float y) {
        float currSpeed = (float) Math.sqrt((double) (dx * dx + dy * dy));
        float speedRatio = 5.0F / currSpeed;

        for (int i = -45; i <= 45; i += 10) {
            Projectile out;
            if (Math.random() < 0.5) {
                out = new Projectile("GreenCandyWHitBox.png");}
            else {
                out = new Projectile("YellowCandyWHitBox.png");}
            out.range = 4.0F;
            float multi = (float) ((int) (Math.random() * 4.0 - 2));

            double dydx = dy / dx;
            /*
            float dyd = 1;
            float dxd = 1;
            if (dy < 0){ dyd = -1;};
            if (dx < 0){ dxd = -1;};
            */
            float dxd = 1;
            if (dx < 0){ dxd = -1;};

            out.dx = (float)(Math.cos(Math.atan(dydx) + Math.toRadians(i)) * dxd) * 6;
            out.dy = (float)(Math.sin(Math.atan(dydx) + Math.toRadians(i)) * dxd) * 6;
            out.setBounds(x, y, 1.0F, 0.7F);
            out.setOrigin(out.getWidth() / 2.0F, out.getHeight() / 2.0F);
            out.rotate((new Vector2(dx, dy)).angleDeg(new Vector2((float) ((int) (Math.random() * 10.0 - 5.0)), (float) ((int) (Math.random() * 10.0 - 5.0)))));
            out.hitsEnemies = true;
            System.out.println("dy = " + dy + " dx = " + dx);
            System.out.println(Math.atan(dydx));
            System.out.println(Math.cos(Math.atan(dydx)));

            out.damage = 1;
            out.proName = "Candy";

            World.currentLevel.addActor(out);

        }
    }

    public static void createChocBar(float dx, float dy, float x, float y) {
        float currSpeed = (float) Math.sqrt((double) (dx * dx + dy * dy));
        float speedRatio = 2.0F / currSpeed;
        Projectile out = new Projectile("ChocBarWHitBox.png");
        out.range = 10.0F;
        out.dx = dx * speedRatio;
        out.dy = dy * speedRatio;
        out.setBounds(x, y, 1.3F/1.2f, 1.7F/1.2f);
        out.setOrigin(out.getWidth() / 2.0F, out.getHeight() / 2.0F);
        out.rotate((new Vector2(dx, dy)).angleDeg(new Vector2(0.0F, 1.0F)));
        out.hitsEnemies = true;

        out.damage = 5;
        out.proName = "ChocBar";

        World.currentLevel.addActor(out);


    }

    public static void createChocChunk(float dx, float dy, float x, float y) {
        float currSpeed = (float) Math.sqrt((double) (dx * dx + dy * dy));
        float speedRatio = 2.0F / currSpeed;

        for (double i = 0; i <= 6.283185307; i = i + 0.5235987756) {
            Projectile out = new Projectile("ChocChunkWHitBox.png");
            out.range = 6.0F;
            out.dx = (float)(Math.cos(i)) * speedRatio;//out.dx = (float)(Math.cos(i)) * speedRatio;
            out.dy = (float)(Math.sin(i)) * speedRatio;

            out.setBounds(x, y, 0.5F, 0.7F);
            out.setOrigin(out.getWidth() / 2.0F, out.getHeight() / 2.0F);
            out.rotate((new Vector2(dx, dy)).angleDeg(new Vector2(0.0F, 1.0F)));
            out.hitsEnemies = true;

            out.damage = 2;
            out.proName = "ChocChunk";

            World.currentLevel.addActor(out);
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

        out.damage = 1;
        out.proName = "ScaredGhost";

        World.currentLevel.addActor(out);
    }
}