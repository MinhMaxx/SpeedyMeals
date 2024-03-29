package com.example.speedymeals.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.speedymeals.model.Food;
import com.example.speedymeals.model.Order;
import com.example.speedymeals.model.Restaurant;
import com.example.speedymeals.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class DBManager {

    //making this class into singleton
    private static DBManager instance = null;
    private DBHelper dbHelper;
    private DBFiller dbFiller;

    private Context context;

    private SQLiteDatabase db;

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
    private static final String USER_NAME_COL = "name";
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

    private DBManager(Context context) {
        this.context = context;
    }

    //singleton methods
    public static DBManager getInstance(Context context)
    {
        if(instance == null)
            instance = new DBManager(context);
        return instance;
    }

    //database
    public DBManager open() throws SQLException {
        dbHelper = new DBHelper(context);
        dbFiller = new DBFiller(this);
        db = dbHelper.getWritableDatabase();
        checkEmptyRestaurant();
        checkEmptyFood();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void checkEmptyRestaurant(){
        Cursor cur = db.rawQuery("SELECT COUNT(*) FROM " + RESTAURANT_TABLE_NAME, null);
        if (cur != null) {
            cur.moveToFirst();                       // Always one row returned.
            if (cur.getInt (0) == 0) {               // Zero count means empty table.
                dbFiller.fillRestaurant();
            }
        }
        if (cur != null) {
            cur.close();
        }
    }

    public void checkEmptyFood(){
        Cursor cur = db.rawQuery("SELECT COUNT(*) FROM " + FOOD_TABLE_NAME, null);
        if (cur != null) {
            cur.moveToFirst();                       // Always one row returned.
            if (cur.getInt (0) == 0) {               // Zero count means empty table.
                dbFiller.fillFood();
            }
        }
        if (cur != null) {
            cur.close();
        }
    }

    public List<Restaurant> readRestaurant(){
        Cursor cursorList = db.rawQuery("SELECT * FROM " + RESTAURANT_TABLE_NAME, null);
        ArrayList<Restaurant> restaurantsList = new ArrayList<>();
        if (cursorList.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                Restaurant inRestaurant = new Restaurant(Integer.parseInt(cursorList.getString(0)),
                        cursorList.getString(1),
                        Integer.parseInt(cursorList.getString(2)));
                inRestaurant.loadMenus(readFoodToRestaurant(inRestaurant.getID()));
                restaurantsList.add(inRestaurant);
            } while (cursorList.moveToNext());
        }
        // at last closing our cursor
        // and returning our array list.
        cursorList.close();
        //db.close();
        return restaurantsList;
    }

    public void addRestaurant(String name, int avatarID){
        ContentValues values = new ContentValues();
        values.put(RESTAURANT_NAME_COL, name);
        values.put(RESTAURANT_AVATAR_COL, avatarID);

        db.insert(RESTAURANT_TABLE_NAME, null, values);
        //db.close();
    }

    public List<Food> readFood(){
        Cursor cursorList = db.rawQuery("SELECT * FROM " + FOOD_TABLE_NAME, null);
        ArrayList<Food> foodList = new ArrayList<>();
        if (cursorList.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                foodList.add(new Food(Integer.parseInt(cursorList.getString(0)),
                        cursorList.getString(1),
                        cursorList.getString(2),
                        Double.parseDouble(cursorList.getString(3)),
                        Integer.parseInt(cursorList.getString(4)),
                        Integer.parseInt(cursorList.getString(5))));
            } while (cursorList.moveToNext());
        }
        // at last closing our cursor
        // and returning our array list.
        cursorList.close();
        //db.close();
        return foodList;
    }

    public List<Food> readFoodToRestaurant(int restaurantID){
        Cursor cursorList = db.rawQuery("SELECT * FROM " + FOOD_TABLE_NAME + " WHERE " + FOOD_RESTAURANT_ID_COL + " = " + restaurantID, null);
        ArrayList<Food> foodList = new ArrayList<>();
        if (cursorList.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                foodList.add(new Food(Integer.parseInt(cursorList.getString(0)),
                        cursorList.getString(1),
                        cursorList.getString(2),
                        Double.parseDouble(cursorList.getString(3)),
                        Integer.parseInt(cursorList.getString(4)),
                        Integer.parseInt(cursorList.getString(5))));
            } while (cursorList.moveToNext());
        }
        // at last closing our cursor
        // and returning our array list.
        cursorList.close();
        //db.close();
        return foodList;
    }

    public void addFood(String name, String description, double price, int avatarID,int restaurantID){
        ContentValues values = new ContentValues();
        values.put(FOOD_NAME_COL, name);
        values.put(FOOD_DESCRIPTION_COL, description);
        values.put(FOOD_PRICE_COL, price);
        values.put(FOOD_AVATAR_COL, avatarID);
        values.put(FOOD_RESTAURANT_ID_COL, restaurantID);

        db.insert(FOOD_TABLE_NAME, null, values);
        //.db.close();
    }

    public List<Order> readOrderOfUser(int userID){
        Cursor cursorList = db.rawQuery("SELECT * FROM " + ORDER_TABLE_NAME + " WHERE " + ORDER_USER_ID_COL + " = " +userID, null);
        ArrayList<Order> orderList = new ArrayList<>();
        if (cursorList.moveToFirst()) {
            do {
                Order loadingOrder;
                // on below line we are adding the data from cursor to our array list.
                orderList.add(new Order(Integer.parseInt(cursorList.getString(0)),
                            Integer.parseInt(cursorList.getString(1)),
                            cursorList.getString(2),
                            convertStringToStringArray(cursorList.getString(3)),
                            convertStringToStringArray(cursorList.getString(4)),
                            convertStringToStringArray(cursorList.getString(5)),
                            convertStringToStringArray(cursorList.getString(6)),
                            cursorList.getString(7),
                            cursorList.getString(8)));
            } while (cursorList.moveToNext());
        }
        cursorList.close();
        //db.close();

        //Newest order will be shown first
        Collections.reverse(orderList);
        return orderList;
    }

    public void addOrder(int userID, String address, String[] restaurantName, String[] foodName,
                         String[] foodNumber, String[] foodPrice, String date, String totalCost){
        ContentValues values = new ContentValues();
        values.put(ORDER_USER_ID_COL, userID);
        values.put(ORDER_ADDRESS_COL, address);
        values.put(ORDER_RESTAURANT_NAME_COL, convertStringArrayToString(restaurantName));
        values.put(ORDER_FOOD_NAME_ARRAY_COL, convertStringArrayToString(foodName));
        values.put(ORDER_FOOD_NUMBER_ARRAY_COL, convertStringArrayToString(foodNumber));
        values.put(ORDER_FOOD_PRICE_ARRAY_COL, convertStringArrayToString(foodPrice));
        values.put(ORDER_DATE_COL, date);
        values.put(ORDER_TOTAL_COST_COL, totalCost);

        db.insert(ORDER_TABLE_NAME, null, values);
        //db.close();
    }

    //Might remove later due to security issues
