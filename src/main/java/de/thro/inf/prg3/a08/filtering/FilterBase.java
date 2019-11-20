package de.thro.inf.prg3.a08.filtering;

import de.thro.inf.prg3.a08.model.Meal;

import java.util.ArrayList;
import java.util.List;

public abstract class FilterBase implements MealsFilter{

	public abstract boolean include(Meal m);

	@Override
	public List<Meal> filter(List<Meal> meals) {
		List<Meal> ret = new ArrayList<Meal>();
		for(Meal m: meals){
			if(include(m)){
				ret.add(m);
			}
		}
		return ret;
	}

}
