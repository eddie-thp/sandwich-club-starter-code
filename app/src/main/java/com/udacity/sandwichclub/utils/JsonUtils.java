package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String TAG = JsonUtils.class.getSimpleName();

    private static final String OBJ_NAME = "name";
    private static final String ARR_NAME_ALSO_KNOWN_AS = "alsoKnownAs";
    private static final String ARR_INGREDIENTS = "ingredients";
    private static final String ATTR_NAME_MAIN_NAME = "mainName";
    private static final String ATTR_PLACE_OF_ORIGIN = "placeOfOrigin";
    private static final String ATTR_DESCRIPTION = "description";
    private static final String ATTR_IMAGE = "image";

    public static Sandwich parseSandwichJson(String json) {
        try {
            Sandwich sandwich = new Sandwich();

            // Parse sandwich JSON obj and populate its attributes
            JSONObject sandwichJsonObj = new JSONObject(json);
            sandwich.setDescription(sandwichJsonObj.getString(ATTR_DESCRIPTION));
            sandwich.setImage(sandwichJsonObj.getString(ATTR_IMAGE));
            sandwich.setPlaceOfOrigin(sandwichJsonObj.getString(ATTR_PLACE_OF_ORIGIN));

            // Parse and populate list of ingredients
            JSONArray ingredientsJsonArray = sandwichJsonObj.getJSONArray(ARR_INGREDIENTS);
            List<String> ingredientsList = new ArrayList<>();
            int ingredientsCount = ingredientsJsonArray.length();
            for (int i = 0; i < ingredientsCount; i++) {
                ingredientsList.add(ingredientsJsonArray.getString(i));
            }
            sandwich.setIngredients(ingredientsList);

            // Parse sandwich name JSON obj and populate its attributes
            JSONObject name = sandwichJsonObj.getJSONObject(OBJ_NAME);
            sandwich.setMainName(name.getString(ATTR_NAME_MAIN_NAME));

            // Parse and populate AKA list
            JSONArray akaJsonArray = name.getJSONArray(ARR_NAME_ALSO_KNOWN_AS);
            List<String> akaList = new ArrayList<>();
            int akaCount = akaJsonArray.length();
            for (int i = 0; i < akaCount; i++) {
                akaList.add(akaJsonArray.getString(i));
            }
            sandwich.setAlsoKnownAs(akaList);

            return sandwich;

        } catch (JSONException e) {
            Log.e(TAG, "Failed to parse sandwich JSON object.", e);
        }

        return null;
    }
}
