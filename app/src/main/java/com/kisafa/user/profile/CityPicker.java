package com.kisafa.user.profile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Currency;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;

import androidx.fragment.app.DialogFragment;

import com.livixa.client.R;

public class CityPicker extends DialogFragment implements
		Comparator<Country> {
	/**
	 * View components
	 */
	private EditText searchEditText;
	private ListView countryListView;

	/**
	 * Adapter for the listview
	 */
	private CityListAdapter adapter;

	/**
	 * Hold all countries, sorted by country name
	 */
	private List<String> allCitesList;

	/**
	 * Hold countries that matched user query
	 */
	private List<String> selectedCitiesList;

	/**
	 * Listener to which country user selected
	 */
	private CountryPickerListener listener;

	/**
	 * Set listener
	 * 
	 * @param listener
	 */
	public void setListener(CountryPickerListener listener) {
		this.listener = listener;
	}

	public EditText getSearchEditText() {
		return searchEditText;
	}

	public ListView getCountryListView() {
		return countryListView;
	}

	/**
	 * Convenient function to get currency code from country code currency code
	 * is in English locale
	 * 
	 * @param countryCode
	 * @return
	 */
	public static Currency getCurrencyCode(String countryCode) {
		try {
			return Currency.getInstance(new Locale("en", countryCode));
		} catch (Exception e) {

		}
		return null;
	}

	/**
	 * Get all countries with code and name from res/raw/countries.json
	 * 
	 * @return
	 */
	private List<String> getAllCountries() {
		if (allCitesList != null) {
			try {
				
				Collections.sort(allCitesList);
				
				selectedCitiesList= new ArrayList<String>();
				selectedCitiesList.addAll(allCitesList);

				return allCitesList;

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	public void setAllCities(List<String> cities) {
		if (allCitesList == null) {
			try {
				allCitesList =cities;

				

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	
	private static String readFileAsString(Context context)
			throws java.io.IOException {
		String base64 = context.getResources().getString(R.string.countries);
		byte[] data = Base64.decode(base64, Base64.DEFAULT);
		return new String(data, "UTF-8");
	}

	/**
	 * To support show as dialog
	 * 
	 * @param dialogTitle
	 * @return
	 */
	public static CityPicker newInstance(String dialogTitle) {
		CityPicker picker = new CityPicker();
		Bundle bundle = new Bundle();
		bundle.putString("dialogTitle", dialogTitle);
		picker.setArguments(bundle);
		return picker;
	}

	/**
	 * Create view
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate view
		View view = inflater.inflate(R.layout.country_picker, null);

		// Get countries from the json
		getAllCountries();

		// Set dialog title if show as dialog
		Bundle args = getArguments();
		if (args != null) {
			String dialogTitle = args.getString("dialogTitle");
			getDialog().setTitle(dialogTitle);
			
			int width = getResources().getDimensionPixelSize(
					R.dimen.cp_dialog_width);
			int height = getResources().getDimensionPixelSize(
					R.dimen.cp_dialog_height);
			getDialog().getWindow().setLayout(width, height);
			
			
		}
		
		getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

		// Get view components
		searchEditText = (EditText) view
				.findViewById(R.id.country_picker_search);
		countryListView = (ListView) view
				.findViewById(R.id.country_picker_listview);

		// Set adapter
		adapter = new CityListAdapter(getActivity(), selectedCitiesList);
		countryListView.setAdapter(adapter);

		// Inform listener
		countryListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (listener != null) {
					String city = selectedCitiesList.get(position);
					listener.onSelectCountry(city,
							"");
				}
			}
		});

		// Search for which countries matched user query
		searchEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				search(s.toString());
			}
		});

		return view;
	}

	/**
	 * Search allCountriesList contains text and put result into
	 * selectedCountriesList
	 * 
	 * @param text
	 */
	@SuppressLint("DefaultLocale")
	private void search(String text) {
		selectedCitiesList.clear();

		for (String city : allCitesList) {
			if (city.toLowerCase(Locale.ENGLISH)
					.contains(text.toLowerCase())) {
				selectedCitiesList.add(city);
			}
		}

		adapter.notifyDataSetChanged();
	}

	/**
	 * Support sorting the countries list
	 */
	@Override
	public int compare(Country lhs, Country rhs) {
		return lhs.getName().compareTo(rhs.getName());
	}

}