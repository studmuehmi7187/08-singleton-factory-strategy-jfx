package de.thro.inf.prg3.a08.filtering;

import de.thro.inf.prg3.a08.model.Meal;

import java.util.List;

public class NoSoyStrategy extends FilterBase {
	@Override
	public boolean include(Meal m) {
		for(String ingredient: m.getNotes()){
			if(ingredient.equals("Soy")){
				return false;
			}
		}
		return true;
	}
}
