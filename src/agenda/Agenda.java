/*package agenda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;

public class Agenda  extends JFrame {
    private Persistencia persistencia;
    private String arquivo;

    public Agenda() {
        setTitle("Agenda de Contatos");
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Persistencia persistencia = new Persistencia("contatos.txt");
        persistencia.criarArquivo();

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel button = new JPanel();
        button.setLayout(new GridLayout(5, 2, 10, 10));

        addButton(button, "Criar Arquivo", e -> criarArquivo());

        panel.add(button, BorderLayout.SOUTH);
        add(panel);

    }

    private void addButton(JPanel panel, String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.addActionListener(listener);
        panel.add(button);
    }

    public void criarArquivo() {
        File file = new File(arquivo);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Agenda().setVisible(true);
        });
    }
}
*/



