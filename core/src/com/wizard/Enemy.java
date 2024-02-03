package com.wizard;

import com.badlogic.gdx.math.Vector2;

public class Enemy extends Entity{
    public Enemy(float x, float y, float width, float height){
        //setting default parameters
        super(10,8,"GhostRedesign.png");
        CollisionManager.collidableEnemies.add(this);
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
}
