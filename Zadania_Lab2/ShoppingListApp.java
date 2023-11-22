import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//zadanie 3
public class ShoppingListApp extends JFrame {

    private final JTextField productTextField;
    private final DefaultListModel<String> shoppingListModel;
    private final JList<String> shoppingList;

    public ShoppingListApp() {
        setTitle("Shopping List App");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //ustawienie okna na srodku ekranu
        setLocationRelativeTo(null);

        //inicjalizacja elementow
        productTextField = new JTextField(20);
        shoppingListModel = new DefaultListModel<>();
        shoppingList = new JList<>(shoppingListModel);

        JButton addButton = new JButton("Dodaj");
        JButton removeButton = new JButton("Usuń");

        //ustawienie obslugi zdarzen dla przyciskow
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProduct();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeProduct();
            }
        });

        // Ustawienie rozkładu
        setLayout(new BorderLayout());

        // Dodanie komponentów do panelu
        JPanel inputPanel = new JPanel();
        inputPanel.add(productTextField);
        inputPanel.add(addButton);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(removeButton);

        //dodanie paneli do glownego kontenera
        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(shoppingList), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addProduct() {
        String product = productTextField.getText().trim();
        if (!product.isEmpty()) {
            shoppingListModel.addElement(product);
            productTextField.setText("");
        }
    }

    private void removeProduct() {
        int selectedIndex = shoppingList.getSelectedIndex();
        if (selectedIndex != -1) {
            shoppingListModel.remove(selectedIndex);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ShoppingListApp shoppingListApp = new ShoppingListApp();
                shoppingListApp.setVisible(true);
            }
        });
    }
}
