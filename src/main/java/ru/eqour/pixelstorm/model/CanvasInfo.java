package ru.eqour.pixelstorm.model;

public class CanvasInfo {

    private int size;
    private String[][] data;

    public CanvasInfo() {
    }

    public CanvasInfo(int size, String[][] data) {
        this.size = size;
        this.data = data;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String[][] getData() {
        return data;
    }

    public void setData(String[][] data) {
        this.data = data;
    }
}
