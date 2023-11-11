import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserProfile extends GUI{

    private final JLabel userNameLabel;
    private final JButton logOut;
    public UserProfile(String user) {
        userNameLabel = getUserNameLabel();
        logOut = new JButton("Wyloguj sie");
        visuals(user);

        logOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void visuals(String user) {
        removeAllComponents();

        setSize(300, 400);
        setLayout(new BorderLayout(5, 3));

        //przycisk wylogowania w prawym gornym rogu
        JPanel panelButton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        fonts(15, logOut);
        panelButton.add(logOut);
        add(panelButton, BorderLayout.NORTH);

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
        setColors(userNameLabel, panelButton, panelCenter);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        revalidate();
        repaint();
    }

}
