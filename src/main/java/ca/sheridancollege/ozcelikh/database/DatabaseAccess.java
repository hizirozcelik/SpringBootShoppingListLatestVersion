package ca.sheridancollege.ozcelikh.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.ozcelikh.beans.Item;

@Repository
public class DatabaseAccess {

	@Autowired
	protected NamedParameterJdbcTemplate jdbc;

	public void insertItem(Item item) {

		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "INSERT INTO item(name, description, quantity, isNeeded)"
				+ "VALUES(:name, :description, :quantity, :isNeeded)";

		namedParameters.addValue("name", item.getName());
		namedParameters.addValue("description", item.getDescription());
		namedParameters.addValue("quantity", item.getQuantity());
		namedParameters.addValue("isNeeded", item.getIsNeeded());

		jdbc.update(query, namedParameters);

	}

	public List<Item> getItemList() {

		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM item";
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Item>(Item.class));

	}

	public void deleteItemById(Long id) {

		MapSqlParameterSource namedParameters = new MapSqlParameterSource();

		String query = "DELETE FROM item WHERE id = :id";

		namedParameters.addValue("id", id);

		jdbc.update(query, namedParameters);

	}

	public List<Item> getItemListById(Long id) {

		MapSqlParameterSource namedParameters = new MapSqlParameterSource();

		String query = "SELECT * FROM item WHERE id = :id";

		namedParameters.addValue("id", id);

		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Item>(Item.class));

	}

	public List<Item> getShoppingList() {

		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
//Retrieve the list of items either quantity is zero or customer wants to buy more
		String query = "SELECT * FROM item WHERE (isNeeded = 1 OR quantity = 0)"; 

		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Item>(Item.class));

	}

}
