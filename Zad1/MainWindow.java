package Zad1;

//Stwórz interfejs Swing z obszarem rysowania (np. JPanel) i polem tekstowym. Po kliknięciu myszą w obszar rysowania, wyświetl współrzędne kliknięcia w polu tekstowym. Dodaj obsługę zdarzeń klawiatury, tak aby po wciśnięciu klawisza "Enter" tekst w polu tekstowym został wyczyszczony.


import javax.swing.*;

public class MainWindow {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new KeyListenerWindow());
    }
}