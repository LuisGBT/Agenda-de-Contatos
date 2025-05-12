package agenda;

import javax.swing.*;
import java.awt.*;

public class CardCriarArquivo extends JPanel {
    protected JLabel titulo;
    protected JLabel tituloTop;
    protected JPanel criarArquivo;
    protected JTextField label;

    public CardCriarArquivo() {
        criarArquivo = new JPanel();
        criarArquivo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setLayout(null);
        criarArquivo.setLayout(null);
        criarArquivo.setBounds(17, 65, 325, 225);

        titulo = new JLabel("Digite o nome do novo arquivo ");
        titulo.setFont(new Font("Arial, Helvetica, sans-serif", Font.BOLD, 16));
        titulo.setBounds(60, 20, 350, 35);

        label = new JTextField();
        label.setBounds(40, 45, 200, 30);


        Botao btn1 = new Botao("<-",50,30);
        JButton botao1 = btn1.criarBotao();
        botao1.setBounds(0, 0, 50, 25);


        Botao btn = new Botao("Criar",120,40);
        JButton botao = btn.criarBotao();
        botao.setBounds(80, 70, 120, 25);

        criarArquivo.add(titulo);
        criarArquivo.add(label);
        criarArquivo.add(botao);

        add(botao1);
        add(criarArquivo);
    }
}
