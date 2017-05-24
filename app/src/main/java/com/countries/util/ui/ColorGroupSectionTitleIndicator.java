package com.countries.util.ui;

/**
 * @author Tosin Onikute.
 */

import android.content.Context;
import android.util.AttributeSet;

import com.countries.util.ui.color.ColorGroup;
import xyz.danoz.recyclerviewfastscroller.sectionindicator.title.SectionTitleIndicator;


public class ColorGroupSectionTitleIndicator extends SectionTitleIndicator<ColorGroup> {

    public ColorGroupSectionTitleIndicator(Context context) {
        super(context);
    }

    public ColorGroupSectionTitleIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ColorGroupSectionTitleIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setSection(ColorGroup colorGroup) {
        // Example of using a single character
        setTitleText(colorGroup.getName().charAt(0) + "");

        setIndicatorTextColor(colorGroup.getAsColor());
    }

}