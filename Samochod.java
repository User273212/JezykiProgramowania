import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//ZADANIE 10
public class Samochod {

    private final String marka;
    private final String model;
    private final int rokProdukcji;


    public static void main(String[] args){
        List<Samochod> cars = list();

        System.out.println("\nDomyslna kolejnosc:");
        printData(cars);

        System.out.println("\n---------------------\nSorotowanie wedlug marki samochodu:");
        cars.sort(new markSort());

        printData(cars);

        System.out.println("\n---------------------\nSorotowanie wedlug modelu samochodu:");
        cars.sort(new modelSort());

        printData(cars);

        System.out.println("\n---------------------\nSorotowanie wedlug najstarzego rocznika samochodu:");
        cars.sort(new rocznikSort());

        printData(cars);
    }

    //konstruktor
    public Samochod(String marka, String model, int rokProdukcji) {
        this.marka = marka;
        this.model = model;
        this.rokProdukcji = rokProdukcji;
    }

    private static List<Samochod> list(){
        //inicjalizacja listy
        List<Samochod> list = new ArrayList<>();

        list.add(new Samochod("Chevrolet", "Silverado", 2021));
        list.add(new Samochod("BMW", "X5", 2020));
        list.add(new Samochod("Lamborghini", "Aventador", 2023));
        list.add(new Samochod("Rolls-Royce", "Phantom", 2022));
        list.add(new Samochod("Bugatti", "Chiron", 2020));

        return list;
    }

    //drukowanie informacji o samochodach
    private static void printData(List<Samochod> cars) {
        for(Samochod car : cars)
            System.out.println("\nMarka: " + car.marka + "\nModel: " + car.model + "\nRok produkcji: " + car.rokProdukcji);
    }

    //sortowanie wedlug marki
     static class markSort implements Comparator<Samochod>{

       public int compare(Samochod a, Samochod b)
       {

           return a.marka.compareTo(b.marka);
       }

    }

    //sortowanie wedlug modelu
    static class modelSort implements Comparator<Samochod>{

        public int compare(Samochod a, Samochod b)
        {

            return a.model.compareTo(b.model);
        }

    }

    //sortowanie wedlug rocznika (malejaco)
    static class rocznikSort implements Comparator<Samochod>{

        public int compare(Samochod a, Samochod b)
        {

            return b.rokProdukcji - a.rokProdukcji;
        }

    }
}

