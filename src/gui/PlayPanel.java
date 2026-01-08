package gui;

import main.JogoPainel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayPanel extends JPanel implements ActionListener {
    private JogoPainel jp;
    private JButton btnT;

    public PlayPanel(JogoPainel jp) {
        this.jp = jp;
        this.setPreferredSize(new Dimension(jp.getScreenWidth(), jp.getScreenHeight()));
        btnT = new JButton("Play");
        this.setBackground(Color.BLACK);
        this.add(btnT);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        jp.startGame();
    }
}