import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MainTravels {
    public static void main(String[] args) {

        Person personOne = new Person("Sergio");
        Person personTwo = new Person("Sara");

        Travels travelOne = new Travels("France");
        Travels travelTwo = new Travels("Dublin");
        Travels travelThree = new Travels("England");
        Travels travelFour = new Travels("Andorra");
        Travels travelFive = new Travels("Prague");

        personOne.addTravel(travelOne);
        personOne.addTravel(travelTwo);

        personTwo.addTravel(travelThree);
        personTwo.addTravel(travelFour);
        personTwo.addTravel(travelFive);

        List<Person> people = new ArrayList<>();
        people.add(personOne);
        people.add(personTwo);

        people.stream().map(Person::getTravelsList)
                .flatMap(Collection::stream)
                .collect(Collectors.toList())
                .forEach(travels -> System.out.println(travels.getPais()));

          /*for(Person person:people){
            for(int travel = 0; travel < person.getTravelsList().size(); travel++){
                countries += person.getTravelsList().get(travel).getPais() + ", ";
            }
            for(Travels travel: person.getTravelsList()){
                System.out.println(travel.getPais());
            }
        }*/
    }
}
