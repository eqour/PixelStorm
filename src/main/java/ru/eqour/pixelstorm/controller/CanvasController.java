package ru.eqour.pixelstorm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.eqour.pixelstorm.model.CanvasInfo;
import ru.eqour.pixelstorm.service.CanvasService;

@RestController
@RequestMapping("/canvas")
public class CanvasController {

    private final CanvasService canvasService;

    public CanvasController(CanvasService canvasService) {
        this.canvasService = canvasService;
    }

    @GetMapping
    public CanvasInfo getCanvasInfo() {
        return new CanvasInfo(canvasService.getCanvasSize(), canvasService.getCanvasData());
    }
}
