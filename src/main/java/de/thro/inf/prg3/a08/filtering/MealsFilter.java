package de.thro.inf.prg3.a08.filtering;

import de.thro.inf.prg3.a08.model.Meal;

import java.util.List;

/**
 * Filter to display true subsets of the given meals
 * Criteria is part of the concrete implementations
 * @author Peter Kurfer
 */

public interface MealsFilter {
	List<Meal> filter(List<Meal> meals);
}
