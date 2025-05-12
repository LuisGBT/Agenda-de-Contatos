package agenda;

import javax.swing.*;
import java.awt.*;

public class CardMain extends JLabel {
    protected JPanel panelTop;
    protected JPanel panelMid;
    protected JPanel panelBottom;
    protected JPanel cardMain;
    protected JLabel titulo;
    protected JLabel rodape;

    public void criarTopo(){
        panelTop = new JPanel();
        titulo = new JLabel("AGENDA DE CONTATOS");
        titulo.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
        panelTop.add(titulo);
    }

    public void criarMid(){
        //Definiçoes do meio (CardMain)
        panelMid = new JPanel();
        panelMid.setBackground(new Color(255, 255, 255));
        this.loopOpcoes();
    }

    public void criarBottom(){
        //Definiçoes do Bottom (CardMain)
        panelBottom = new JPanel();
        rodape = new JLabel("@ by Luis Gustavo");
        rodape.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        panelBottom.add(rodape);
    }

    public void loopOpcoes(){
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
        for(String name: opcoes){
            Botao btn = new Botao(name,180,50);
            JButton botao = btn.criarBotao();
            panelMid.add(botao);
            botao.addActionListener(e -> {
                String opcao = e.getActionCommand();
                CardManager.chamarOutrosCards(opcao);
            });
        }
    }

    public JPanel criarCard() {
        cardMain = new JPanel();
        cardMain.setLayout(new BorderLayout(0,0));

        this.criarTopo();
        this.criarMid();
        this.criarBottom();

        cardMain.add(panelTop, BorderLayout.NORTH); //Add topo no CardMain
        cardMain.add(panelMid, BorderLayout.CENTER); //Add meio no CardMain
        cardMain.add(panelBottom, BorderLayout.SOUTH); //Add Bottom no CardMain

        return cardMain;
    }
}
