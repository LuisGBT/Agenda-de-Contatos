package agenda;

import javax.swing.*;
import java.awt.*;

public class CardManager extends JFrame {
    protected JFrame frame;
    protected JPanel cardMain;
    protected JPanel cardCriarArquivo;
    private static CardLayout cardLayout;
    public static JPanel cardManager;

    public CardManager() {
        frame = new JFrame("Agenda de Contatos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Garante que o programa ira fechar corretamente (Não ficar rodando em segundo plano)
        frame.setSize(400, 400); // Setando o tamanho da janela
        frame.setLocationRelativeTo(null); //  Centraliza o programa na tela
        frame.setVisible(true);
        cardLayout = new CardLayout();
        cardManager = new JPanel(cardLayout);//Gerenciador de cards

    }

    public JPanel getCardManager() {
        return cardManager;
    }

    public void chamarCardMain() {
        CardMain cd = new CardMain();
        cardMain =  cd.criarCard();
        cardManager.add(cardMain, "cardMain");

        frame.add(cardManager);
    }

    public static void chamarOutrosCards(String name) {
        cardManager.add(new CardCriarArquivo(), "cardCriarArquivo");

        switch (name){
            case "📁 Criar Arquivo":
                cardLayout.show(cardManager, "cardCriarArquivo");
                break;
        }
    }

}
