package com.wizard.UIpackage;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.wizard.Constant;
import com.wizard.TextureManager;
import sun.jvm.hotspot.utilities.BitMap;


//I AM JUST GOING TO ASSUME THIS GETS LOADED IN AFTER WORLD DOES

public class Overlay extends Stage {
    private ScreenPrinter textField;
    private BitmapFont font = new BitmapFont();
    public Overlay(){
        super(new ScreenViewport());
        textField = new ScreenPrinter(this);
        this.addActor(textField);
        this.textField.queueText("Arcane Tablet:\nWelcome, human! This land has long been terrorized by ghosts. Can you save us?", 30,2000);
    }

    public void displayText(String newText){
        this.textField.printText(newText, Constant.textSpeed);
    }

    public void resize(int width, int height){
        this.getViewport().update(width, height,true);
        this.textField.resize(width, height);
        this.getCamera().update();
    }
    public void draw(){
        this.getViewport().apply();
        super.draw();
    }
}
