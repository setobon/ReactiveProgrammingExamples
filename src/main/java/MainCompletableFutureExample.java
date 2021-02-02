import java.util.concurrent.*;

public class MainCompletableFutureExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //completeFutureExample();
        //supplyAsyncCompletableFutureExample();
        //thenApplyCompletableFutureExample();
        //thenAcceptCompletableFutureExample();
        //thenRunCompletableFutureExample();
        //thenComposeCompletableFutureExample();
        //thenCombineCompletableFutureExample();
        //executorServiceCompletableFutureExample();
        //handleCompletableFuture();
        exceptionallyCompletableFuture();
    }


    /**
     * handle, exceptionally.
     * */


    public static void exceptionallyCompletableFuture() throws ExecutionException, InterruptedException {
        String name = "Sergio";

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(()->{
            if(name==null){
                throw new RuntimeException("Error...");
            }
            return "Hi ".concat(name);
        });

        completableFuture.completeExceptionally(new RuntimeException("There is an error"));

        System.out.println(completableFuture.get());
    }

    public static void handleCompletableFuture() throws ExecutionException, InterruptedException {
        String name = "Sergio";

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(()->{
            if(name==null){
                throw new RuntimeException("Error...");
            }
            return "Hi ".concat(name);
        }).handle((success,error) -> success != null?success:"There is an error");

        System.out.println(completableFuture.get());
    }

    private static void executorServiceCompletableFutureExample() throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        CompletableFuture<Integer> completableFuture =
                CompletableFuture.supplyAsync(()->3*3);

        CompletableFuture<Integer> future = completableFuture.thenApplyAsync((nine)->nine/3);

        System.out.println(future.get());

        executor.shutdown();
    }

    private static void thenCombineCompletableFutureExample() throws InterruptedException, ExecutionException {

        /**
         * thenCompose -> permite encadenar varios métodos. Utiliza lo que justo pasó anteriormente,
         * como argumento, devuelve un future con el resultado directamente.
         */

        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        CompletableFuture<CompletableFuture<String>> completableText =
                completableFuture.thenApply((word)->firstName(word));

        CompletableFuture<String> completableFutureCompose = completableFuture
                .thenCompose((word) -> firstName(word));

        System.out.println(completableFutureCompose.get());

    }

    private static void thenComposeCompletableFutureExample() throws InterruptedException, ExecutionException {

        /**
         * thenCompose -> permite encadenar varios métodos. Utiliza lo que justo pasó anteriormente,
         * como argumento, devuelve un future con el resultado directamente.
         */

        CompletableFuture<String> completableFuture =
                CompletableFuture.supplyAsync(()->"Hola ");

        CompletableFuture<String> greetingFuture = completableFuture
                .thenCompose((word) -> firstName(word))
                .thenCompose((word)->lastName(word));

        System.out.println(greetingFuture.get());

    }

    public static CompletableFuture<String> firstName(String word){
        return CompletableFuture.supplyAsync(()->word.concat("Sergio"));
    }

    public static CompletableFuture<String> lastName(String word){
        return CompletableFuture.supplyAsync(()->word.concat(" Tobón"));
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
