import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class GUI extends JFrame {

    private final JLabel userNameLabel;
    private final JTextField userName;
    private final JLabel passwordLabel;
    private final JPasswordField password;
    private final JButton loginButton;

    private Map<String, String> users;

    public GUI() {

        //tytul okna interfejsu
        super("Interfejs użytkownika");


        //wyglad interfejsu

        Color backgroundColor = new Color(60, 63, 65);
        Color textColor = new Color(252, 122, 1);
        Color enterColor = Color.WHITE;

        //ustawienie tla dla calego okna

        getContentPane().setBackground(backgroundColor);

        //inicjalizacja elementow

         userNameLabel = new JLabel("Nazwa uzytkownika");
         userName = new JTextField();
         passwordLabel = new JLabel("haslo");
         password = new JPasswordField();
         loginButton = new JButton("Zaloguj");

        //wyglad komponentow

        userNameLabel.setForeground(textColor);
        userNameLabel.setBackground(backgroundColor);

        userName.setForeground(enterColor);
        userName.setBackground(backgroundColor);

        passwordLabel.setForeground(textColor);
        passwordLabel.setBackground(backgroundColor);

        password.setForeground(enterColor);
        password.setBackground(backgroundColor);

        loginButton.setBackground(new Color(76, 175, 80));
        loginButton.setForeground(Color.WHITE);

        //ustawienie czcionki elementow

        try {
            Font f = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/fonts/static/Nunito-Black.ttf"));
            Font font = f.deriveFont(Font.PLAIN, 14);

            userNameLabel.setFont(font);
            userName.setFont(font);
            passwordLabel.setFont(font);
            password.setFont(font);
            loginButton.setFont(f.deriveFont(Font.PLAIN, 18));

        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

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

        users = addUsers();
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

        add(new JLabel());
        add(loginButton);

        //domyslne ustawienia okna interfejsu

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,150);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private Map<String, String> addUsers(){
        Map<String, String> users = new TreeMap<>();

        //admin
        users.put("admin", "admin");

        //pozostali uzytkownicy
        for(int pos = 1; pos < 6; pos++)
            users.put("user" + pos, "password"+ pos);
        System.out.println("\nUsers: " + users + "\n\n");
        return users;
    }

    private void performLogin(){
        users = addUsers();
        String enteredUsername = userName.getText();
        //zamiana zakodowanego hasla na tekst
        String enteredPassword = new String(password.getPassword());
        if (users.containsKey(enteredUsername) && users.get(enteredUsername).equals(enteredPassword)) {
            //otwarcie okna danego uzytkowika
            System.out.println("Wprowadzony uzytkownik: " + enteredUsername + "\nWprowadzone haslo: " + enteredPassword);
            removeAllComponents();
            userProfile(enteredUsername);
        } else {
            //wyskakujace okno informujace o nieprwidlowym hasle lub uzytkowniku
            JOptionPane.showMessageDialog(GUI.this, "Nieprawidlowe haslo lub uzytkownik!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeAllComponents() {
        getContentPane().removeAll();
    }

    private void userProfile(String user) {

        setSize(300, 430);
        setLayout(new FlowLayout());

        //ikona profilu

        Icon icon = new ImageIcon("src/main/Images/icon.png");
        JLabel jLabel = new JLabel();
        jLabel.setIcon(icon);
        jLabel.setHorizontalAlignment(JLabel.CENTER);
        add(jLabel);

        //nazwa uzytkownika
        userNameLabel.setFont(new Font("Arial", Font.BOLD, 40));
        userNameLabel.setText(user);
        userNameLabel.setHorizontalAlignment(JLabel.CENTER);
        add(userNameLabel);


        revalidate();
        repaint();
    }


}
