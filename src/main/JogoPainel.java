package main;

import personagens.Player;
import tijolos.GeradorTijolo;

import javax.swing.*;
import java.awt.*;

public class JogoPainel extends JPanel implements Runnable {
    private final int originalTileSize = 16;
    private final int scale = 3;

    public final int tileSize = originalTileSize * scale;

    private final int maxScreenWidth = 16;
    private final int maxScreenHeight = 12;

    private final int FPS = 60;
    private final GeradorTijolo geradorTijolo;
    private final Teclas teclas;
    private final Player player;
    private Thread loopGame;

    public JogoPainel() {
        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension(this.getScreenWidth(), this.getScreenHeight()));
        this.setDoubleBuffered(true);

        teclas = new Teclas();
        geradorTijolo = new GeradorTijolo(this);
        player = new Player(teclas, this);

        this.addKeyListener(teclas);
        this.setFocusable(true);
    }

    public int getMaxScreenHeight() {
        return maxScreenHeight;
    }

    public int getMaxScreenWidth() {
        return maxScreenWidth;
    }

    public int getScreenWidth() {
        return tileSize * maxScreenWidth;
    }

    public int getScreenHeight() {
        return tileSize * maxScreenHeight;
    }

    public void startGame() {
        loopGame = new Thread(this);
        loopGame.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        geradorTijolo.gerarTijolo(g2d);
        player.carregar(g2d);
    }

    @Override
    public void run() {
        double drawIntervalo = (double) 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (loopGame != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawIntervalo;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                this.update();
                repaint();

                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    private void update() {
        player.update();
    }
}