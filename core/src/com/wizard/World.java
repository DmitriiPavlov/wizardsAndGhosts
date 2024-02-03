package com.wizard;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.*;

public class World extends Stage {
    public static Level currentLevel;
    public static Character player;
    public World(){
        //this is where everything gets initiated, like texture manager and projectile manager
        super(new FitViewport(28,20));


        Gdx.input.setInputProcessor(this);
        BlockManager.loadAll();
        TextureManager.loadAll();
        ProjectileManager.stage = this;

        Image backGround = new Image(TextureManager.get("empty.png"));
        backGround.setBounds(-10000,-10000,50000,50000);
        this.addActor(backGround);


        currentLevel = LevelManager.load("default");
        currentLevel.setPosition(0,0);

        CollisionManager.currentLevel = currentLevel;
        player = new Character(1,1,Constant.CHARACTER_WIDTH,Constant.CHARACTER_HEIGHT);
        CollisionManager.character = player;
        currentLevel.addActor(player);
        this.addActor(currentLevel);
        this.addActor(new Enemy(3,3,1,1.2F));


        //event listener for a click (projectile fired)
        this.addListener(new InputListener(){
            long lastTime = 0;
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (button == Input.Buttons.LEFT && TimeUtils.timeSinceMillis(lastTime) > 100){
                    //we want to make a projectile that fires this way
                    ProjectileManager.createMint(x - player.getX(), y -player.getY(), player.getX(),player.getY());
                    lastTime = TimeUtils.millis();
                    return true;
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });

    }
    public void resize(int width, int height){
        this.getViewport().update(width, height,true);
        this.getCamera().position.set(player.getX(),player.getY(),0);
        this.getCamera().update();
    }

    public void draw(){
        this.getViewport().apply();
        this.getCamera().position.set(player.getX(),player.getY(),0);
        super.draw();
    }

}
