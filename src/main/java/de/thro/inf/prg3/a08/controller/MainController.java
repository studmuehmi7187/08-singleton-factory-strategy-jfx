package de.thro.inf.prg3.a08.controller;

import com.google.gson.Gson;
import de.thro.inf.prg3.a08.api.OpenMensaAPI;
import de.thro.inf.prg3.a08.filtering.MealsFilter;
import de.thro.inf.prg3.a08.filtering.MealsFilterFactory;
import de.thro.inf.prg3.a08.filtering.NoSoyStrategy;
import de.thro.inf.prg3.a08.model.Meal;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.InputStreamReader;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Controller for main.fxml
 *
 * @author Peter Kurfer
 */
public class MainController implements Initializable {

	/**
	 * Logger e.g. to debug multi-threading issues
	 */
	private static final Logger logger;

	/**
	 * DateFormat instance to generate required date format string for OpenMensa API
	 */
	private static final DateFormat openMensaDateFormat;

	private final OpenMensaAPI api;
	private final ObservableList<Meal> meals;
	private final Gson gson;

	/**
	 * Binding of ChoiceBox UI element to filter for certain types of meals
	 */
	@FXML
	private ChoiceBox<String> filterChoiceBox;

	/**
	 * Binding of ListView UI element to display meals
	 */
	@FXML
	private ListView<Meal> mealsListView;

	/*
	  static initializer to initialize fields in class
	 */
	static {
		logger = LogManager.getLogger(MainController.class);
		openMensaDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
	}

	/**
	 * Default constructor
	 */
	public MainController() {
		meals = FXCollections.observableArrayList();
		gson = new Gson();

		/* initialize Retrofit instance */
		var retrofit = new Retrofit.Builder()
			.addConverterFactory(GsonConverterFactory.create(gson))
			.baseUrl("http://openmensa.org/api/v2/")
			.build();

		/* create OpenMensaAPI instance */
		api = retrofit.create(OpenMensaAPI.class);
	}

	/**
	 * Initialization method of the UI controller
	 * Called after the FXML fields are assigned
	 *
	 * @param location
	 * @param resources
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mealsListView.setItems(meals);
		filterChoiceBox.setItems(FXCollections.observableList(Arrays.asList(gson.fromJson(new InputStreamReader(getClass().getResourceAsStream("/filters.json")), String[].class))));
		doGetMeals();

		filterChoiceBox.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent actionEvent) {
				MealsFilter mf = new MealsFilterFactory(filterChoiceBox.getSelectionModel());
				mealsListView.setItems(mf.filter(meals));

			}
		});

	}

	/**
	 * Handles fetching of meals from OpenMensa API
	 */
	private void doGetMeals() {
		api.getMeals(openMensaDateFormat.format(new Date())).enqueue(new Callback<>() {
			@Override
			public void onResponse(Call<List<Meal>> call, Response<List<Meal>> response) {
				logger.debug("Got response");
				if (!response.isSuccessful() || response.body() == null) {
					logger.error(String.format("Got response with not successfull code %d", response.code()));
					Platform.runLater(() -> {
						var alert = new Alert(Alert.AlertType.ERROR);
						alert.setHeaderText("Unsuccessful HTTP call");
						alert.setContentText("Failed to get meals from OpenMensaAPI");
						alert.show();
					});
					return;
				}

				meals.clear();
				meals.addAll(response.body());
			}

			@Override
			public void onFailure(Call<List<Meal>> call, Throwable t) {
				logger.error("Failed to fetch meals");
				var alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText("Failed HTTP call");
				alert.setContentText("Failed to submit HTTP call to fetch meals.");
				alert.show();
			}
		});
	}
}
