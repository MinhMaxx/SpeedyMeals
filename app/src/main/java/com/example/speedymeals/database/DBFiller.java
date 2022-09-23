package com.example.speedymeals.database;

import com.example.speedymeals.R;

public class DBFiller {
    private DBManager dbManager;

    public DBFiller(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public void fillRestaurant() {
        dbManager.addRestaurant("Phoever", R.drawable.phoever);
        dbManager.addRestaurant("Arirang", R.drawable.arirang);
        dbManager.addRestaurant("Hilton Pizza", R.drawable.hilton_pizza);
        dbManager.addRestaurant("Just Coffee", R.drawable.just_coffee);
        dbManager.addRestaurant("Toast and Things", R.drawable.toast_and_things);
        dbManager.addRestaurant("Just T",R.drawable.just_t);
        dbManager.addRestaurant("Good Fortune",R.drawable.good_fortune);
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


        dbManager.addFood("Pizza Margherita","Mozzarella & cheddar mix, tomato sauce, sliced tomato & oregano",
                19.5,R.drawable.margherita,3);
        dbManager.addFood("Pizza Pepperoni","Mozzarella & cheddar mix, tomato sauce & pepperoni",
                20.0,R.drawable.pepperoni,3);
        dbManager.addFood("Pizza Seafood","Mozzarella & cheddar mix, tomato sauce, tomato, prawns, crab, squid, baby octopus, mussels, tuna",
                21.0, R.drawable.seafood,3);
        dbManager.addFood("Pizza Supreme","Mozzarella & cheddar mix, tomato sauce, pineapple, onion, capsicum, GF FR ham, mushroom, pepperoni, tomato",
                18.0,R.drawable.supreme,3);
        dbManager.addFood("Pizza Tropicana","Vegan pizza: vegan Tasty cheese, tomato sauce & Vegenaise mix, pineapple, mushroom, chilli tofu, jalapeños",
                15.0,R.drawable.tropicana,3);
        dbManager.addFood("Pizza Animal Lover","Vegan pizza: Dairy free Almond Mozzarella cheese, bbq sauce, spinach, mushroom & vegan sausage",
                14.0,R.drawable.animal_lover,3);
        dbManager.addFood("Pizza Aussie","Mozzarella & cheddar mix, tomato sauce, tomato & GF FR bacon",
                18.5,R.drawable.aussie,3);
        dbManager.addFood("Pizza ","Mozzarella & cheddar mix, egg mayonnaise, onion, mushroom, tomato, GF FR bacon & swirl of hot chilli sauce",
                22.0,R.drawable.hilton_deluxe,3);

        dbManager.addFood("Espresso","Just your usual Espresso",
                3.5,R.drawable.espresso,4);
        dbManager.addFood("Flat White","Just your usual Flat White",
                4.5,R.drawable.flat_white,4);
        dbManager.addFood("Mocha","Just your usual Mocha",
                5.0, R.drawable.mocha,4);
        dbManager.addFood("Hot Chocolate","Just your usual Hot Chocolate",
                4.5,R.drawable.hot_chocolate,4);
        dbManager.addFood("Latte","Just your usual Latte",
                4.5,R.drawable.latte,4);
        dbManager.addFood("Cappuccino","Just your usual Cappuccino",
                14.0,R.drawable.cappuccino,4);

        dbManager.addFood("Avo on Toast","Mashed Avo on Toast with Poached Egg",
                15.0,R.drawable.avo_on_toast,5);
        dbManager.addFood("Bean on Toast","Baked Bean on Toast",
                10.5,R.drawable.bean_on_toast,5);
        dbManager.addFood("Egg on Toast","Poached Egg on Toast",
                7.0, R.drawable.eggs_on_toast,5);
        dbManager.addFood("French Toast","French Toast with Maple Syrup and seasonal fruit",
                15.5,R.drawable.french_toast,5);
        dbManager.addFood("Grilled Cheese Toast","Grilled toast with 4 cheeses",
                7.5,R.drawable.grilled_cheese_toast,5);
        dbManager.addFood("BEC Toast","Bacon Egg and Cheese toast",
                12.0,R.drawable.bacon_egg_toast,5);

        dbManager.addFood("Earl Grey","Just your usual Earl Grey",
                4.5,R.drawable.earl_grey,6);
        dbManager.addFood("English Breakfast","Just your usual English Breakfast",
                4.5,R.drawable.english_breakfast,6);
        dbManager.addFood("Matcha","Just your usual Matcha",
                5.0, R.drawable.matcha,6);
        dbManager.addFood("Green Tea","Just your usual Green Tea",
                4.5,R.drawable.green_tea,6);

        dbManager.addFood("Whole Roasted Duck","Peking duck roasted",
                32.0,R.drawable.roast_duck,7);
        dbManager.addFood("Crispy Skin Roasted Pork","Crispy roasted pork belly",
                25.5,R.drawable.roast_pork,7);
        dbManager.addFood("Braised Beef","Braised beef with daikon radish and carrot",
                24.5,R.drawable.braised_beef,7);
        dbManager.addFood("Fried Rice","Special Fried Rice",
                14.0,R.drawable.fried_rice,7);
        dbManager.addFood("Spring Roll","Meat deep fried sping rolls",
                9.0,R.drawable.spring_roll,7);
        dbManager.addFood("Beef Stir Fried Noodle","Stir fried noodle with beef and veggies",
                15.0,R.drawable.stir_fried_noodle,7);
        dbManager.addFood("Rice","One serving of rice",
                3.5,R.drawable.rice,7);
        dbManager.addFood("Gai Lan","Stir fried gai lan with garlic an oyster sauce",
                10.0,R.drawable.stir_fried_noodle,7);
    }
}
