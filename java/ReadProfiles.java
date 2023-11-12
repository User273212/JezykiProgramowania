import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public interface ReadProfiles {

    // Metoda do odczytu profili uzytkownikow z pliku
    default Map<String, String> readProfiles() {

        // Mapa przechowujaca pary nazwa uzytkownika - haslo
        Map<String, String> profiles = new TreeMap<>();

        try {
            // Utworzenie obiektu pliku
            File file = new File("src/main/userList/profiles.txt");

            // Utworzenie obiektu skanera do odczytu pliku
            Scanner scanner = new Scanner(file);

            // Petla odczytujaca kolejne linie z pliku
            while (scanner.hasNextLine()) {
                // Odczytanie kolejnej linii
                String data = scanner.nextLine();

                // Podzial linii na czesci, rozdzielone separatorem ":"
                String[] parts = data.split(":");

                // Sprawdzenie czy linia zawiera dwie czesci (nazwa uzytkownika i haslo)
                if (parts.length == 2) {
                    // Dodanie do mapy pary nazwa uzytkownika - haslo
                    profiles.put(parts[0], parts[1]);
                }
            }

            // Zamkniecie skanera po zakonczeniu odczytu pliku
            scanner.close();

        } catch (IOException e) {
            // Obsluga bledu w przypadku problemow z odczytem pliku
            System.out.println("\nWystapil blad podczas otwierania\n");
            e.printStackTrace();
        }

        // Zwrocenie mapy profili uzytkownikow
        return profiles;
    }
}