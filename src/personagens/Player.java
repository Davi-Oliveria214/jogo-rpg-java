package personagens;

import main.JogoPainel;
import main.Teclas;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entidades {
    private final Teclas tm;
    private final JogoPainel jogoPainel;

    public Player(Teclas tm, JogoPainel jogoPainel) {
        this.tm = tm;
        this.jogoPainel = jogoPainel;

        this.setValores();
        this.getPlayerImg();
    }

    public void setValores() {
        this.x = 50;
        this.y = 50;
        this.velocidade = 4;
        this.direcao = "down";
    }

    public void carregar(Graphics2D g2d) {
        BufferedImage img = null;


        if (tm.isBaixo() || tm.isCima() || tm.isEsquerda() || tm.isDireita()) {
            switch (this.direcao) {
                case "down":
                    if (spriteNum == 1) {
                        img = this.down1;
                    }
                    if (spriteNum == 2) {
                        img = this.down2;
                    }
                    break;
                case "up":
                    if (spriteNum == 1) {
                        img = this.up1;
                    }
                    if (spriteNum == 2) {
                        img = this.up2;
                    }
                    break;
                case "left":
                    if (spriteNum == 1) {
                        img = this.left1;
                    }
                    if (spriteNum == 2) {
                        img = this.left2;
                    }
                    break;
                case "right":
                    if (spriteNum == 1) {
                        img = this.right1;
                    }
                    if (spriteNum == 2) {
                        img = this.right2;
                    }
                    break;
            }
        } else {
            img = switch (direcao) {
                case "down" -> this.down;
                case "up" -> this.up;
                case "left" -> this.left;
                case "right" -> this.right;
                default -> img;
            };
        }

        g2d.drawImage(img, this.x, this.y, this.jogoPainel.tileSize, this.jogoPainel.tileSize, null);
    }

    public void getPlayerImg() {
        try {
            up = ImageIO.read(getClass().getResourceAsStream("/res/player/ashura-up.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/player/ashura-up-1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/player/ashura-up-2.png"));

            down = ImageIO.read(getClass().getResourceAsStream("/res/player/ashura-down.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/player/ashura-down-1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/player/ashura-down-2.png"));

            left = ImageIO.read(getClass().getResourceAsStream("/res/player/ashura-left.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/player/ashura-left-1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/player/ashura-left-2.png"));

            right = ImageIO.read(getClass().getResourceAsStream("/res/player/ashura-right.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/player/ashura-right-1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/player/ashura-right-2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (tm.isCima()) {
            this.direcao = "up";
            this.y -= velocidade;
        } else if (tm.isBaixo()) {
            this.direcao = "down";
            this.y += velocidade;
        } else if (tm.isDireita()) {
            this.direcao = "right";
            this.x += velocidade;
        } else if (tm.isEsquerda()) {
            this.direcao = "left";
            this.x -= velocidade;
        }

        this.spriteCounter++;
        if (this.spriteCounter > 12) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            this.spriteCounter = 0;
        }
    }
}