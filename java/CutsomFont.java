import java.awt.*;
import java.io.File;
import java.io.IOException;

public interface CutsomFont {

    default void fonts(int size, Component... components){
        try {
            Font f = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/fonts/static/Nunito-Black.ttf"));
            Font font = f.deriveFont(Font.PLAIN, size);

            for (Component component : components) {
                component.setFont(font);
            }

        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }
}