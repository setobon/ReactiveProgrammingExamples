import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class MainAutomaticCashier {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        AutomaticCashierCallable automaticCashierCallable = new AutomaticCashierCallable();

        Future<String> completableFuture =
                CompletableFuture.completedFuture(automaticCashierCallable.withdrawal(100));

        String result = completableFuture.get();

        System.out.println("Response : " + result);

        automaticCashierCallable.shutDown();;
    }
}
