package tijolos;

import main.JogoPainel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class GeradorTijolo {
    private final JogoPainel jogoPainel;
    private final Tijolo[] tijolo;
    private final int[][] mapTilenum;

    public GeradorTijolo(JogoPainel jogoPainel) {
        this.jogoPainel = jogoPainel;

        tijolo = new Tijolo[10];
        this.getImagemTijolo();
        this.mapTilenum = new int[jogoPainel.maxWorldWidth][jogoPainel.maxWorldHeight];
        this.carregarMapa("/res/mapas/mapa1.txt");
    }

    public void getImagemTijolo() {
        try {
            tijolo[0] = new Tijolo();
            tijolo[0].setImageTile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tijolos/grama.png"))));

            tijolo[1] = new Tijolo();
            tijolo[1].setImageTile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tijolos/pedra.png"))));

            tijolo[2] = new Tijolo();
            tijolo[2].setImageTile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tijolos/agua.png"))));

            tijolo[3] = new Tijolo();
            tijolo[3].setImageTile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tijolos/lava.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void carregarMapa(String mapa) {
        try {
            InputStream is = getClass().getResourceAsStream(mapa);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            for (int altura = 0; altura < jogoPainel.maxWorldHeight; altura++) {
                String numMapa = br.readLine();
                String[] numero = numMapa.split(" ");

                for (int largura = 0; largura < jogoPainel.maxWorldWidth; largura++) {
                    if (largura < numero.length) {
                        int num = Integer.parseInt(numero[largura]);
                        mapTilenum[largura][altura] = num;
                    }
                }
            }

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gerarTijolo(Graphics2D g2d) {
        int altura = 0;
        int largura = 0;

        while (largura < jogoPainel.maxWorldWidth && altura < jogoPainel.maxWorldHeight) {
            int numMapa = mapTilenum[largura][altura];

            int worldX = largura * jogoPainel.tileSize;
            int worldY = altura * jogoPainel.tileSize;
            int screenX = worldX - jogoPainel.getPlayer().getWordX() + jogoPainel.getPlayer().getScreenX();
            int screenY = worldY - jogoPainel.getPlayer().getWordY() + jogoPainel.getPlayer().getScreenY();

            g2d.drawImage(tijolo[numMapa].getImageTile(), screenX, screenY, jogoPainel.tileSize, jogoPainel.tileSize, null);
            largura++;

            if (largura == jogoPainel.maxWorldWidth) {
                largura = 0;
                altura++;
            }
        }
    }
}