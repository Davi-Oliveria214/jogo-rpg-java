package tijolos;

import java.awt.image.BufferedImage;

public class Tijolo {
    private BufferedImage imageTile;
    private boolean colisao = false;

    public BufferedImage getImageTile() {
        return imageTile;
    }

    public void setImageTile(BufferedImage imageTile) {
        this.imageTile = imageTile;
    }

    public boolean isColisao() {
        return colisao;
    }

    public void setColisao(boolean colisao) {
        this.colisao = colisao;
    }
}