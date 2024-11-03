package ru.eqour.pixelstorm.service;

import ru.eqour.pixelstorm.model.CanvasPixel;

public interface CanvasService {

    void updatePixel(CanvasPixel pixel);
    String[][] getCanvasData();
    int getCanvasSize();
}
