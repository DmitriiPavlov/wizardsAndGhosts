package com.wizard;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.HashMap;

public class LevelManager {
    public static Level load(String name){
        FileHandle file = Gdx.files.internal("levels" +"/"+ name);

        String levelString = file.readString();
//
        String[] lines = levelString.split("\n");

        int width = 0;
        int height = 0;
        int blockAmount = 0;
        HashMap<Integer,String> decoderMap = new HashMap<>();
        Level outLevel = new Level();
        for (int i = 0; i < lines.length; i++){
            String line = lines[i];

            if (i == 0){
                width = bullCrapInt(line.split("x")[0].substring(0,2));
                height = bullCrapInt(line.split("x")[1].substring(0,2));

                outLevel.blockArray = new Block[width][height];
            }
            else if (i == 1){
                blockAmount = bullCrapInt(line);
            }

            else if ((i > 1) && (i < 2+blockAmount)){
                int key = bullCrapInt(line.split(" ")[1]);
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
        outLevel.initGroup();
        return outLevel;
    }

    public static int bullCrapInt(String name){
        char[] lineArray = name.toCharArray();
        String out = "";
        for (char c : lineArray){
            try{
                out += Integer.parseInt(String.valueOf(c));
            }
            catch (Exception e){
                continue;
            }
        }
        return Integer.parseInt(out);
    }
}
