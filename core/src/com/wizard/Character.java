package com.wizard;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

public class Character extends Entity{
    private Drawable leftWizard;
    private Drawable rightWizard;
    private Drawable downWizard;

    public Character(float x, float y, float width, float height) {
        super(20, 10, "WizardFrontImproved.png");
        this.setBounds(x,y,width,height);

        leftWizard = new SpriteDrawable(new Sprite(TextureManager.get("WizardLeftFacing.png")));
        downWizard = new SpriteDrawable(new Sprite(TextureManager.get("WizardFrontImproved.png")));
        rightWizard = new SpriteDrawable(new Sprite(TextureManager.get("WizardRightFacing.png")));
    }

    @Override
    public void getHitbox(Rectangle out){
        super.getHitbox(out);
        out.setSize(Constant.CHARACTER_HITBOX_WIDTH, Constant.CHARACTER_HITBOX_HEIGHT);
    }

    public void act(float deltaTime){
        super.act(deltaTime);
        int dx = 0;
        int dy = 0;
        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            dy += 1;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            dx += 1;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            dy -= 1;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            dx -= 1;
        }

        //need this so the wizard actually changes sprite from frame to frame
        setSprite(dx,dy);

        float useSpeed = Constant.CHARACTER_SPEED;
        if (dx != 0 && dy != 0){
            //this is to keep diagonal movement the same speed
            useSpeed /= (float) Math.sqrt(2.0F);
        }

        interpolateMotion(dx*useSpeed*deltaTime, dy*useSpeed*deltaTime);
    }
    public void setSprite(float dx, float dy){
        if (dx == 0){
            this.sprite.setDrawable(downWizard);
        }

        if (dx < 0){
            this.sprite.setDrawable(leftWizard);
        }

        if (dx > 0){
            this.sprite.setDrawable(rightWizard);
        }
    }
}
