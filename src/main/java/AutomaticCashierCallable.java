import java.util.concurrent.*;

public class AutomaticCashierCallable{

    private final ExecutorService executor = Executors.newFixedThreadPool(2);


    public String withdrawal(double amount){
        String message;

        if(amount<=0){
            message = "error";
        }else {
            message = "Success transaction => Withdrawal per $" + amount;
        }
        return message;
    }

    public void shutDown(){
        executor.shutdown();
    }
}
