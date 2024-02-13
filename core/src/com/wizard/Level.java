package com.wizard;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import java.util.ArrayList;

public class Level extends Group {
    public Block[][] blockArray;
    public  ArrayList<Enemy> collidableEnemies = new ArrayList<>();
    public  ArrayList<Loot> lootList = new ArrayList<>();
    public void initGroup(){
        this.setBounds(0,0,800,800);
        for (int x = 0; x < blockArray.length; x ++){
            for (int y = 0; y < blockArray[0].length; y++){
                this.addActor(blockArray[x][y]);
                blockArray[x][y].setPosition(x,y);
                blockArray[x][y].setWidth(1);
                blockArray[x][y].setHeight(1);
            }
        }
    }

    public boolean inBounds(int x, int y){
        return 0<=x && x<blockArray.length && 0<=y && y<blockArray[0].length;
    }


    public void addActor(Enemy e){
        collidableEnemies.add(e);
        this.addActor((Actor)e);
    }

    public void removeActor(Enemy e){
        collidableEnemies.remove(e);
        //10% chance to spawn chill pills after enemies die
        if (MathUtils.random(1,10) ==1){
            this.addActor(new ChillPill(e.getX(),e.getY()));
        }
        this.removeActor((Actor) e);

    }

    public void addActor(Loot e){
        lootList.add(e);
        this.addActor((Actor)e);
    }

    public void removeActor(Loot e){
        lootList.remove(e);
        this.removeActor((Actor) e);
    }



}


