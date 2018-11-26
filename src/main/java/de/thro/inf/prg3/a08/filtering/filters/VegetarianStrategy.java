package de.thro.inf.prg3.a08.filtering.filters;

import de.thro.inf.prg3.a08.filtering.FilterBase;
import de.thro.inf.prg3.a08.model.Meal;

import java.util.Arrays;

/**
 * @author Peter Kurfer
 */
public class VegetarianStrategy extends FilterBase {

	private static final String[] meatTerms = new String[]{"fleisch", "schwein", "rind"};

	@Override
	protected boolean include(Meal m) {
		return m.getNotes()
			.stream()
			.map(String::toLowerCase)
			.noneMatch(note -> Arrays.stream(meatTerms).anyMatch(note::contains));
	}
}
