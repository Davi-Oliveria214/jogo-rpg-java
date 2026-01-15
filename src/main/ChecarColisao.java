package main;

import personagens.Entidades;

public class ChecarColisao {
    private JogoPainel jogoPainel;

    public ChecarColisao(JogoPainel jogoPainel) {
        this.jogoPainel = jogoPainel;
    }

    public void checar(Entidades entidade) {
        int worldLeftX = entidade.getWordX() + entidade.getAreaSolida().x;
        int worldRightX = entidade.getWordX() + entidade.getAreaSolida().x + entidade.getAreaSolida().width;

        int worldTopY = entidade.getWordY() + entidade.getAreaSolida().y;
        int worldBottomY = entidade.getWordY() + entidade.getAreaSolida().y + entidade.getAreaSolida().height;

        int entityLeftRow = worldLeftX / jogoPainel.tileSize;
        int entityRightRow = worldRightX / jogoPainel.tileSize;

        int entityTopCol = worldTopY / jogoPainel.tileSize;
        int entityBottomCol = worldBottomY / jogoPainel.tileSize;

        int tileNum1 = 0, tileNum2 = 0;

        switch (entidade.getDirecao()) {
            case "up":
                entityTopCol = (worldTopY - entidade.getVelocidade()) / jogoPainel.tileSize;
                tileNum1 = jogoPainel.getGeradorTijolo().mapTilenum[entityLeftRow][entityTopCol];
                tileNum2 = jogoPainel.getGeradorTijolo().mapTilenum[entityRightRow][entityTopCol];
                break;
            case "down":
                entityBottomCol = (worldBottomY - entidade.getVelocidade()) / jogoPainel.tileSize;
                tileNum1 = jogoPainel.getGeradorTijolo().mapTilenum[entityLeftRow][entityBottomCol];
                tileNum2 = jogoPainel.getGeradorTijolo().mapTilenum[entityRightRow][entityBottomCol];
                break;
            case "right":
                entityRightRow = (worldRightX - entidade.getVelocidade()) / jogoPainel.tileSize;
                tileNum1 = jogoPainel.getGeradorTijolo().mapTilenum[entityRightRow][entityRightRow];
                tileNum2 = jogoPainel.getGeradorTijolo().mapTilenum[entityRightRow][entityBottomCol];
                break;
            case "left":
                entityLeftRow = (worldLeftX - entidade.getVelocidade()) / jogoPainel.tileSize;
                tileNum1 = jogoPainel.getGeradorTijolo().mapTilenum[entityLeftRow][entityTopCol];
                tileNum2 = jogoPainel.getGeradorTijolo().mapTilenum[entityLeftRow][entityBottomCol];
                break;
        }

        if (jogoPainel.getGeradorTijolo().tijolos.get(tileNum1).isColisao() || jogoPainel.getGeradorTijolo().tijolos.get(tileNum2).isColisao()) {
            entidade.setColisaoOn(true);
        }
    }
}