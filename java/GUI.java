import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Map;

public class GUI extends JFrame implements Colors, CutsomFont, ReadProfiles {

    private final JLabel userNameLabel;
    private final JTextField userName;
    private final JLabel passwordLabel;
    private final JPasswordField password;
    private final JButton newProfile;
    private final JButton loginButton;

    private Map<String, String> users;

    public GUI() {

        //tytul okna interfejsu
        super("Interfejs użytkownika");



        //ustawienie tla dla calego okna

        getContentPane().setBackground(backgroundColor);

        //inicjalizacja elementow

         userNameLabel = new JLabel("Nazwa uzytkownika");
         userName = new JTextField();
         passwordLabel = new JLabel("haslo");
         password = new JPasswordField();
         newProfile = new JButton("Utworz uzytkownika");
         loginButton = new JButton("Zaloguj");

        //wyglad komponentow

        setColors(userNameLabel, passwordLabel, newProfile, loginButton);
        setColors(ColorType.ENTER, userName, password);


        setButtonColor(newProfile, loginButton);

        //ustawienie czcionki elementow

        fonts(14, userNameLabel, userName, passwordLabel, password);
        fonts(14, newProfile, loginButton);

        //przechwytywanie zdarzen z danych elementow

        userName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    performLogin();
                }
            }
        });

        password.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    performLogin();
                }
            }
        });



        newProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new AddProfileWindow();

            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        });

        //rozmieszczenie komponentow

        setLayout(new GridLayout(3, 2, 10, 10));

        add(userNameLabel);
        add(userName);

        add(passwordLabel);
        add(password);

        add(newProfile);
        add(loginButton);

        //domyslne ustawienia okna interfejsu

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(450,150);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void performLogin(){
        users = readProfiles();

        if(users.size() == 0) {
            JOptionPane.showMessageDialog(GUI.this, "Brak uzytkownikow !\nDodaj uzykownika, aby kontynuowac.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String enteredUsername = userName.getText();
        //zamiana zakodowanego hasla na tekst
        String enteredPassword = new String(password.getPassword());
        if (users.containsKey(enteredUsername) && users.get(enteredUsername).equals(enteredPassword)) {
            //otwarcie okna danego uzytkowika
            System.out.println("Wprowadzony uzytkownik: " + enteredUsername + "\nWprowadzone haslo: " + enteredPassword);
            new UserProfile(enteredUsername);
        } else {
            //wyskakujace okno informujace o nieprwidlowym hasle lub uzytkowniku
            JOptionPane.showMessageDialog(GUI.this, "Nieprawidlowe haslo lub uzytkownik !", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void removeAllComponents() {
        getContentPane().removeAll();
    }

    public JLabel getUserNameLabel() {
        return userNameLabel;
    }


}