package de.thro.inf.prg3.a08.filtering;

public class MealsFilterFactory {

	MealsFilter getStrategy(String key){

		switch(key){
			case "All":
				return new AllMealsStrategy();
			case "Vegetarian":
				return new VegetarianStrategy();
			case "No pork":
				return new NoPorkStrategy();
			case "No soy":
				return new NoSoyStrategy();
		}
		return null;

	}

}
