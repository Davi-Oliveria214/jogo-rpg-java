package tijolos;

import main.JogoPainel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class GeradorTijolo {
    public final int[][] mapTilenum;
    public final List<Tijolo> tijolos;
    private final JogoPainel jogoPainel;

    public GeradorTijolo(JogoPainel jogoPainel) {
        this.jogoPainel = jogoPainel;

        tijolos = new ArrayList<>();
        this.getImagemTijolo();
        this.mapTilenum = new int[jogoPainel.maxWorldWidth][jogoPainel.maxWorldHeight];
        this.carregarMapa("/res/mapas/Mapa1.txt");
    }

    public void getImagemTijolo() {
        Path diretorio = Paths.get("src/res/tijolos/");
        try (Stream<Path> stream = Files.list(diretorio)) {
            List<String> lista = stream.filter(Files::isRegularFile).map(path -> path.getFileName().toString()).sorted((a, b) -> {
                int n1 = Integer.parseInt(a.split("_")[0]);
                int n2 = Integer.parseInt(b.split("_")[0]);
                return Integer.compare(n1, n2);
            }).toList();

            List<String> listaSolidos = Files.readAllLines(Path.of("src/res/utilitarios/solidos"));

            for (String bloco : lista) {
                String semExtensao = bloco.substring(0, bloco.lastIndexOf("."));
                String semId = semExtensao.split("_", 2)[1];
                String blocoVerificar = semId.split("_")[0];

                Tijolo tijolo = new Tijolo();
                for (String solidos : listaSolidos) {
                    if (solidos.equals(blocoVerificar)) {
                        tijolo.setColisao(true);
                    }
                }

                tijolo.setImageTile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tijolos/" + bloco))));
                this.tijolos.add(tijolo);
            }
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

            g2d.drawImage(tijolos.get(numMapa).getImageTile(), screenX, screenY, jogoPainel.tileSize, jogoPainel.tileSize, null);
            largura++;

            if (largura == jogoPainel.maxWorldWidth) {
                largura = 0;
                altura++;
            }
        }
    }
}