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
        this.mapTilenum = new int[jogoPainel.getMaxScreenWidth()][jogoPainel.getMaxScreenHeight()];
        this.carregarMapa();
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

    public void carregarMapa() {
        try {
            InputStream is = getClass().getResourceAsStream("/res/mapas/mapa1.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            for (int altura = 0; altura < jogoPainel.getMaxScreenHeight(); altura++) {
                String numMapa = br.readLine();
                String[] numero = numMapa.split(" ");

                for (int largura = 0; largura < jogoPainel.getMaxScreenWidth(); largura++) {
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
        int x = 0;
        int y = 0;

        while (largura < jogoPainel.getMaxScreenWidth() && altura < jogoPainel.getMaxScreenHeight()) {
            int numMapa = mapTilenum[largura][altura];

            g2d.drawImage(tijolo[numMapa].getImageTile(), x, y, jogoPainel.tileSize, jogoPainel.tileSize, null);

            largura++;
            x += jogoPainel.tileSize;

            if (largura == jogoPainel.getMaxScreenWidth()) {
                largura = 0;
                x = 0;

                altura++;
                y += jogoPainel.tileSize;
            }
        }
    }
}