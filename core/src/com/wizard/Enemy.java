package com.wizard;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

public class Enemy extends Entity{
    public long lastAttack;
    public float lockOnRange = 7.5f;
    public Enemy(float x, float y, float width, float height){
        //setting default parameters
        super(10,4,"GhostRedesign.png");
        this.setBounds(x,y,width,height);
    }
    public Enemy(float initHP, float speed, String spriteTextureName) {
        super(initHP, speed, spriteTextureName);
    }

    public void act(float deltaTime){
        Vector2 directionVector = new Vector2(CollisionManager.character.getX() - this.getX(), CollisionManager.character.getY() - this.getY());
        directionVector.nor();
        float xDist = this.getX() - Wizard.w.player.getX();
        float yDist = this.getY() - Wizard.w.player.getY();
        if (lockOnRange*lockOnRange > xDist*xDist + yDist*yDist) {
            lockOnRange *= 1.5f;
            interpolateMotion(this.SPEED * directionVector.x * deltaTime, this.SPEED * directionVector.y * deltaTime);
        }
        //code that checks if the ghost should hit the player
        if (CollisionManager.isCollidingPlayer(this) && canAttack()){
            Wizard.w.player.updateHP(Wizard.w.player.HP-1);
            lastAttack = TimeUtils.millis();
        }
    }

    //SO ENEMIES CAN COLLIDE WITH EACH OTHER AND DONT BUNCH UP
    public void interpolateMotion(float dx, float dy) {
        int interpolationConstant = (int) SPEED;

        for (int i = 0; i < interpolationConstant; i++) {
            this.moveBy(dx / interpolationConstant, 0);

            if (CollisionManager.isHittingBlock(this) || null != CollisionManager.isCollidingEnemy(this)) {
                this.moveBy(-dx / interpolationConstant, 0);
            }

            this.moveBy(0, dy / interpolationConstant);
            if (CollisionManager.isHittingBlock(this) || null != CollisionManager.isCollidingEnemy(this)) {
                this.moveBy(0, -dy / interpolationConstant);
            }

        }
    }

    public void updateHP(float newHP){
        super.updateHP(newHP);
        if (this.HP == 0){
            ProjectileManager.createScaredGhost(this.getX(), this.getY());
            Wizard.w.currentLevel.removeActor(this);
        }
    }

    public boolean canAttack(){
        return TimeUtils.timeSinceMillis(lastAttack) > 100;
    }

}
