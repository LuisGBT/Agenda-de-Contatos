package agenda;
import javax.swing.*;
import java.awt.*;

public class MainSwing {

    private static JButton criarBotao(String texto){
        JButton btn = new JButton(texto);
        Dimension d = new Dimension(180, 50);

        btn.setPreferredSize(d);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        return btn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Agenda de Contatos"); //Cria uma nova janela
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Garante que o programa ira fechar corretamente (Não ficar rodando em segundo plano)
            frame.setSize(400, 400); // Setando o tamanho da janela
            frame.setLocationRelativeTo(null); //  Centraliza o programa na tela
            frame.setLayout(new BorderLayout(0, 0)); // Definindo o layout


            JPanel panelTop = new JPanel();//Definiçoes do painel do topo

            JLabel titulo = new JLabel("AGENDA DE CONTATOS");
            titulo.setFont(new Font("Comic Sans MS", Font.BOLD, 25));

            JPanel panelForm = new JPanel();
            panelForm.setBackground(new Color(255, 255, 255));

            JPanel panelBottom = new JPanel();
            JLabel rodape = new JLabel("@ by Luis Gustavo");
            rodape.setFont(new Font("Comic Sans MS", Font.BOLD, 15));

            String[] opcoes = {
                    "📁 Criar Arquivo",
                    "📂 Selecionar Arquivo",
                    "➕ Adicionar Contato",
                    "📋 Listar Contatos",
                    "✏️ Editar Registro",
                    "🗃️ Listar Arquivos",
                    "🔍 Buscar Contato",
                    "❌ Excluir Arquivo",
                    "💾 Realizar Backup",
                    "❌ Excluir Contato"
            };

            for(String texto: opcoes){
             JButton btn = (JButton) panelForm.add(criarBotao(texto));
             btn.addActionListener(new EventClickButton());
            }



            panelTop.add(titulo);
            panelBottom.add(rodape);

            frame.add(panelTop, BorderLayout.NORTH);
            frame.add(panelForm, BorderLayout.CENTER);
            frame.add(panelBottom, BorderLayout.SOUTH);

            frame.setVisible(true); // Ativando a visibilidade da tela
        });
    }
}
