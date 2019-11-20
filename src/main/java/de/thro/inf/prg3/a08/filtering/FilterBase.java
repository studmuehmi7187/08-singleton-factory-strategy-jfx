package de.thro.inf.prg3.a08.filtering;

import de.thro.inf.prg3.a08.model.Meal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public abstract class FilterBase implements MealsFilter{

	public abstract boolean include(Meal m);

	@Override
	public ObservableList<Meal> filter(List<Meal> meals) {
		ObservableList<Meal> ret = FXCollections.observableArrayList();
		for(Meal m: meals){
			if(include(m)){
				ret.add(m);
			}
		}
		return ret;
	}

}
