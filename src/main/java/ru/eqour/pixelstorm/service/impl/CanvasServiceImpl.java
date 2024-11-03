package ru.eqour.pixelstorm.service.impl;

import org.springframework.stereotype.Service;
import ru.eqour.pixelstorm.model.CanvasPixel;
import ru.eqour.pixelstorm.service.CanvasService;

@Service
public class CanvasServiceImpl implements CanvasService {

    private static final int CANVAS_SIZE = 40;
    private final String[][] canvasData;

    public CanvasServiceImpl() {
        canvasData = initCanvasData();
    }

    private String[][] initCanvasData() {
        String[][] data = new String[CANVAS_SIZE][];
        for (int i = 0; i < CANVAS_SIZE; i++) {
            data[i] = new String[CANVAS_SIZE];
            for (int j = 0; j < CANVAS_SIZE; j++) {
                data[i][j] = "#FFFFFF";
            }
        }
        return data;
    }

    @Override
    public void updatePixel(CanvasPixel pixel) {
        if (pixel.getX() < 0 || pixel.getX() >= CANVAS_SIZE || pixel.getY() < 0 || pixel.getY() >= CANVAS_SIZE) {
            return;
        }
        canvasData[pixel.getY()][pixel.getX()] = pixel.getColor();
    }

    @Override
    public String[][] getCanvasData() {
        return canvasData;
    }

    @Override
    public int getCanvasSize() {
        return CANVAS_SIZE;
    }
}
