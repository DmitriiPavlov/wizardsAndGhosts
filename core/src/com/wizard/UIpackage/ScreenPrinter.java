package com.wizard.UIpackage;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.TimeUtils;
import com.wizard.TextureManager;

import java.util.ArrayList;
import java.util.LinkedList;

public class ScreenPrinter extends Label {
    long printJobStart;
    long printJobEnd;
    float height;

    private ArrayList<Long> pauseQueue = new ArrayList<>();
    private ArrayList<Long> speedQueue = new ArrayList<>();
    private ArrayList<String> stringQueue = new ArrayList<>();
    int queueIndex = 0;
    int stringIndex = 0;
    long lastAction;
    boolean printing = false;


    public ScreenPrinter(Stage parent){
        super("", TextureManager.mSkin);
        this.setWrap(true);
        height = 80;
        this.setFontScale(0.8F);
        this.setBounds(0,parent.getHeight()-height, parent.getWidth(), height);
    }

    public void printText(String text,long timePerCharacterMillis){
        queueText(text, timePerCharacterMillis, 0);
    }

    public void queueText(String text, long timePerCharacterMillis, long timeOnScreen){
        stringQueue.add(text);
        pauseQueue.add(timeOnScreen);
        speedQueue.add(timePerCharacterMillis);
    }


    public void resize(int Pwidth, int Pheight){
        this.setBounds(0,Pheight-height, Pwidth, height);
    }
    public void act(float deltaTime){
        //determines if we even have to do anything
        if (queueIndex < stringQueue.size()){
            //we are checking if we are the end of a string - if we are, we must wait the pause time
            if (stringIndex == stringQueue.get(queueIndex).length()){
                if (TimeUtils.timeSinceMillis(lastAction) >= pauseQueue.get(queueIndex)){
                    queueIndex += 1;
                    stringIndex = 0;
                    lastAction = TimeUtils.millis();
                }
            }

            //if we are not at the end of the string, we must wait the string's character speed time
            else {
                if (TimeUtils.timeSinceMillis(lastAction) >= speedQueue.get(queueIndex)){
                    stringIndex += 1;
                    lastAction = TimeUtils.millis();
                    this.setText(stringQueue.get(queueIndex).substring(0,stringIndex));
                }
            }

        }
    }


}
