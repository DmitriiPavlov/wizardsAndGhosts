package com.wizard;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.*;
import com.wizard.enemies.EnemyManager;
import com.wizard.enemies.EnemyShooter;
import com.wizard.staffs.*;

import java.util.ArrayList;
import com.badlogic.gdx.audio.Sound;

public class World extends Stage {
    public static Level currentLevel;

    public static ArrayList<Level> levelList = new ArrayList<>();
    public static int indexLevel = -1;
    public static Character player;
    public static Staff currentStaff = null;

    public static Staff PrimaryStaff = null;

    public static Staff SecondaryStaff = null;

    public Sound Woosh;
    public static ArrayList<Loot> lootList = new ArrayList<>();
    public World(){
        //this is where everything gets initiated, like texture manager and projectile manager
        super(new FitViewport(28,20));

        Woosh = Gdx.audio.newSound(Gdx.files.internal("Whoosh Sounds Effects HD (No Copyright).mp3"));

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
                if (button == Input.Buttons.LEFT && currentStaff != null && currentStaff.canFire()){
                    //we want to make a projectile that fires this way
                    currentStaff.fire(player.getX(),player.getY(),x, y);
                    Woosh.play();
                    return true;
                }
                return super.touchDown(event, x, y, pointer, button);
            }
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (keycode == Input.Keys.E){
                    Loot pickUp = CollisionManager.isCollidingLoot(player);
                    if (pickUp != null && pickUp.matchingStaff != null){
                        if (PrimaryStaff == null) { // checking how to manipulate inventory depending on what number of items have already been picked up
                            currentStaff = pickUp.matchingStaff;
                            PrimaryStaff = pickUp.matchingStaff;
                            Wizard.o.inventory.setWeaponOne(currentStaff.itemTexture);
                            Wizard.w.currentLevel.removeActor(pickUp);
                        }
                        else if (SecondaryStaff == null){
                            SecondaryStaff = PrimaryStaff;
                            currentStaff = pickUp.matchingStaff;
                            PrimaryStaff = pickUp.matchingStaff;
                            Wizard.o.inventory.setWeaponOne(PrimaryStaff.itemTexture);
                            Wizard.o.inventory.setWeaponTwo(SecondaryStaff.itemTexture);
                            Wizard.w.currentLevel.removeActor(pickUp);
                        }
                        else{
                            Staff temp = Staff.getStaffType(PrimaryStaff);
                            World.currentLevel.addActor(new Loot(player.getX(),player.getY(),temp.getItemTexture(),temp));
                            currentStaff = pickUp.matchingStaff;
                            PrimaryStaff = pickUp.matchingStaff;
                            Wizard.o.inventory.setWeaponOne(currentStaff.itemTexture);
                            Wizard.w.currentLevel.removeActor(pickUp);
                        }

                    }
                }
                else if(keycode == Input.Keys.Q){
                    if (PrimaryStaff != null && SecondaryStaff != null){
                        Staff temp = PrimaryStaff;
                        PrimaryStaff = SecondaryStaff;
                        SecondaryStaff = temp;
                        currentStaff = PrimaryStaff;
                        Wizard.o.inventory.setWeaponOne(PrimaryStaff.itemTexture);
                        Wizard.o.inventory.setWeaponTwo(SecondaryStaff.itemTexture);
                        return true;
                    }

                }

                return super.keyDown(event, keycode);
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
        if (currentStaff == null){
            Wizard.o.bottomText.setText("Use [WASD] to move.");
        }
        else {
            Wizard.o.bottomText.setText("Use [Q] to swap spells,\n and click to cast.");
        }
        super.act();
        //this line is to see if the player goes out of bounds above
        if (player.getY() > currentLevel.blockArray[0].length){
            loadNextLevel();
        }

        if (player.getY() < 0){
            loadPreviousLevel();
        }


