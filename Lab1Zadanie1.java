import java.util.*;


//ZADANIE 1
public class Lab1Zadanie1 {

    public static void main(String[] args) {
        Menu();
    }

    private static void Menu(){
        System.out.println("\nWitaj!\nProsze podac ciag liczb, aby zakonczyc program podaj dowolny znak niebedacy liczba\n");

        Scanner in = new Scanner(System.in);

        //inicjalizacja listy
        List<Integer> list = new ArrayList<>();

        //petla pobierajaca ciag liczb -> zostaje przerwana w przypadku podania znaku, ktory nie jest liczba
        while (in.hasNextInt())
            list.add(in.nextInt());


        Set<Integer> set = new HashSet<>(list);

        Set<Integer> treeSet = new TreeSet<>(list);

        System.out.println("Lista podanych elementow: " + list);
        System.out.println("Zbior set i treeSet po dodaniu elementow z listy - porownanie kolejnosci");
        System.out.println("\nZbior set: " + set + "\nZbior treeSet: " + treeSet);

    }
}