package com.cydeo.pizzacloud.model;

import com.cydeo.pizzacloud.enums.Cheese;
import com.cydeo.pizzacloud.enums.Sauce;
import com.cydeo.pizzacloud.enums.Topping;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pizza {

    @NotNull
    private UUID id;
    private List<Cheese> cheeseList;
    private List<Sauce> sauceList;
    private List<Topping> toppingList;

}
