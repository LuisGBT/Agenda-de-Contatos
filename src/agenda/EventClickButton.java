package agenda;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventClickButton implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String opc = e.getActionCommand();

        AgendaOptions agenda = new AgendaOptions();
        switch (opc){
            case "📁 Criar Arquivo":
                agenda.criarArquivo();
                break;
            case "📂 Selecionar Arquivo":

                break;
            case "➕ Adicionar Contato":

                break;
            case "📋 Listar Contatos":

                break;
            case "✏️ Editar Registro":

                break;
            case "🗃️ Listar Arquivos":

                break;
            case "🔍 Buscar Contato":

                break;
            case "❌ Excluir Arquivo":

                break;
            case "💾 Realizar Backup":

                break;
            case "❌ Excluir Contato":

                break;
        }
    }
}
