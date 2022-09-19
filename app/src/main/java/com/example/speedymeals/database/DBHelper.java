package com.example.speedymeals.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.speedymeals.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {


    //Database Enum
    private static final String DB_NAME = "speedMealDB";

    private static final int DB_VERSION = 1;


    //Restaurant Enum
    private static final String RESTAURANT_TABLE_NAME = "restaurant";

    private static final String RESTAURANT_ID_COL = "id";

    private static final String RESTAURANT_NAME_COL = "name";

    private static final String RESTAURANT_AVATAR_COL = "avatar";


    //Food Enum
    private static final String FOOD_TABLE_NAME = "food";

    private static final String FOOD_ID_COL = "id";

    private static final String FOOD_NAME_COL = "name";

    private static final String FOOD_AVATAR_COL = "avatar";

    private static final String FOOD_RESTAURANT_ID_COL = "restaurantID";


    //User Enum
    private static final String USER_TABLE_NAME = "user";

    private static final String USER_ID_COL = "id";

    private static final String USER_USERNAME_COL = "username";

    private static final String USER_PASSWORD_COL = "password";


    //Order Enum
    //Order's table name need to be "order_table" since "order" would create conflict with db method
    private static final String ORDER_TABLE_NAME = "order_table";

    private static final String ORDER_ID_COL = "id";

    private static final String ORDER_USER_ID_COL = "userID";

    private static final String ORDER_RESTAURANT_NAME_COL = "restaurantName";

    private static final String ORDER_FOOD_NAME_ARRAY_COL = "foodNameArray";

    private static final String ORDER_FOOD_NUMBER_ARRAY_COL = "foodNumberArray";

    private static final String ORDER_FOOD_PRICE_ARRAY_COL = "foodPriceArray";

    private static final String ORDER_DATE_COL = "date";

    private static final String ORDER_TOTAL_COST_COL = "totalCost";


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryRestaurant =  "CREATE TABLE " + RESTAURANT_TABLE_NAME + " ("
                + RESTAURANT_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + RESTAURANT_NAME_COL + " TEXT,"
                + RESTAURANT_AVATAR_COL + " INTEGER)";

        String queryFood =  "CREATE TABLE " + FOOD_TABLE_NAME + " ("
                + FOOD_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FOOD_NAME_COL + " TEXT,"
                + FOOD_AVATAR_COL + " INTEGER,"
                + FOOD_RESTAURANT_ID_COL + " INTEGER)";

        String queryUser =  "CREATE TABLE " + USER_TABLE_NAME + " ("
                + USER_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USER_USERNAME_COL + " TEXT,"
                + USER_PASSWORD_COL + " TEXT)";

        String queryOrder = "CREATE TABLE " + ORDER_TABLE_NAME + " ("
                + ORDER_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ORDER_USER_ID_COL + " INTEGER,"
                + ORDER_RESTAURANT_NAME_COL + " TEXT,"
                + ORDER_FOOD_NAME_ARRAY_COL + " TEXT,"
                + ORDER_FOOD_NUMBER_ARRAY_COL + " TEXT,"
                + ORDER_FOOD_PRICE_ARRAY_COL + " TEXT,"
                + ORDER_DATE_COL + " TEXT,"
                + ORDER_TOTAL_COST_COL + " INTEGER)";

        db.execSQL(queryRestaurant);
        db.execSQL(queryFood);
        db.execSQL(queryUser);
        db.execSQL(queryOrder);
    }

    public List<Restaurant> readRestaurant(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorList = db.rawQuery("SELECT * FROM " + RESTAURANT_TABLE_NAME, null);
        ArrayList<Restaurant> restaurantsList = new ArrayList<>();
        if (cursorList.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                restaurantsList.add(new Restaurant(Integer.parseInt(cursorList.getString(0)),
                        cursorList.getString(1)));
            } while (cursorList.moveToNext());
        }
        // at last closing our cursor
        // and returning our array list.
        cursorList.close();
        //db.close();
        return restaurantsList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + RESTAURANT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ORDER_TABLE_NAME);
        onCreate(db);
    }
}
