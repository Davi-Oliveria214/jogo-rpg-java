package personagens;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entidades {
    protected Image personagem;

    protected int wordX, wordY;
    protected int velocidade;

    protected BufferedImage up, up1, up2, down, down1, down2, left, left1, left2, right, right1, right2;
    protected String direcao;
    protected int spriteCounter = 0;
    protected int spriteNum = 1;

    private String nome;
    private int dano;
    private int idade;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getDano() {
        return dano;
    }

    public void setDano(int dano) {
        this.dano = dano;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Image getPersonagem() {
        return personagem;
    }

    public void setPersonagem(Image personagem) {
        this.personagem = personagem;
    }

    public int getWordX() {
        return wordX;
    }

    public void setWordX(int wordX) {
        this.wordX = wordX;
    }

    public int getWordY() {
        return wordY;
    }

    public void setWordY(int wordY) {
        this.wordY = wordY;
    }
}