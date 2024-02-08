package com.wizard.UIpackage;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.wizard.Constant;
import com.wizard.TextureManager;
import sun.jvm.hotspot.utilities.BitMap;


//I AM JUST GOING TO ASSUME THIS GETS LOADED IN AFTER WORLD DOES


//If you want to display text at the top in white, and have it print
//do Wizard.o.displayText("BLA BLAH ALBLAH");
// if you want to change the blue text, which doesnt print, you do
// Wizard.o.bottomText.setText("Blah blah blah blah");
public class Overlay extends Stage {
    private ScreenPrinter textField;
    public Inventory inventory;
    public BottomText bottomText;
    private BitmapFont font = new BitmapFont();
    public Overlay(){
        super(new ScreenViewport());

        textField = new ScreenPrinter(this);
        this.addActor(textField);
        inventory = new Inventory();
        this.addActor(inventory);
        inventory.setBounds(30,20,100,100);

        bottomText = new BottomText();
        this.addActor(bottomText);
        bottomText.setText("Use WASD to move. Click to shoot.");
    }

    public void displayText(String newText){
        this.textField.printText(newText, Constant.textSpeed);
    }

    public void resize(int width, int height){
        this.getViewport().update(width, height,true);
        this.textField.resize(width, height);
        this.bottomText.resize(width,height);
        this.getCamera().update();
    }
    public void draw(){
        this.getViewport().apply();
        super.draw();
    }
}
