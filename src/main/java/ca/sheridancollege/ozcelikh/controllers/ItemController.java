package ca.sheridancollege.ozcelikh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.ozcelikh.beans.Item;
import ca.sheridancollege.ozcelikh.database.DatabaseAccess;

/**
 * JAVA-3 Midterm Exam
 * 
 * @author Hizir Ozcelik, October 2021
 */

@Controller
public class ItemController {

	@Autowired
	private DatabaseAccess da;

	@GetMapping("/")
	public String index(Model model) {

		model.addAttribute("itemList", da.getItemList());
		model.addAttribute("item", new Item());

		return "index";
	}

	@PostMapping("/insertItem")
	public String insertItem(Model model, @ModelAttribute Item item) {

		da.insertItem(item);

		model.addAttribute("itemList", da.getItemList());
		model.addAttribute("item", new Item());

		return "index";
	}

	@GetMapping("/shoppingList")
	public String getShoppingList(Model model) {
	
// Item adds to the shoppingList either quantity is zero or customer wants to buy more
		model.addAttribute("shoppingList", da.getShoppingList());

		return "shoppingList";
	}

	@GetMapping("/deleteItemById/{id}")
	public String deleteItemById(Model model, @PathVariable Long id) {

		da.deleteItemById(id);

		model.addAttribute("itemList", da.getItemList());
		model.addAttribute("item", new Item());

		return "index";
	}

	@GetMapping("/editItemById/{id}")
	public String editItemById(Model model, @PathVariable Long id) {

		Item item = da.getItemListById(id).get(0);
		model.addAttribute("item", item);

		da.deleteItemById(id);
		model.addAttribute("itemList", da.getItemList());

		return "index";
	}

}
