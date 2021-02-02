import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class MainCompletableFutureExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //completeFutureExample();
        //supplyAsyncCompletableFutureExample();
        //thenApplyCompletableFutureExample();
        //thenAcceptCompletableFutureExample();
        thenRunCompletableFutureExample();
    }

    private static void thenRunCompletableFutureExample() throws InterruptedException, ExecutionException {

        /**
         * thenRun -> es de tipo consumer y not tiene  parametro y tampoco devuelve nada
         */

        CompletableFuture<Integer> completableFuture =
                CompletableFuture.supplyAsync(()->3*3);

        completableFuture.thenRun(()->System.out.println("Esto no hace nada"));

    }

    private static void thenAcceptCompletableFutureExample() throws InterruptedException, ExecutionException {

        /**
         * thenAccept -> es de tipo consumer y tiene un unico parametro pero no devuelve nada
         */

        CompletableFuture<Integer> completableFuture =
                CompletableFuture.supplyAsync(()->3*3);

        completableFuture.thenAccept((nine)->System.out.println("The number is " + nine/3));

    }

    private static void thenApplyCompletableFutureExample() throws InterruptedException, ExecutionException {

        /**
         * thenApply -> es de tipo callback generico; acepta una instancia de funcion, lo proces y devuelve un future,
         * que contiene el valor devuelto por la funcion
         */

        CompletableFuture<Integer> completableFuture =
                CompletableFuture.supplyAsync(()->3*3);

        CompletableFuture<String> completableFutureGreat =
                CompletableFuture.supplyAsync(()->"Hi");

        CompletableFuture<Integer> future = completableFuture.thenApply((nine)->nine/3);
        CompletableFuture<String> futureGreat = completableFutureGreat.thenApply((message)->message + " Sergio");

        System.out.println(future.get());
        System.out.println(futureGreat.get());
    }


    private static void supplyAsyncCompletableFutureExample() throws InterruptedException, ExecutionException {
        SquareFutureExample squareFutureExample = new SquareFutureExample();


        /**
         * supplyAsync -> interface que no recibe argumentos pero si recibe valores
         * runAsync -> No retorna nada
         */

        /**Future<Integer> completableFuture =
                //CompletableFuture.supplyAsync(()->3*3);*/
        /*Future<Void> completableFuture =*/
        CompletableFuture.runAsync(()->System.out.println("Este es runAsync"));

        /*Integer result = completableFuture.get();

        System.out.println("The result is : " + result);

        squareFutureExample.shutDown();*/
    }

    private static void completeFutureExample() throws InterruptedException, ExecutionException {
        SquareFutureExample squareFutureExample = new SquareFutureExample();

        Future<Integer> completableFuture =
                CompletableFuture.completedFuture(squareFutureExample.calculate(12));
        Integer result = completableFuture.get();

        System.out.println("The result is : " + result);

        squareFutureExample.shutDown();
    }
}
