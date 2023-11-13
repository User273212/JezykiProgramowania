import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DeleteProfile extends UserProfile {
    public DeleteProfile(String user) {
        super(user);

        try {
            //utworzenie obiektu pliku
            File file = new File("src/main/userList/profiles.txt");

            //utworzenie obiektu skanera do odczytu pliku
            Scanner scanner = new Scanner(file);

            //tworzenie nowego pliku tymczasowego
            File tempFile = new File("src/main/userList/tempProfiles.txt");
            FileWriter tempFileWriter = new FileWriter(tempFile);

            while (scanner.hasNextLine()) {
                //odczytanie kolejnej linii
                String profile = scanner.nextLine();

                //sprawdzenie czy linia zawiera użytkownika do usunięcia
                if (!profile.startsWith(user + ":")) {
                    //zapisanie linii (profilu) do pliku tymczasowego
                    tempFileWriter.write(profile + "\n");
                }
            }

            //zamknięcie skanera i pliku tymczasowego
            scanner.close();
            tempFileWriter.close();

            //usunięcie oryginalnego pliku
            if (file.delete()) {
                System.out.println("\nPlik " + file.getName() + " został usunięty\n");
            } else {
                System.out.println("\nNie udało się usunąć pliku " + file.getName() + "\n");
            }

            //zmiana nazwy pliku tymczasowego na oryginalny
            if (tempFile.renameTo(file)) {
                System.out.println("\nPlik tymczasowy został przemianowany na " + file.getName() + "\n");
            } else {
                System.out.println("\nNie udało się przemianować pliku tymczasowego\n");
            }

            dispose();

        } catch (IOException e) {
            //obsługa błędu w przypadku problemów z odczytem lub zapisem pliku
            System.out.println("\nWystąpił błąd podczas operacji na pliku\n");
            e.printStackTrace();
        }
    }
}
