package com.cydeo.pizzacloud.controller;

import com.cydeo.pizzacloud.bootstrap.DataGenerator;
import com.cydeo.pizzacloud.model.Pizza;
import com.cydeo.pizzacloud.repository.PizzaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/design")
public class DesignPizzaController {
    private final PizzaRepository pizzaRepository;

   public DesignPizzaController(PizzaRepository pizzaRepository){
       this.pizzaRepository=pizzaRepository;
   }


    @GetMapping
    public String showDesignForm(Model model) {
        model.addAttribute("pizza", new Pizza());
        model.addAttribute("cheeses", DataGenerator.cheeseTypeList);
        model.addAttribute("sauces", DataGenerator.sauceTypeList);
        model.addAttribute("toppings", DataGenerator.toppingTypeList);

        //model.addAttribute("pizza", new Pizza()); // Add an empty Pizza object to the model
        return "design";

    }


    @PostMapping("/createPizza")
    public String processPizza( Pizza pizza) {
        pizzaRepository.createPizza(pizza);
        pizza.setId(UUID.randomUUID());
        return "redirect:/orders/current?pizzaId=" + pizza.getId();
    }


}
