import java.util.ArrayList;
import java.util.List;

public class PersonShop {

    private String name;
    private List<Product> products = new ArrayList<>();

    public PersonShop(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product){
        this.products.add(product);
    }
}
