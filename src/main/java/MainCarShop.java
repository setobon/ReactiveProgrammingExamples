import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MainCarShop {
    public static void main(String[] args) {
        PersonShop personOne = new PersonShop("Sergio");
        PersonShop personTwo = new PersonShop("Sara");

        Product productOne = new Product("Rice", 2000);
        Product productTwo = new Product("meat", 8000);
        Product productThree = new Product("yogurt", 2500);
        Product productFour = new Product("apple", 2000);
        Product productFive = new Product("orange", 500);

        personOne.addProduct(productOne);
        personOne.addProduct(productTwo);

        personTwo.addProduct(productThree);
        personTwo.addProduct(productFour);
        personTwo.addProduct(productFive);

        List<PersonShop> people = new ArrayList<>();
        people.add(personOne);
        people.add(personTwo);


        long timeBefore = System.currentTimeMillis();
        people.stream()
                .map(PersonShop::getProducts)
                .flatMap(Collection::stream)
                .filter(person -> person.getPrice() > 500)
               // .parallel()
                .forEach(products -> System.out.println(products.getName() + ": " + products.getPrice()));

        long timeAfter = System.currentTimeMillis();
        System.out.println("\nwithout Parallel time taken: " + (timeAfter-timeBefore) + "\n");

        timeBefore = System.currentTimeMillis();
        people.stream()
                .map(PersonShop::getProducts)
                .flatMap(Collection::stream)
                .filter(person -> person.getPrice() > 500)
                // .parallel()
                .forEach(products -> System.out.println(products.getName() + ": " + products.getPrice()));

        timeAfter = System.currentTimeMillis();
        System.out.println("\nParallel time taken: " + (timeAfter-timeBefore));
    }
}
