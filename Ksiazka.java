import java.util.HashSet;
import java.util.Set;

//ZADANIE 7
public class Ksiazka {

    private final String tytul;
    private final String autor;
    private final int numerISBN;

    // konstruktor klasy
    public Ksiazka(String tytul, String autor, int numerISBN) {
        this.tytul = tytul;
        this.autor = autor;
        this.numerISBN = numerISBN;

        printData();
    }

    public static void main(String[] args) {
        addElements();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ksiazka ksiazka = (Ksiazka) o;

        return numerISBN == ksiazka.numerISBN;
    }

    @Override
    public int hashCode() {
        return numerISBN;
    }

    private static void addElements() {

        // inicjalizacja hashSet
        Set<Ksiazka> hashSet = new HashSet<>();

        // utworzenie obiektu klasy i przypisanie mu wartosci
        Ksiazka ksiazka1 = new Ksiazka("Prorok", "Gibran Khalil", 978836);
        Ksiazka ksiazka2 = new Ksiazka("Sto lat samotności", "Gabriel García Márquez Khalil", 536206);
        Ksiazka ksiazka3 = new Ksiazka("Stary człowiek i morze", "Ernest Hemingway", 152683);
        Ksiazka ksiazka4 = new Ksiazka("Wielki Gatsby", "F.S. Fitzgerald", 592417);
        Ksiazka ksiazka5 = new Ksiazka("Duma i Uprzedzenie", "Jane Austen", 602732);
        Ksiazka ksiazka6 = new Ksiazka("Duma i Uprzedzenie", "Jane Austen", 602732);

        // dodanie obiektow do hashSet
        hashSet.add(ksiazka1);
        hashSet.add(ksiazka2);
        hashSet.add(ksiazka3);
        hashSet.add(ksiazka4);
        hashSet.add(ksiazka5);
        hashSet.add(ksiazka6);

        System.out.println("\nMetoda equals (porównanie ksiazka6 z ksiazka5): " + ksiazka6.equals(ksiazka5));
        System.out.println("\nMetoda equals (porównanie ksiazka1 z ksiazka5): " + ksiazka1.equals(ksiazka5));
        System.out.println("\nMetoda hashCode (ksiazka6): " + ksiazka6.hashCode());
        System.out.println("Numer ISBN (ksiazka6): " + ksiazka6.numerISBN + "\n");
        System.out.println("Numer ISBN (ksiazka1): " + ksiazka1.numerISBN + "\n");
    }

    private void printData() {
        // drukowanie informacji o danej ksiazce
        System.out.println("\nTytul: " + tytul + "\nAutor: " + autor + "\nNumer ISBN: " + numerISBN);
    }
}
