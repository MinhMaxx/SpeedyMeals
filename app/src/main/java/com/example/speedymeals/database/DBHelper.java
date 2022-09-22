package com.example.speedymeals.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.speedymeals.R;

public class DBHelper extends SQLiteOpenHelper {

    //Database Field
    private static final String DB_NAME = "speedMealDB";

    private static final int DB_VERSION = 1;

    //Restaurant Table Field
    private static final String RESTAURANT_TABLE_NAME = "restaurant";
    private static final String RESTAURANT_ID_COL = "id";
    private static final String RESTAURANT_NAME_COL = "name";
    private static final String RESTAURANT_AVATAR_COL = "avatar";


    //Food Table Field
    private static final String FOOD_TABLE_NAME = "food";
    private static final String FOOD_ID_COL = "id";
    private static final String FOOD_NAME_COL = "name";
    private static final String FOOD_DESCRIPTION_COL = "description";
    private static final String FOOD_PRICE_COL = "price";
    private static final String FOOD_AVATAR_COL = "avatar";
    private static final String FOOD_RESTAURANT_ID_COL = "restaurantID";


    //User Table Field
    private static final String USER_TABLE_NAME = "user";
    private static final String USER_ID_COL = "id";
    private static final String USER_USERNAME_COL = "username";
    private static final String USER_PASSWORD_COL = "password";
    private static final String USER_ADDRESS_COL = "address";


    //Order Table Field
    //Order's table name need to be "order_table" since "order" would create conflict with db method
    private static final String ORDER_TABLE_NAME = "order_table";
    private static final String ORDER_ID_COL = "id";
    private static final String ORDER_USER_ID_COL = "userID";
    private static final String ORDER_ADDRESS_COL = "address";
    private static final String ORDER_RESTAURANT_NAME_COL = "restaurantName";
    private static final String ORDER_FOOD_NAME_ARRAY_COL = "foodName";
    private static final String ORDER_FOOD_NUMBER_ARRAY_COL = "foodNumber";
    private static final String ORDER_FOOD_PRICE_ARRAY_COL = "foodPrice";
    private static final String ORDER_DATE_COL = "date";
    private static final String ORDER_TOTAL_COST_COL = "totalCost";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryRestaurant =  "CREATE TABLE " + RESTAURANT_TABLE_NAME + " ("
                + RESTAURANT_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + RESTAURANT_NAME_COL + " TEXT NOT NULL UNIQUE,"
                + RESTAURANT_AVATAR_COL + " INTEGER)";

        String queryFood =  "CREATE TABLE " + FOOD_TABLE_NAME + " ("
                + FOOD_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FOOD_NAME_COL + " TEXT,"
                + FOOD_DESCRIPTION_COL + " DOUBLE,"
                + FOOD_PRICE_COL + " DOUBLE,"
                + FOOD_AVATAR_COL + " INTEGER,"
                + FOOD_RESTAURANT_ID_COL + " INTEGER)";

        String queryUser =  "CREATE TABLE " + USER_TABLE_NAME + " ("
                + USER_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USER_USERNAME_COL + " TEXT NOT NULL UNIQUE,"
                + USER_PASSWORD_COL + " TEXT NOT NULL,"
                + USER_ADDRESS_COL + " TEXT)";

        String queryOrder = "CREATE TABLE " + ORDER_TABLE_NAME + " ("
                + ORDER_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ORDER_USER_ID_COL + " INTEGER,"
                + ORDER_ADDRESS_COL + " TEXT,"
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

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + RESTAURANT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ORDER_TABLE_NAME);
        onCreate(db);
    }
}
