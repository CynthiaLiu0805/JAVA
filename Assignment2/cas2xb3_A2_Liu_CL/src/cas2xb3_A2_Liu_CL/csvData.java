package cas2xb3_A2_Liu_CL;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class csvData {
	String USCities = "USCities.csv";
	String wendys = "wendys.csv";
	String burgerking = "burgerking.csv";
	String mcdonalds = "mcdonalds.csv";
	String menu = "menu.csv";
	String line = "";
	ArrayList<String> cityName = new ArrayList<>();

	// can change this to one key multi values
	ArrayList<Double> cityLat = new ArrayList<>();
	ArrayList<Double> cityLong = new ArrayList<>();

	ArrayList<Double> wendysLat = new ArrayList<>();
	ArrayList<Double> wendysLong = new ArrayList<>();

	ArrayList<Double> burgerkingLat = new ArrayList<>();
	ArrayList<Double> burgerkingLong = new ArrayList<>();

	ArrayList<Double> mcdonaldsLat = new ArrayList<>();
	ArrayList<Double> mcdonaldsLong = new ArrayList<>();

	HashMap<String, Double> mcdonaldsMenu = new HashMap<String, Double>();
	HashMap<String, Double> wendysMenu = new HashMap<String, Double>();
	HashMap<String, Double> burgerkingMenu = new HashMap<String, Double>();

	HashMap<String, Integer> cities = new HashMap<String, Integer>();

	HashMap<String, Double> availableMenu = new HashMap<String, Double>();

	// city and corresponding meal
	HashMap<String, String> meals = new HashMap<String, String>();

	//city from and city to
	HashMap<String, String> citiesBeenTo = new HashMap<String, String>();


	//get the data from csv file
	public csvData() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(USCities));
			line = br.readLine();
			while ((line = br.readLine()) != null) {
				String[] lines = line.split(",");
				cityName.add(lines[3]);
				cityLat.add(Double.parseDouble(lines[4]));
				cityLong.add(Double.parseDouble(lines[5]));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			BufferedReader br = new BufferedReader(new FileReader(wendys));
			line = br.readLine();
			while ((line = br.readLine()) != null) {
				String[] lines = line.split(",");
				wendysLat.add(Double.parseDouble(lines[1]));
				wendysLong.add(Double.parseDouble(lines[0]));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			BufferedReader br = new BufferedReader(new FileReader(burgerking));
			line = br.readLine();
			while ((line = br.readLine()) != null) {
				String[] lines = line.split(",");
				burgerkingLat.add(Double.parseDouble(lines[1]));
				burgerkingLong.add(Double.parseDouble(lines[0]));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			BufferedReader br = new BufferedReader(new FileReader(mcdonalds));
			line = br.readLine();
			while ((line = br.readLine()) != null) {
				String[] lines = line.split(",");
				mcdonaldsLat.add(Double.parseDouble(lines[1]));
				mcdonaldsLong.add(Double.parseDouble(lines[0]));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			BufferedReader br = new BufferedReader(new FileReader(menu));
			line = br.readLine();
			while ((line = br.readLine()) != null) {
				String[] lines = line.split(",");
				if (lines[0].equals("McDonald’s")) {
					mcdonaldsMenu.put(lines[1], Double.parseDouble(lines[2].substring(1)));
				}
				if (lines[0].equals("Burger King")) {
					burgerkingMenu.put(lines[1], Double.parseDouble(lines[2].substring(1)));
				}
				if (lines[0].equals("Wendy’s")) {
					wendysMenu.put(lines[1], Double.parseDouble(lines[2].substring(1)));

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// get the arraylist of the restaurants in a city
	public ArrayList<String> getRestaurants(String city) {
		ArrayList<String> restaurants = new ArrayList<>();
		int indexC = cityName.indexOf(city.toUpperCase());
		double latitude = cityLat.get(indexC);
		double longitude = cityLong.get(indexC);
		for (int i = 0; i < wendysLat.size(); i++) {
			if (Math.abs(wendysLat.get(i) - latitude) < 0.5 && Math.abs(wendysLong.get(i) - longitude) < 0.5) {
				restaurants.add("wendys");
				break;
			}
		}
		for (int i = 0; i < mcdonaldsLat.size(); i++) {
			if (Math.abs(mcdonaldsLat.get(i) - latitude) < 0.5 && Math.abs(mcdonaldsLong.get(i) - longitude) < 0.5) {
				restaurants.add("mcdonalds");
				break;
			}
		}
		for (int i = 0; i < burgerkingLat.size(); i++) {
			if (Math.abs(burgerkingLat.get(i) - latitude) < 0.5 && Math.abs(burgerkingLong.get(i) - longitude) < 0.5) {
				restaurants.add("burgerking");
				break;
			}
		}
		return restaurants;
	}

	// return the available menu of an arraylist of restaurant
	public HashMap<String, Double> getAvailableMenu(ArrayList<String> restaurants) {
		for (String restaurant : restaurants) {
			if (restaurant.equals("burgerking")) {
				availableMenu.putAll(burgerkingMenu);
			}
			if (restaurant.equals("mcdonalds")) {
				availableMenu.putAll(mcdonaldsMenu);
			}
			if (restaurant.equals("wendys")) {
				availableMenu.putAll(wendysMenu);
			}
		}
		return availableMenu;
	}

	// find which to eat for the next meal and add to the meal hashmap
	public void findMeal(HashMap<String, Double> menuHashmap, HashMap<String, String> mealList, String cityfrom,
			String city) {
		ArrayList<Double> values = new ArrayList<>();
		for (String key : menuHashmap.keySet()) {
			values.add(menuHashmap.get(key));
		}
		Collections.sort(values);
		double min = values.get(0);

		// when the meallist is empty, the first price of meal is the cheapest one
		if (mealList.isEmpty()) {
			double price = min;
			mealList.put(city, getKey(availableMenu, price));

		}
		// for the cities after boston
		if (mealList.get(cityfrom) != null) {
			String lastMeal = mealList.get(cityfrom);
			double lastValue = availableMenu.get(lastMeal);
			Collections.sort(values);
			min = values.get(0);
			// mark down that has been to the cityfrom
			citiesBeenTo.put(cityfrom, city);
			// if the price of last meal is the same as the current minimal one
			if (lastValue == min) {
				// price for this meal is the second cheapest
				double thisValue = values.get(values.indexOf(min) + 1);
				// add this city and meal to the meal list
				mealList.put(city, getKey(availableMenu, thisValue));
			} else {
				// when the price is not the same, just take the min one
				mealList.put(city, getKey(availableMenu, min));
			}
		}
		// for the case that one city is connected to more than one city
		if (citiesBeenTo.containsKey(cityfrom)) {
			String mealToBeRemoved = mealList.get(cityfrom);
			double valueToBeRemoved = availableMenu.get(mealToBeRemoved);
			// remove the price of last meal for now and get the minimal
			values.remove(valueToBeRemoved);
			Collections.sort(values);
			min = values.get(0);
			mealList.put(city, getKey(availableMenu, min));
			// add the value back
			values.add(valueToBeRemoved);
			citiesBeenTo.put(cityfrom, city);
		}

	}

	// Given the city names, find the restaurants near that city first, and then get
	// the available menu with meal name being key and price being value
	// then use the find meal function to add the meal for current city to the meals
	// list, which record the meal for every city
	public Double getWeight(String cityfrom, String city) {
		ArrayList<String> restaurantList = getRestaurants(city);
		HashMap<String, Double> menu = getAvailableMenu(restaurantList);
		findMeal(menu, meals, cityfrom, city); // meals is an arraylist of all meals
		String mealName = meals.get(city);
		double result = menu.get(mealName);
		return result;
	}

	// assign each city a number, in the form eg. boston=0, NYC=1 etc
	// get the hashMap for each city, using <String, Integer>
	public HashMap<String, Integer> getHashMap() {
		int count = 0;
		for (String c : cityName) {
			if (!cities.containsKey(c)) {
				cities.put(c, count);
			}
			count++;
		}
		return cities;
	}

	// a function that get the key of a hashmap by value
	// to get the meal just find the min cost one for each restaurant and compare
	// and add the lowest to arraylist

	// when getting the next meal, check the last meal is not the same as this one,
	// if not add to the meal arraylist?
	public String getMeal(String city) {
		return meals.get(city);
	}

	public static <K, V> K getKey(HashMap<K, V> map, V value) {
		return map.keySet().stream().filter(key -> value.equals(map.get(key))).findFirst().get();
	}

}
