package ru.eqour.pixelstorm.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import ru.eqour.pixelstorm.model.CanvasPixel;
import ru.eqour.pixelstorm.service.CanvasService;

@Controller
public class CanvasWebsocketController {

    private final CanvasService canvasService;

    public CanvasWebsocketController(CanvasService canvasService) {
        this.canvasService = canvasService;
    }

    @MessageMapping("/update-pixel")
    @SendTo("/canvas-change")
    public String[][] updatePixel(CanvasPixel pixel) {
        canvasService.updatePixel(pixel);
        return canvasService.getCanvasData();
    }
}
