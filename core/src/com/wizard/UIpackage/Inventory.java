package com.wizard.UIpackage;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
import com.wizard.TextureManager;
import com.wizard.Wizard;
import org.w3c.dom.Text;

public class Inventory extends Group {
    public Image weaponOne = null;
    public Image weaponTwo = null;
    public Inventory(){
        super();

        Image frameOne = new Image(TextureManager.get("frame.png"));
        Image frameTwo = new Image(TextureManager.get("frame.png"));

        frameOne.setBounds(0,0,66,66);
        frameTwo.setBounds(77,82,33,33);

        Label label1 = new Label("Primary Spell",TextureManager.mSkin);
        label1.setBounds(0,-10,66,10);
        label1.setFontScale(0.5F);
        label1.setAlignment(Align.center);
        Label label2 = new Label("Secondary Spell",TextureManager.mSkin);
        label2.setBounds(77,72,33,10);
        label2.setAlignment(Align.center);
        label2.setFontScale(0.5F);
        this.addActor(frameOne);
        this.addActor(frameTwo);

        this.addActor(label1);
        this.addActor(label2);

        Label runeLabel = new Label("Runes", TextureManager.mSkin);
        runeLabel.setBounds(-10,100,50,10);
        runeLabel.setAlignment(Align.center);
        runeLabel.setFontScale(0.5F);

//        this.addActor(runeLabel);

        Table runeTable =  new Table();
        runeTable.add(new Image(TextureManager.get("frame.png")));
        runeTable.row();
        runeTable.add(new Image(TextureManager.get("frame.png")));
        runeTable.row();
        runeTable.add(new Image(TextureManager.get("frame.png")));
        runeTable.bottom();
        runeTable.setBounds(-10,110,50,200);
        // can be enabled later when we want to add runes
//        this.addActor(runeTable);
    }

    public void setWeaponOne(String weapon){
        Wizard.o.inventory.removeActor(weaponOne);//Fixes visual bug (Honestly impressed i figured out how to do this lol)

        weaponOne = new Image(TextureManager.get(weapon));
        //we want to make sure aspect ratio is preserved here
        float scalingFactor = 64.0f/Math.max(weaponOne.getHeight(), weaponOne.getWidth());
        weaponOne.setBounds(0,0,scalingFactor*weaponOne.getWidth(), scalingFactor*weaponOne.getHeight());
        weaponOne.setPosition((64.0f - weaponOne.getWidth())/2.0f,(64.0f - weaponOne.getHeight())/2.0f);
        this.addActor(weaponOne);
    }

    public void setWeaponTwo(String weapon){
        Wizard.o.inventory.removeActor(weaponTwo);//Fixes visual bug (Honestly impressed i figured out how to do this lol)

        weaponTwo= new Image(TextureManager.get(weapon));
        //we want to make sure aspect ratio is preserved here
        float scalingFactor = 33.0f/Math.max(weaponTwo.getHeight(), weaponTwo.getWidth());
        weaponTwo.setBounds(0,0,scalingFactor*weaponTwo.getWidth(), scalingFactor*weaponTwo.getHeight());
        weaponTwo.setPosition(77+(33.0f - weaponTwo.getWidth())/2.0f,82+(33.0f - weaponTwo.getHeight())/2.0f);
        this.addActor(weaponTwo);
    }
}
