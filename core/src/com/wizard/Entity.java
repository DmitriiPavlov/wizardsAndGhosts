package com.wizard;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Entity extends Group {
    public Image sprite;
    public Image redHP;
    public Image greenHP;
    public float SPEED;
    public float HP;
    public float maxHP;

    public boolean active = true;
    public Entity(float initHP,float speed, String spriteTextureName){
        HP = initHP;
        maxHP = initHP;
        SPEED = speed;
        sprite = new Image(TextureManager.get(spriteTextureName));
        redHP = new Image(TextureManager.get("REDHP.png"));
        greenHP = new Image(TextureManager.get("GREENHP.png"));

        this.addActor(sprite);
        if (maxHP > 0) {
            this.addActor(redHP);
            this.addActor(greenHP);
        }
    }
    public void updateHP(float newHP){
        if (newHP <0) newHP = 0;
        HP = newHP;
        redHP.setBounds(0, 0, this.getWidth(), 0.2F);
        greenHP.setBounds(0, 0, this.getWidth() * HP / maxHP, 0.2F);
    }
    public void setBounds(float x, float y, float h, float w){
        super.setBounds(x,y,h,w);
        if (maxHP>0) {
            updateHP(this.HP);
        }
        sprite.setBounds(0,0.2F,this.getWidth(),this.getHeight()-0.2F);
    }

    public void interpolateMotion(float dx, float dy) {
        int interpolationConstant = (int) SPEED;

        for (int i = 0; i < interpolationConstant; i++) {
            this.moveBy(dx / interpolationConstant, 0);
            if (CollisionManager.isHittingBlock(this)) {
                this.moveBy(-dx / interpolationConstant, 0);
            }

            this.moveBy(0, dy / interpolationConstant);
            if (CollisionManager.isHittingBlock(this)) {
                this.moveBy(0, -dy / interpolationConstant);
            }

        }
    }
    public void getHitbox(Rectangle outRect){
        if (active){
        outRect.set(this.getX() + this.sprite.getX(),this.getY() + this.sprite.getY(), this.sprite.getHeight(),this.sprite.getWidth());
        }
        else{
            outRect.set(0,0,0,0);
        }
    }

    public void rotate(float degrees){
        this.sprite.rotateBy(degrees);
    }

    public float centerX(){
        return this.getX() + this.sprite.getX() + this.sprite.getWidth()/2;
    }
    public float centerY(){
        return this.getY() + this.sprite.getY() + this.sprite.getHeight()/2;
    }

}
