package de.thro.inf.prg3.a08.filtering.filters;

import de.thro.inf.prg3.a08.filtering.FilterBase;
import de.thro.inf.prg3.a08.filtering.NotesFilterBase;
import de.thro.inf.prg3.a08.model.Meal;

/**
 * @author Peter Kurfer
 */
public class NoSoyStrategy extends NotesFilterBase {

	public NoSoyStrategy() {
		super(new String[]{"soja"});
	}
}
