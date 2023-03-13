package order;

import java.util.ArrayList;

public class CreateOrderBody {

    private ArrayList<String> ingredients;



    public CreateOrderBody(){}
    public CreateOrderBody(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }
}
