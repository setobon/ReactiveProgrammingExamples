import java.util.concurrent.*;

public class NameFutureExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        SquareFutureExample squareFutureExample = new SquareFutureExample();
        Future<Integer> future = squareFutureExample.calculate(12);
        Future<Integer> future1 = squareFutureExample.calculate(100);

        while (!(future.isDone() && future1.isDone())){
            System.out.println("Calculating...");
            Thread.sleep(400);
        }

        Integer result = future.get(100, TimeUnit.SECONDS);
        Integer result1 = future1.get(100, TimeUnit.SECONDS);

        System.out.println("The result is " + result);
        System.out.println("The result1 is " + result1);

        future.cancel(true);

        squareFutureExample.shutDown();
    }
}
