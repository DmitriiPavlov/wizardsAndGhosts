package com.wizard;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

public class BlockManager {
    public static HashMap<String, Texture> nameToTextures = new HashMap<>();

    public static HashMap<String, Boolean> nameToCollidable = new HashMap<>();

    public static void loadAll(){
        nameToTextures = TextureManager.loadFrom("blockTextures");

        //we make all blocks passable through
        for (String name : nameToTextures.keySet()){
            setNameToNotCollidable(name);
        }
        setNameToCollidable("wall.png");
    }

    public static void setNameToCollidable(String name){
        nameToCollidable.put(name,true);
    }
    public static void setNameToNotCollidable(String name){
        nameToCollidable.put(name,false);
    }

    public static boolean isCollidable(String name){
        return nameToCollidable.get(name);
    }
}
