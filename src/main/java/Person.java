import java.util.ArrayList;
import java.util.List;

public class Person {

    private String name;
    private List<Travels> travelsList = new ArrayList<>();

    public Person(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Travels> getTravelsList() {
        return travelsList;
    }

    public void setTravelsList(List<Travels> travelsList) {
        this.travelsList = travelsList;
    }

    public void addTravel(Travels travel){
        this.travelsList.add(travel);
    }
}