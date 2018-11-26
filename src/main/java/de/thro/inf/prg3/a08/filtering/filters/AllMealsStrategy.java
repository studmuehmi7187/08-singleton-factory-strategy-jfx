package de.thro.inf.prg3.a08.filtering.filters;

import de.thro.inf.prg3.a08.filtering.MealsFilter;
import de.thro.inf.prg3.a08.model.Meal;

import java.util.List;

/**
 * @author Peter Kurfer
 */

public class AllMealsStrategy implements MealsFilter {
	@Override
	public List<Meal> filter(List<Meal> meals) {
		/* don't filter anything */
		return meals;
	}
}
