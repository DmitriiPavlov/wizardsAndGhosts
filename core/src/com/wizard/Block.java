package com.wizard;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Block extends Image {
    public String name;
    public Block(String name){
        super(BlockManager.nameToTextures.get(name));
        this.name = name;
    }
    public Texture getTexture(){
        return BlockManager.nameToTextures.get(this.name);
    }

    public boolean collidable(){
        return BlockManager.isCollidable(this.name);
    }

}
