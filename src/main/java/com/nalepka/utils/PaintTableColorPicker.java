package com.nalepka.utils;

import com.itextpdf.text.BaseColor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaintTableColorPicker {
    public BaseColor color;

    public PaintTableColorPicker() {
        this.color = BaseColor.WHITE;
    }

    public BaseColor getNextColor(){
        BaseColor result = getColor();
        setColor((color == BaseColor.WHITE) ? BaseColor.LIGHT_GRAY: BaseColor.WHITE);

        return result;
    }
}
