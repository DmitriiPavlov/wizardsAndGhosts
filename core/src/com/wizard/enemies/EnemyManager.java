package com.wizard.enemies;

import com.badlogic.gdx.math.MathUtils;
import com.wizard.CollisionManager;
import com.wizard.Enemy;
import com.wizard.Level;
import com.wizard.staffs.GhostStaff;
import com.wizard.staffs.Staff;

public class EnemyManager {

    public static Enemy defaultEnemy(float x, float y){
        Enemy out =  new Enemy(x,y,1.0f,1.0f);
        return out;
    }

    public static Enemy defaultEnemy(){
        return defaultEnemy(0,0);
    }

    public static EnemyShooter defaultShooter(){
        return new EnemyShooter(0,0,1,1.2f,new GhostStaff());
    }

    public static EnemyShooter bigBoy(){
        EnemyShooter out= defaultShooter();
        out.weapon = new GhostStaff();
        out.maxHP = 50;
        out.HP = 50;
        out.setBounds(0,0,2f,2.4f);
        out.SPEED *= 0.6f;
        return out;
    }
    public static Enemy fastEnemy(float x, float y){
        Enemy out =  new Enemy(x,y,1.0f,1.2f);
        out.SPEED *= 1.5;
        return out;
    }

    public static Enemy largeEnemy(float x, float y){
        Enemy out =  new Enemy(x,y,2.0f,2.0f);
        out.maxHP *= 5; out.HP *=5;
        return out;
    }

    public static void addEnemyRandomly(int quantity, Enemy e, Level l){
        for (int i = 0; i< quantity; i++) {
            Enemy out = new Enemy(1, 1, 1, 1);
            out.setBounds(e.getX(), e.getY(), e.getWidth(), e.getHeight());
            out.maxHP = e.maxHP;
            out.HP = e.HP;
            out.SPEED = e.SPEED;
            float rx; float ry;
            while(true){
                rx = MathUtils.random() * (l.blockArray.length - 1);
                ry = MathUtils.random() * (l.blockArray[0].length - 1);
                out.setBounds(rx,ry,out.getWidth(),out.getHeight());
                if (CollisionManager.isCollidingEnemy(out)==null && !CollisionManager.isHittingBlock(out)){
                    break;
                }
            }
            l.addActor(out);
        }
    }
    public static void addEnemyRandomly(int quantity, EnemyShooter e, Level l){
        for (int i = 0; i< quantity; i++) {
            EnemyShooter out = new EnemyShooter(1, 1, 1, 1,null);
            out.setBounds(e.getX(), e.getY(), e.getWidth(), e.getHeight());
            out.maxHP = e.maxHP;
            out.HP = e.HP;
            out.SPEED = e.SPEED;
            out.weapon = Staff.getStaffType(e.weapon);
            float rx; float ry;
            while(true){
                rx = MathUtils.random() * (l.blockArray.length - 1);
                ry = MathUtils.random() * (l.blockArray[0].length - 1);
                out.setBounds(rx,ry,out.getWidth(),out.getHeight());
                if (CollisionManager.isCollidingEnemy(out)==null && !CollisionManager.isHittingBlock(out)){
                    break;
                }
            }
            l.addActor(out);
        }
    }

}
