package agenda;
import javax.swing.*;
import java.awt.*;

public class MainSwing {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaPrincipal tp = new TelaPrincipal();
            tp.chamarTelaPrincipal();
        });
    }
}
