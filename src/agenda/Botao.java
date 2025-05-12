package agenda;

import javax.swing.*;
import java.awt.*;

public class Botao {
    private String name;
    private Dimension dimension;
    private int width;
    private int height;

    public Botao(String name,int width,int height) {
        this.name = name;
        this.width = width;
        this.height = height;
    }

    public JButton criarBotao() {
        JButton botao = new JButton(this.name);
        dimension = new Dimension(this.width, this.height);
        botao.setPreferredSize(this.dimension);
        botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return botao;
    }

}
