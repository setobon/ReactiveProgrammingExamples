import java.util.concurrent.Callable;

public class SquareCallableExample implements Callable<Integer>{

    private Integer input;

    public SquareCallableExample(Integer input) {
        this.input = input;
    }

    @Override
    public Integer call() throws Exception {
        return input*input;
    };
}

