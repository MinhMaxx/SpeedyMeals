package com.example.speedymeals.database;

import android.database.sqlite.SQLiteDatabase;

import com.example.speedymeals.R;

public class DBFiller {
    private DBManager dbManager;

    public DBFiller(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public void fillRestaurant() {
        dbManager.addRestaurant("Phoever", R.drawable.phoever);
        dbManager.addRestaurant("Arirang", R.drawable.arirang);
    }

    public void fillFood() {
        dbManager.addFood("Banh My","Vienamese breadroll with grilled pork",
                10.0,R.drawable.banh_my,1);
        dbManager.addFood("Banh Xeo","Vienamese crispy tumeric rice pancake",
                17.5,R.drawable.banh_xeo,1);
        dbManager.addFood("Com Tam","Vienamese broken rice with grilled pork chop",
                18.0,R.drawable.com_tam,1);
        dbManager.addFood("Fresh Roll","Summer rolls with prawn and peanut dipping sauce",
                9.0,R.drawable.fresh_roll,1);
        dbManager.addFood("Fried Rice","Special Fried Rice",
                15.0,R.drawable.fried_rice,1);
        dbManager.addFood("Pho Bo","Vienamese Pho with beef slice and beef broth",
                15.5,R.drawable.pho_bo,1);
        dbManager.addFood("Pho Ga","Vienamese Pho with chicken and chicken broth",
                14.5,R.drawable.pho_ga,1);
        dbManager.addFood("Shaking Beef","Cube stir fired beef with veggies",
                21.0,R.drawable.shaking_beef,1);
        dbManager.addFood("Spring Roll","Meat deep fried sping rolls",
                10.0,R.drawable.spring_roll,1);
        dbManager.addFood("Beef Stir Fried Noodle","Stir fried noodle with beef and veggies",
                14.0,R.drawable.stir_fried_noodle,1);
        dbManager.addFood("Rice","One serving of rice",
                3.0,R.drawable.rice,1);

        dbManager.addFood("Beef Bulgogi","Stir fried beef with Bulgogi sauce",
                19.0,R.drawable.beef_bulgogi,2);
        dbManager.addFood("Bibimbap","Mixxed rice, egg, beef, veggies server in a hot stone bowl",
                18.5,R.drawable.bibimbap,2);
        dbManager.addFood("Bossam Pork Belly","Bossam style - Boiled Pork Belly",
                15.0,R.drawable.bossam_pork_belly,2);
        dbManager.addFood("Dak Bulgogi","Stir fried chicken with Bulgogi sauce",
                18.0,R.drawable.dak_bulgogi,2);
        dbManager.addFood("Gabli Jjim","Spicy beef rib soup",
                15.0,R.drawable.gabli_jjim,2);
        dbManager.addFood("Kimchi Jigae","Kimchi soup with pork belly and tofu",
                14.0,R.drawable.kimchi_jigae,2);
        dbManager.addFood("Kimchi","One serving of kimchi",
                4.0,R.drawable.kimchi,2);
        dbManager.addFood("Rice","One serving of rice",
                4.0,R.drawable.rice,2);

    }
}
