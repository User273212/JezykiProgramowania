import javax.swing.*;

public class USER {
    public static void main(String[] args) {

        // Uruchomienie GUI w wątku rozkładu zdarzeń

        SwingUtilities.invokeLater(() -> new GUI());


    }

}
