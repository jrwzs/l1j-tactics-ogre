package l1j.server.server.templates;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import l1j.server.server.utils.L1QueryUtil;
import l1j.server.server.utils.L1QueryUtil.EntityFactory;
import l1j.server.server.utils.collections.Lists;

public class L1CookingRecipe {
	private static Logger _log = Logger.getLogger(L1CookingRecipe.class
			.getName());

	private final int _id;
	private final String _name;
	private final int _dishId;
	private final int _dishAmount;
	private final int _fantasyDishId;
	private final int _fantasyDishAmount;
	private final List<L1CookingIngredient> _ingredients;

	private L1CookingRecipe(int id, String name, int dishId, int dishAmount,
			int fantasyDishId, int fantasyDishAmount,
			List<L1CookingIngredient> ingredients) {
		super();
		_id = id;
		_name = name;
		_dishId = dishId;
		_dishAmount = dishAmount;
		_fantasyDishId = fantasyDishId;
		_fantasyDishAmount = fantasyDishAmount;
		_ingredients = ingredients;
	}

	public int getId() {
		return _id;
	}

	public String getName() {
		return _name;
	}

	public int getDishId() {
		return _dishId;
	}

	public int getDishAmount() {
		return _dishAmount;
	}

	public int getFantasyDishId() {
		return _fantasyDishId;
	}

	public int getFantasyDishAmount() {
		return _fantasyDishAmount;
	}

	public List<L1CookingIngredient> getIngredients() {
		return Lists.newArrayList(_ingredients);
	}

	private static class Factory implements EntityFactory<L1CookingRecipe> {
		@Override
		public L1CookingRecipe fromResultSet(ResultSet rs) throws SQLException {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			int dishId = rs.getInt("dish_id");
			int dishAmount = rs.getInt("dish_amount");
			int fantasyDishId = rs.getInt("fantasy_dish_id");
			int fantasyDishAmount = rs.getInt("fantasy_dish_amount");
			List<L1CookingIngredient> ingredients = L1CookingIngredient
					.findByCookingRecipeId(id);
			return new L1CookingRecipe(id, name, dishId, dishAmount,
					fantasyDishId, fantasyDishAmount, ingredients);
		}
	}

	public static List<L1CookingRecipe> all() {
		return L1QueryUtil.selectAll(new Factory(),
				"SELECT * FROM cooking_recipes");
	}
}
