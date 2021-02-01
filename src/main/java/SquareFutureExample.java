import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SquareFutureExample {
    private final ExecutorService executor = Executors.newFixedThreadPool(2);

    /*public SquareFutureExample(ExecutorService executor){
        this.executor = executor;
    }*/

    public Future<Integer> calculate(Integer input){
        return executor.submit(()->{
            Thread.sleep(1000);
            return input*input;
        });
    }

    public void shutDown(){
        executor.shutdown();
    }
}