//    public List<User> readUser(){
//        Cursor cursorList = db.rawQuery("SELECT * FROM " + USER_TABLE_NAME, null);
//        ArrayList<User> userList = new ArrayList<>();
//        if (cursorList.moveToFirst()) {
//            do {
//                // on below line we are adding the data from cursor to our array list.
//                userList.add(new User(Integer.parseInt(cursorList.getString(0)),
//                        cursorList.getString(1)));
//            } while (cursorList.moveToNext());
//        }
//        cursorList.close();
//        //db.close();
//        return userList;
//    }

    public Boolean addUser(String username, String password, String name, String address){
        Boolean isSuccessful = true;
        //Check to see if an user with the username have been registered
        Cursor cursorList = db.rawQuery("SELECT * FROM " + USER_TABLE_NAME + " WHERE " + USER_USERNAME_COL + " = '" +username+"'", null);
        if(cursorList.getCount()==0){
            ContentValues values = new ContentValues();
            values.put(USER_USERNAME_COL, username);
            values.put(USER_PASSWORD_COL, password);
            values.put(USER_NAME_COL, name);
            values.put(USER_ADDRESS_COL, address);

            db.insert(USER_TABLE_NAME, null, values);
        }
        else{
            isSuccessful = false;
        }
        //db.close();
        cursorList.close();
        return isSuccessful;
    }

    //Take in Username + Password
    //Check to see if User exit in DB, if Password match with Encrypted Password stored in DB
    public User checkUser(String username, String inPassword){
        //Check to see if an user with the username have been registered
        Cursor cursorList = db.rawQuery("SELECT * FROM " + USER_TABLE_NAME + " WHERE " + USER_USERNAME_COL + " = '" +username+"'", null);
        if(cursorList.getCount()!=0){
            String password = "";
            User newUser = null;
            if (cursorList.moveToFirst()) {
                do {
                    // on below line we are adding the data from cursor to our array list.
                    newUser = new User(Integer.parseInt(cursorList.getString(0)),
                            cursorList.getString(1),
                            cursorList.getString(3),
                            cursorList.getString(4));
                    password = cursorList.getString(2);
                } while (cursorList.moveToNext());
            }
            //db.close();
            cursorList.close();
            if(password.equals(inPassword))
            {
                return newUser;
            }
            else{
                return null;
            }
        }
        else{
            //db.close();
            cursorList.close();
            return null;
        }
    }

    private SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");

    private String strSeparator = "__,__";

    private String convertStringArrayToString(String[] array){
        String str = "";
        for (int i = 0;i<array.length; i++) {
            str = str+array[i];
            // Do not append comma at the end of last element
            if(i<array.length-1){
                str = str+strSeparator;
            }
        }
        return str;
    }

    private String[] convertStringToStringArray(String str){
        String[] arr = str.split(strSeparator);
        return arr;
    }



}
