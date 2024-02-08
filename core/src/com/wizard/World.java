package com.wizard;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.*;

import java.util.ArrayList;

public class World extends Stage {
    public static Level currentLevel;

    public static ArrayList<Level> levelList = new ArrayList<>();
    public static int indexLevel = -1;
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

        player = new Character(1,1,Constant.CHARACTER_WIDTH,Constant.CHARACTER_HEIGHT);
        CollisionManager.character = player;
        this.addActor(player);

        loadNextLevel();



        //event listener for a click (projectile fired)
        this.addListener(new InputListener(){
            long lastTime = 0;


            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (button == Input.Buttons.LEFT && TimeUtils.timeSinceMillis(lastTime) > Candy.getCandyCooldown()){
                    //we want to make a projectile that fires this way
                    Candy.useCandy(x - player.centerX(), y - player.centerY(), player.centerX(), player.centerY());
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
        this.player.setZIndex(100);
        this.getViewport().apply();
        this.getCamera().position.set(player.getX(),player.getY(),0);
        super.draw();
    }

    @Override
    public void act() {
        super.act();
        //this line is to see if the player goes out of bounds above
        if (player.getY() > currentLevel.blockArray.length){
            loadNextLevel();
        }

        if (player.getY() < 0){
            loadPreviousLevel();
        }

    }

    public void loadNextLevel(){
        indexLevel+=1;
        //this sees if we're creating the level for the first time
        Level newLevel = null;
        if (levelList.size() == indexLevel) {
            newLevel = LevelManager.load(String.valueOf(indexLevel));
            if (newLevel == null){
                indexLevel -= 1;
                return;
            };
            levelList.add(newLevel);
            //spawns NPCS
            populateLevel(newLevel);
        }
        else{
            newLevel = levelList.get(indexLevel);
        }
        if (currentLevel != null) currentLevel.remove();
        currentLevel = newLevel;

        currentLevel.setPosition(0,0);
        CollisionManager.currentLevel = currentLevel;
        this.addActor(currentLevel);
        currentLevel.setZIndex(1);
        player.setPosition(this.currentLevel.blockArray[0].length/2, 1);

    }

    public void loadPreviousLevel(){
        if (indexLevel == 0) return;
        indexLevel-=1;
        Level newLevel = levelList.get(indexLevel);
        if (newLevel == null) return;

        if (currentLevel != null) currentLevel.remove();
        currentLevel = newLevel;
        currentLevel.setPosition(0,0);
        CollisionManager.currentLevel = currentLevel;
        this.addActor(currentLevel);
        currentLevel.setZIndex(1);
        player.setPosition(this.currentLevel.blockArray[0].length/2, this.currentLevel.blockArray.length-1);
    }

    //this goes through and adds all the necessary enemies to a level
    public void populateLevel(Level toPopulate){
        switch (indexLevel){
            case 0:
                toPopulate.addActor(new Enemy(3,3,1,1.2F));
                toPopulate.addActor(new Enemy(4,5,1,1.2F));
                toPopulate.addActor(new Enemy(2,3,1,1.2F));
                toPopulate.addActor(new Enemy(6,3,1,1.2F));
        }
    }
}
