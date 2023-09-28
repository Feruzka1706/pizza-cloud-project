package com.cydeo.pizzacloud.controller;

import com.cydeo.pizzacloud.model.Pizza;
import com.cydeo.pizzacloud.model.PizzaOrder;
import com.cydeo.pizzacloud.repository.PizzaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.UUID;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final PizzaRepository pizzaRepository;

    public OrderController(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @GetMapping("/current")
    public String orderForm( @RequestParam(name = "pizzaId") String pizzaId, Model model) {

        PizzaOrder pizzaOrder = new PizzaOrder();

        // Fix the getPizza method below in line 49.
        pizzaOrder.setPizza(getPizza(UUID.fromString(pizzaId)));

        model.addAttribute("pizzaOrder", pizzaOrder);

        return "/orderForm";
    }


    @PostMapping
    public String processOrder(@RequestParam(value = "pizzaId") String pizzaId,
                               @ModelAttribute("pizzaOrder") PizzaOrder pizzaOrder, Model model) {
        // Set the pizza before saving it
        pizzaOrder.setPizza(getPizza(UUID.fromString(pizzaId)));

        // Save the order
        pizzaRepository.createPizza(pizzaOrder.getPizza());
        model.addAttribute("pizzaId", pizzaOrder);
        return "redirect:/home?pizzaId=" + pizzaId;
    }

    //TODO
    private Pizza getPizza(UUID pizzaId) {
        // Get the pizza from repository based on it's id
        return pizzaRepository.readAll().stream()
                .filter(pizza -> pizza.getId().equals(pizzaId))
                .findAny().orElseThrow(()-> new NoSuchElementException("Pizza not found with id: "+pizzaId));
    }

}
