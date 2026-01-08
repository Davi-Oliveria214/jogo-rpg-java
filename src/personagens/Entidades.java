package personagens;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entidades {
    // Posição do personagem no mundo e a velocidade
    protected int wordX, wordY;
    protected int velocidade;

    // Imagens da posição do personagem
    protected BufferedImage up, up1, up2, down, down1, down2, left, left1, left2, right, right1, right2;
    protected String direcao;

    // Contadores para alterar a imagem do personagem
    protected int spriteCounter = 0;
    protected int spriteNum = 1;

    // Colisão do personagem
    protected Rectangle areaSolida;
    protected boolean colisaoOn = false;

    public String getDirecao() {
        return direcao;
    }

    public boolean isColisaoOn() {
        return colisaoOn;
    }

    public Rectangle getAreaSolida() {
        return areaSolida;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public void setColisaoOn(boolean colisaoOn) {
        this.colisaoOn = colisaoOn;
    }

    public int getWordX() {
        return wordX;
    }

    public int getWordY() {
        return wordY;
    }
}