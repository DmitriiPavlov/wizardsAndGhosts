package com.wizard;

import com.badlogic.gdx.math.Vector2;

public class Enemy extends Entity{
    public Enemy(float x, float y, float width, float height){
        //setting default parameters
        super(10,8,"GhostRedesign.png");
        this.setBounds(x,y,width,height);
    }
    public Enemy(float initHP, float speed, String spriteTextureName) {
        super(initHP, speed, spriteTextureName);
    }

    public void act(float deltaTime){
        Vector2 directionVector = new Vector2(CollisionManager.character.getX() - this.getX(), CollisionManager.character.getY() - this.getY());
        directionVector.nor();
        interpolateMotion(directionVector.x * deltaTime,directionVector.y * deltaTime);
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
}
