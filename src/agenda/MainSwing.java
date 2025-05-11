package agenda;
import javax.swing.*;
import java.awt.*;

public class MainSwing {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CardManager manager = new CardManager();
            manager.chamarCardMain();
        });
    }
}
