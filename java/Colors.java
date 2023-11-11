import javax.swing.*;
import java.awt.*;

public interface Colors {
    //wyglad interfejsu

    Color backgroundColor = new Color(60, 63, 65);
    Color textColor = new Color(252, 122, 1);
    Color enterColor = Color.WHITE;
    Color buttonColor = new Color(76, 175, 80);

    enum ColorType {
        ENTER, DEFAULT
    }


    default void setColors(Component... components) {
        setColors(ColorType.DEFAULT, components);
    }

    default void setColors(ColorType type, Component... components) {
        for (Component component : components) {
            switch (type) {
                case ENTER -> component.setForeground(enterColor);
                case DEFAULT -> component.setForeground(textColor);
            }
            component.setBackground(backgroundColor);
        }
    }

    default void setButtonColor(JButton... buttons) {
        for (JButton button : buttons) {
            button.setBackground(buttonColor);
            button.setForeground(enterColor);
        }
    }


}