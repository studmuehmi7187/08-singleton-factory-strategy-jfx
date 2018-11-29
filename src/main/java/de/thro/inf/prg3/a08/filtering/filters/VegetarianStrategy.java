package de.thro.inf.prg3.a08.filtering.filters;

import de.thro.inf.prg3.a08.filtering.FilterBase;
import de.thro.inf.prg3.a08.filtering.NotesFilterBase;
import de.thro.inf.prg3.a08.model.Meal;

import java.util.Arrays;

/**
 * @author Peter Kurfer
 */
public class VegetarianStrategy extends NotesFilterBase {

	public VegetarianStrategy() {
		super(new String[]{"fleisch", "schwein", "rind"});
	}
}
