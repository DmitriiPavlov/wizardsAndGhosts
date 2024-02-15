package com.wizard;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

public class BlockManager {
    public static HashMap<String, Texture> nameToTextures = new HashMap<>();

    public static HashMap<String, Boolean> nameToCollidable = new HashMap<>();

    public static void loadAll(){
        nameToTextures = TextureManager.loadFrom("blockTextures");
        for (String name : nameToTextures.keySet()){
            setNameToNotCollidable(name);
        }
        HashMap<String, Texture> nameToTextures2 = TextureManager.loadFrom("GraveYardBiome");

        HashMap<String, Texture> nameToTextures3 = TextureManager.loadFrom("GraveYardBiomeCollidable");
        for (String name : nameToTextures2.keySet()){
            setNameToNotCollidable(name);
        }

        for (String name : nameToTextures3.keySet()){
            setNameToCollidable(name);
        }

        nameToTextures.putAll(nameToTextures2);
        nameToTextures.putAll(nameToTextures3);
        //we make all blocks passable through
        setNameToCollidable("wall.png");
        setNameToCollidable("forestRoof.png");
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
