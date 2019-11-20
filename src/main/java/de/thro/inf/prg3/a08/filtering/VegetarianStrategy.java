package de.thro.inf.prg3.a08.filtering;

import de.thro.inf.prg3.a08.model.Meal;

import java.util.List;

public class VegetarianStrategy extends FilterBase {
	@Override
	public boolean include(Meal m) {
		for(String ingedient: m.getNotes()){
			if(ingedient.equals("fleish")){
				return false;
			}
		}
		return true;
	}
}
