package com.wizard;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.HashMap;

public class LevelManager {
    public static Level load(String name){
        FileHandle file = Gdx.files.internal("levels" +"/"+ name);

        String levelString = file.readString();

        String[] lines = levelString.split("\n");

        int width = 0;
        int height = 0;
        int blockAmount = 0;
        HashMap<Integer,String> decoderMap = new HashMap<>();

        Level outLevel = new Level();
        for (int i = 0; i < lines.length; i++){
            String line = lines[i];
            if (i == 0){
                width = Integer.parseInt(line.split("x")[0]);
                height = Integer.parseInt(line.split("x")[1]);

                outLevel.blockArray = new Block[width][height];
            }
            else if (i == 1){
                blockAmount = Integer.parseInt(line);
            }

            else if ((i > 1) && (i < 2+blockAmount)){
                int key = Integer.parseInt(line.split(" ")[1]);
                String decoderName = line.split(" ")[0];
                decoderMap.put(key,decoderName);
            }

            else {
                String[] blocks = line.split(" ");
                int x = i - (2+blockAmount);


                for (int y = 0; y < height; y++){
                    Block newBlock = new Block(decoderMap.get(Integer.parseInt(blocks[y])));
                    outLevel.blockArray[x][y] = newBlock;
                }
            }
        }
        //this is so all the blocks become sub actors of the group, and are allowed their own coordinate system
        outLevel.initGroup();
        return outLevel;
    }
}
