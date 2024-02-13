package com.wizard;

import com.wizard.staffs.MintStaff;
import com.wizard.staffs.Staff;

public class ChillPill extends Loot{
    public ChillPill(float x, float y) {
        super(x, y,"chillPill.png",null);
        super.interactionText = "Press [E] to take a Chill Pill.";
    }

}
