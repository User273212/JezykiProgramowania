import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserProfile extends GUI{

    private final JLabel userNameLabel;
    private final JButton logOut;
    private final JButton deleteUser;
    public UserProfile(String user) {
        userNameLabel = getUserNameLabel();
        logOut = new JButton("Wyloguj sie");
        deleteUser = new JButton("Usun uzytkownika");
        visuals(user);

        logOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        deleteUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(UserProfile.this, "Czy na pewno chcesz usunac tego uzytkownika ?", "Potwierdzenie", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    new DeleteProfile(userNameLabel.getText());
                    dispose();
                }
            }
        });
    }

    private void visuals(String user) {
        removeAllComponents();

        setSize(320, 400);
        setLayout(new BorderLayout(5, 3));

        //przycisk wylogowania w prawym gornym rogu
        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        fonts(15, logOut, deleteUser);
        deleteUser.setBackground(Color.RED);
        deleteUser.setForeground(Color.BLACK);
        logoutPanel.add(deleteUser);
        logoutPanel.add(logOut);
        add(logoutPanel, BorderLayout.NORTH);

        //ikona i nazwa profilu
        JPanel panelCenter = new JPanel(new BorderLayout());

        //ikona
        Icon icon = new ImageIcon("src/main/Images/icon.png");
        JLabel jLabel = new JLabel();
        jLabel.setIcon(icon);
        jLabel.setHorizontalAlignment(JLabel.CENTER);
        panelCenter.add(jLabel, BorderLayout.CENTER);

        //nazwa profilu
        fonts(40, userNameLabel);
        userNameLabel.setText(user);
        userNameLabel.setHorizontalAlignment(JLabel.CENTER);
        panelCenter.add(userNameLabel, BorderLayout.SOUTH);

        add(panelCenter, BorderLayout.CENTER);

        setButtonColor(logOut);
        setColors(userNameLabel, logoutPanel, panelCenter);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        revalidate();
        repaint();
    }

}
