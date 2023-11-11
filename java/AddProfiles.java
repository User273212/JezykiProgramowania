import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class AddProfileWindow extends JFrame implements Colors, CutsomFont {

    private final JLabel title;
    private final JLabel profileLabel;
    private final JTextField profileName;
    private final JLabel passwordLabel;
    private final JPasswordField password;
    private final JButton insert;

    public AddProfileWindow() {
        super("Utworz nowy profil");
        title = new JLabel("Dodaj nowego uzytkownika");
        profileLabel = new JLabel("Nazwa uzytkownika:");
        profileName = new JTextField();
        passwordLabel = new JLabel("Haslo:");
        password = new JPasswordField(10);
        insert = new JButton("Dodaj uzytkownika");

        visuals();

        insert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addUser();
            }
        });


        setSize(380, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }


    private void visuals() {
        setLayout(new BorderLayout(5, 5));

        fonts(14, title, profileLabel, profileName, passwordLabel, password, insert);

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        titlePanel.add(title);
        add(titlePanel, BorderLayout.NORTH);

        JPanel profilePanel = new JPanel(new GridLayout(3, 2, 5, 5));

        getContentPane().setBackground(backgroundColor);
        setColors(title, profileLabel, passwordLabel, titlePanel, profilePanel);
        setColors(ColorType.ENTER, profileName, password);
        setButtonColor(insert);

        profilePanel.add(profileLabel);
        profilePanel.add(profileName);
        profilePanel.add(passwordLabel);
        profilePanel.add(password);
        profilePanel.add(new JLabel());
        profilePanel.add(insert);
        add(profilePanel, BorderLayout.CENTER);
    }


private void addUser(){

        //dane do zapisania

        String profile = profileName.getText();
        String profilePassword = password.getText();

        if(profile.isEmpty() || profilePassword.isEmpty()) {
            JOptionPane.showMessageDialog(AddProfileWindow.this, "Podaj haslo oraz uzytkownika !", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //otwarcie lub stworzenie pliku przechowujacego uzywkownikow

        try{
            File users = new File("src/main/userList/profiles.txt");
            FileWriter fileWriter = new FileWriter(users, true);
                     fileWriter.write("\n" + profile + ":" + profilePassword + "\n");
            if(users.createNewFile()) {
                System.out.println("\nPlik " + users.getName() + " zostal utworzony\n");
                fileWriter.write("admin:admin\n" + "user:window1!?3");
            }else System.out.println("\nPlik " + users.getName() + " juz istnieje\n");
            fileWriter.close();
        }catch (IOException e){
            System.out.println("\nWystapil blad podczas otwierania/tworzenia pliku\n");
            e.printStackTrace();
        }
    }

}