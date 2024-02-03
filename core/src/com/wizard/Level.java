package com.wizard;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class Level extends Group {
    public Block[][] blockArray;

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


}


