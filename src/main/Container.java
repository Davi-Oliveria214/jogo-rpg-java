package main;

import javax.swing.*;

public class Container extends JFrame {

    public Container() {
        JogoPainel jogoPainel = new JogoPainel();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Jogo");

        add(jogoPainel);
        jogoPainel.startGame();
        this.pack();
        this.setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Container();
    }
}