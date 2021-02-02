import java.util.concurrent.*;

public class MainFutureExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        //FutureExampleMethod();
        FutureCallableExampleMethod();
    }

    private static void FutureCallableExampleMethod() throws InterruptedException, TimeoutException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Future<Integer> future = executor.submit(new SquareCallableExample(12));

        while (!future.isDone()) {
            System.out.println("Calculating...");
            Thread.sleep(400);
        }

        Integer result = future.get(100, TimeUnit.SECONDS);
        System.out.println("The result is " + result);

        executor.shutdown();

    }

    private static void FutureExampleMethod() throws InterruptedException, ExecutionException, TimeoutException {
        SquareFutureExample squareFutureExample = new SquareFutureExample();
        Future<Integer> future = squareFutureExample.calculateAsync(12);
        Future<Integer> future1 = squareFutureExample.calculateAsync(100);

        while (!(future.isDone() && future1.isDone())) {

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