        if (player.HP == 0){
            loadPreviousLevel(0);
            player.updateHP(20);
            player.setY(3);
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
            CollisionManager.currentLevel = newLevel;
            //spawns NPCS
            populateLevel(newLevel);
        }
        else{
            newLevel = levelList.get(indexLevel);
        }
        if (currentLevel != null) currentLevel.remove();
        currentLevel = newLevel;
        CollisionManager.currentLevel = newLevel;
        currentLevel.setPosition(0,0);
        this.addActor(currentLevel);
        currentLevel.setZIndex(1);
        player.setPosition(this.currentLevel.blockArray.length/2, 1);

    }

    public void loadPreviousLevel(int levelIndex){
        indexLevel = levelIndex;
        Level newLevel = levelList.get(levelIndex);
        if (newLevel == null) return;

        if (currentLevel != null) currentLevel.remove();
        currentLevel = newLevel;
        currentLevel.setPosition(0,0);
        CollisionManager.currentLevel = currentLevel;
        this.addActor(currentLevel);
        currentLevel.setZIndex(1);
        player.setPosition(this.currentLevel.blockArray.length/2, this.currentLevel.blockArray[0].length-2);
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
        player.setPosition(this.currentLevel.blockArray.length/2, this.currentLevel.blockArray[0].length-2);
    }

    //this goes through and adds all the necessary enemies to a level
    public void populateLevel(Level toPopulate){
        switch (indexLevel){
            case 0:
                toPopulate.addActor(new Loot(10,7,"LolipopWHitBox.png",new LollipopStaff()));
                break;
            case 1:
                Wizard.o.displayText("Magic Candy:\nYou Must Feed The ghosts CANDY!\n Find different candy spells on the ground");
                toPopulate.addActor(new Enemy(3,3,1,1.2F));
                toPopulate.addActor(new Enemy(4,5,1,1.2F));
                toPopulate.addActor(new Enemy(2,3,1,1.2F));
                toPopulate.addActor(new Enemy(6,3,1,1.2F));
                toPopulate.addActor(new Loot(2,4,"MintScroll.png",new MintStaff()));
                toPopulate.addActor(new Loot(8,6,"CandyBlastScroll.png",new CandyStaff()));
                break;
            case 2:
                toPopulate.addActor(new EnemyShooter(1,1,1,1.2f,new GhostStaff()));
                toPopulate.addActor(new EnemyShooter(1,3,1,1.2f,new GhostStaff()));
                toPopulate.addActor(new EnemyShooter(1,7,1,1.2f,new GhostStaff()));
                toPopulate.addActor(new EnemyShooter(1,9,1,1.2f,new GhostStaff()));
                toPopulate.addActor(new EnemyShooter(1,11,1,1.2f,new GhostStaff()));
                toPopulate.addActor(new EnemyShooter(1,13,1,1.2f,new GhostStaff()));
                toPopulate.addActor(new EnemyShooter(1,15,2,2.4f,new GhostStaff()));
                toPopulate.addActor(new Loot(6,3,"ChocBarWHitBox.png",new ChocBarStaff()));
                break;
            case 3:
                EnemyManager.addEnemyRandomly(5,EnemyManager.defaultEnemy(),toPopulate);
                break;
            case 4:
                toPopulate.addActor(new EnemyShooter(1,1,1,1.2f,new GhostStaff()));
                toPopulate.addActor(new EnemyShooter(1,3,1,1.2f,new GhostStaff()));
                toPopulate.addActor(new EnemyShooter(1,7,1,1.2f,new GhostStaff()));
                toPopulate.addActor(new EnemyShooter(1,9,1,1.2f,new GhostStaff()));
                toPopulate.addActor(new EnemyShooter(2,1,1,1.2f,new GhostStaff()));
                toPopulate.addActor(new EnemyShooter(2,3,1,1.2f,new GhostStaff()));
                toPopulate.addActor(new EnemyShooter(2,7,1,1.2f,new GhostStaff()));
                toPopulate.addActor(new EnemyShooter(2,9,1,1.2f,new GhostStaff()));
                toPopulate.addActor(new EnemyShooter(1,11,1,1.2f,new GhostStaff()));
                toPopulate.addActor(new EnemyShooter(1,13,1,1.2f,new GhostStaff()));
                toPopulate.addActor(new EnemyShooter(1,15,2,2.4f,new GhostStaff()));
                break;
        }
    }
}
