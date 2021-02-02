import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class MainAutomaticCashier {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        AutomaticCashierCallable automaticCashierCallable = new AutomaticCashierCallable();

        Future<String> completableFuture =
                CompletableFuture.supplyAsync(()->{
                    String message = automaticCashierCallable.withdrawal(0);
                    if(message.equals("error")){
                        throw new RuntimeException(message.concat(" invalid amount"));
                    }
                    return message;
                }).exceptionally(ex -> {
                    System.out.println("failed: " + ex);
                    return "failed";
                });

        String result = completableFuture.get();

        System.out.println("Response : " + result);

        automaticCashierCallable.shutDown();;
    }
}
