package ca.sheridancollege.ozcelikh.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Item {

	private Long id;
	private String name;
	private String description;
	private int quantity;
	private Boolean isNeeded;

}
