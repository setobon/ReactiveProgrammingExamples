import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
public class StreamExamples {

    Stream<String> employees = employeeList().stream();
    Stream<List<String>> getEmployeesTwo = Stream.of(employeeList());

    public void iteratorStream(){
        Stream.iterate(4, number -> number*4)
                .limit(10)
                .forEach(System.out::println);

    }

    public void generateStream(){
        Stream
                .generate(Math::random)
                .limit(10)
                .forEach(System.out::println);
    }


    public void intStreamSpecializationReduce(){
        /*showValues(IntStream
                .of(2,2,4,6)
                .distinct()
                .reduce(0, (a, b)->a+b));*/
        showValues(IntStream
                //.of(2,2,4,6).allMatch((number)->number%2==0));
                //.of(2,2,4,6).anyMatch((number)->number%2==0));
                .of(2,2,4,6).noneMatch((number)->number%2==0));
    }

    public void intStreamSpecializationReduceSum(){

        showValues(IntStream
                .of(2,2,4,6)
                .distinct()
                .reduce(0, Integer::sum));
    }

    public void intStreamSpecializationDistinctAndCount(){
        long resultDistinctCount = IntStream
                .of(2,2,3,4,4,5,6,6,6,4,6,8)
                .distinct()
                .count();

        showValues(Integer.valueOf((int) resultDistinctCount));
    }

    public void intStreamSpecializationSum(){
        int resultSum = IntStream
                .range(0, 15)
                .sum();

        showValues(resultSum);

    }

    public void intStreamSpecialization(){
        int valueMin = IntStream.of(2,4,6,8)
        .min().getAsInt();

        showValues(valueMin);
    }

    public void limit(){
        employees.limit(3)
                .forEach(employee -> showEmployee(employee));
    }

    public void skip(){
        employees.skip(3)
                .forEach(employee -> showEmployee(employee));
    }

    public void filter(){
        employees.filter(employee -> employee.length() > 3)
                .forEach(employee -> showEmployee(employee));
    }

    public void flatmap(){
        List<Integer> listOne = Arrays.asList(1, 2, 3);
        List<Integer> listTwo = Arrays.asList(4, 5);
        List<Integer> listThree = Arrays.asList(6, 7, 8, 9);
        List<List<Integer>> listOfInts = Arrays.asList(listOne, listTwo, listThree);
        List<Integer> listOfIntsFlatMap = listOfInts.stream()
                .flatMap(list -> list.stream())
                .collect(Collectors.toList());

        System.out.println(listOfIntsFlatMap);
    }



    public void map(){
        employees.map(employee ->  convertToUpperCase(employee))
                .forEach(employee -> showEmployee(employee));

    }
    public void peek(){
        employees.filter(employee -> employee.length() > 3)
                .peek(employee -> showEmployee(employee));
    }

    public void forEach(){
        employees.forEach(employee->showEmployee(employee));
    }

    private void showValues(int input){
        System.out.println(input);
    }

    private void showValues(boolean input){
        System.out.println(input);
    }

    private void showEmployee(String employee) {
        System.out.println(employee);
    }

    private String convertToUpperCase(String employee){
        return employee.toUpperCase();
    }

    public List<String> employeeList(){

        List<String> alist = new ArrayList<String>();

        alist.add("Steve");
        alist.add("Tim");
        alist.add("Lucy");
        alist.add("Pat");
        alist.add("Angela");
        alist.add("Tom");

        return alist;
    }


}
