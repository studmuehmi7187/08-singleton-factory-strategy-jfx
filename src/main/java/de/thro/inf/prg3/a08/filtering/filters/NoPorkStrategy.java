package de.thro.inf.prg3.a08.filtering.filters;

import de.thro.inf.prg3.a08.filtering.FilterBase;
import de.thro.inf.prg3.a08.model.Meal;

import java.util.Arrays;

/**
 * @author Peter Kurfer
 */
public class NoPorkStrategy extends FilterBase {

	@Override
	protected boolean include(Meal m) {
		return m.getNotes()
			.stream()
			.map(String::toLowerCase)
			.noneMatch(note -> note.contains("schwein"));
	}

}
