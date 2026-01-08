package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Teclas implements KeyListener {
    private boolean esquerda, direita, cima, baixo, stop;

    public Teclas() {
        this.stop = true;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int codigo = e.getKeyCode();

        if (codigo == KeyEvent.VK_W) {
            cima = true;
        }
        if (codigo == KeyEvent.VK_S) {
            baixo = true;
        }
        if (codigo == KeyEvent.VK_A) {
            esquerda = true;
        }
        if (codigo == KeyEvent.VK_D) {
            direita = true;
        }

        stop = false;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int codigo = e.getKeyCode();

        if (codigo == KeyEvent.VK_W) {
            cima = false;

        }
        if (codigo == KeyEvent.VK_S) {
            baixo = false;
        }
        if (codigo == KeyEvent.VK_A) {
            esquerda = false;
        }
        if (codigo == KeyEvent.VK_D) {
            direita = false;
        }

        stop = true;
    }

    public boolean isStop() {
        return stop;
    }

    public boolean isCima() {
        return cima;
    }

    public void setCima(boolean cima) {
        this.cima = cima;
    }

    public boolean isEsquerda() {
        return esquerda;
    }

    public void setEsquerda(boolean esquerda) {
        this.esquerda = esquerda;
    }

    public boolean isDireita() {
        return direita;
    }

    public void setDireita(boolean direita) {
        this.direita = direita;
    }

    public boolean isBaixo() {
        return baixo;
    }

    public void setBaixo(boolean baixo) {
        this.baixo = baixo;
    }
}
