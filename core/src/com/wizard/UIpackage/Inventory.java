package com.wizard.UIpackage;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
import com.wizard.TextureManager;
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

        this.addActor(runeLabel);

        Table runeTable =  new Table();
        runeTable.add(new Image(TextureManager.get("frame.png")));
        runeTable.row();
        runeTable.add(new Image(TextureManager.get("frame.png")));
        runeTable.row();
        runeTable.add(new Image(TextureManager.get("frame.png")));
        runeTable.bottom();
        runeTable.setBounds(-10,110,50,200);
        this.addActor(runeTable);
    }

    public void setWeaponOne(String weapon){
        weaponOne = new Image(TextureManager.get(weapon));
        weaponOne.setBounds(0,0,66,66);
        this.addActor(weaponOne);
    }

    public void setWeaponTwo(String weapon){
        weaponTwo = new Image(TextureManager.get(weapon));
        weaponTwo.setBounds(77,82,33,33);
        this.addActor(weaponTwo);
    }
}
