package com.example.shweta.railway;

/**
 * Created by shweta on 20-04-2017.
 */

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static com.example.shweta.railway.LoginDataBaseAdapter.db;

public class FoodMenu extends AppCompatActivity {

    LoginDataBaseAdapter loginDataBaseAdapter;
    ExpandableListAdapter listAdapter;

    ExpandableListView expListView;
    List<String> listDataHeader ;
    HashMap<String, List<String>> listDataChild;
    public static ArrayAdapter<String> Aadapter ;

    public static  ArrayList<String> list = null;

    Button cart;

    public static String[] array = new String[50];
    int No_of_items = 0;
    public static int total = 0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_menu);

        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        expListView = (ExpandableListView) findViewById(R.id.Explv);
        cart = (Button)findViewById(R.id.cart);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // Listview Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });



        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                No_of_items++;
                cart.setText("Cart : " + No_of_items);

                insert_into_cart(groupPosition,childPosition);

                // TODO Auto-generated method stub
                Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Cart.class);
                startActivity(i);
            }
        });
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("North Indian");
        listDataHeader.add("South Indian");
        listDataHeader.add("Gujarati");
        listDataHeader.add("Punjabi");
        listDataHeader.add("Fast Food");
        listDataHeader.add("Desert");


        // Adding child data
        List<String> Ni = new ArrayList<String>();
        Ni.add("Dum Aloo (Rs.50)");
        Ni.add("Poha (Rs.40)");
        Ni.add("Fara (Rs.60)");
        Ni.add("Paneer Butter Masala (Rs.160)");
        Ni.add("Cholle Puri (Rs.150)");
        Ni.add("Dahi Vada (Rs.150)");


        List<String> Si = new ArrayList<String>();
        Si.add("Dosa (Rs.150)");
        Si.add("Idli (Rs.100)");
        Si.add("Biryani (Rs.150)");
        Si.add("Paal Payasam (Rs.150)");
        Si.add("Coorgi Pandi Curry (Rs.75)");
        Si.add("Upma (Rs.50)");


        List<String> G = new ArrayList<String>();
        G.add("Gujarati Thali (Rs.250)");
        G.add("Puran Poli (Rs.80)");
        G.add("Khaman Dhokla (Rs.90)");
        G.add("Handvo (Rs.70)");
        G.add("Sukhadi (Rs.70)");


        List<String> P = new ArrayList<String>();
        P.add("Naan (Rs.50)");
        P.add("Paratha (Rs.50)");
        P.add("Paneer Tikka (Rs.150)");
        P.add("Paneer Bhurji (Rs.150)");
        P.add("Veg Jaipuri (Rs.150)");
        P.add("Veg Hydrabadi (Rs.150)");

        List<String> FF = new ArrayList<String>();
        FF.add("Pizza (Rs.250)");
        FF.add("Sandwich (Rs.150)");
        FF.add("Hotdog (Rs.60)");
        FF.add("French Fries (Rs.90)");
        FF.add("Mccain (Rs.90)");
        FF.add("Burger (Rs.90)");

        List<String> D = new ArrayList<String>();
        D.add("Ice Cream (Rs.50)");
        D.add("Brownie (Rs.150)");
        D.add("Chaas (Rs.30)");
        D.add("Lassi (Rs.50)");
        D.add("Colddrink (Rs.40)");


        listDataChild.put(listDataHeader.get(0), Ni); // Header, Child data
        listDataChild.put(listDataHeader.get(1), Si);
        listDataChild.put(listDataHeader.get(2), G);
        listDataChild.put(listDataHeader.get(3), P);
        listDataChild.put(listDataHeader.get(4), FF);
        listDataChild.put(listDataHeader.get(5), D);
    }

    void insert_into_cart(int GP,int CP){

        if(GP==0){
            switch (CP){
                case 0:
                    loginDataBaseAdapter.insertEntry_FOOD("Dum Aloo",50);
                    total = total + 50;
                    //return "Dum Aloo";
                    break;
                case 1:
                    loginDataBaseAdapter.insertEntry_FOOD("Poha",40);
                    total = total + 40;
                    //return "Poha";
                    break;
                case 2:
                    loginDataBaseAdapter.insertEntry_FOOD("Fara",60);
                    total = total + 60;
                    //return "Fara";
                    break;
                case 3:
                    loginDataBaseAdapter.insertEntry_FOOD("Paneer Butter Masala",160);
                    total = total + 160;
                    //return "Paneer Butter Masala";
                    break;
                case 4:
                    loginDataBaseAdapter.insertEntry_FOOD("Chhole Puri",150);
                    total = total + 150;
                    //return "Chhole Puri";
                    break;
                case 5:
                    loginDataBaseAdapter.insertEntry_FOOD("Dahi Vada",150);
                    total = total + 150;
                    //return "Dahi Vada";
                    break;
            }
        }
        else
        if(GP==1){
            switch (CP){
                case 0:
                    loginDataBaseAdapter.insertEntry_FOOD("Dosa",150);
                    total = total + 150;
                    //return "Dosa";
                break;
                case 1:
                    loginDataBaseAdapter.insertEntry_FOOD("Idli",100);
                    total = total + 100;
                    //return "Idli";
                break;
                case 2:
                    loginDataBaseAdapter.insertEntry_FOOD("Biryani",150);
                    total = total + 150;
                  //  return "Biryani";
                    break;
                case 3:
                    loginDataBaseAdapter.insertEntry_FOOD("Paal Payasam",150);
                    total = total + 150;
                    //return "Paal Payasam";
                    break;
                case 4:
                    loginDataBaseAdapter.insertEntry_FOOD("Coorgi Pandi Curry",75);
                    total = total + 75;
                    //return "Coorgi Pandi Curry";
                    break;
                case 5:
                    loginDataBaseAdapter.insertEntry_FOOD("Upma",50);
                    total = total + 50;
                    //return "Upma";
                    break;
            }
        }
        else
        if(GP==2){
            switch (CP){
                case 0:
                    loginDataBaseAdapter.insertEntry_FOOD("Gujarati Thali",250);
                    total = total + 250;
                //    return "Gujarati Thali";
                    break;
                case 1:
                    loginDataBaseAdapter.insertEntry_FOOD("Puran Poli",80);
                    total = total + 80;
                  //  return "Puran Poli";
                    break;
                case 2:
                    loginDataBaseAdapter.insertEntry_FOOD("Khaman Dhokla",90);
                    total = total + 90;
                    //return "Khaman Dhokla";
                    break;
                case 3:
                    loginDataBaseAdapter.insertEntry_FOOD("Handvo",70);
                    total = total + 70;
                   // return "Handvo";
                    break;
                case 4:
                    loginDataBaseAdapter.insertEntry_FOOD("Sukhadi",70);
                    total = total + 70;
                   // return "Sukhadi";
                    break;
            }
        }
        else
        if(GP==3){
            switch (CP){
                case 0:
                    loginDataBaseAdapter.insertEntry_FOOD("Naan",50);
                    total = total + 50;
                 //   return "Naan";
                    break;
                case 1:
                    loginDataBaseAdapter.insertEntry_FOOD("Paratha",50);
                    total = total + 50;
                   // return "Paratha";
                    break;
                case 2:
                    loginDataBaseAdapter.insertEntry_FOOD("Paneer Tikka",150);
                    total = total + 150;
                    //return "Paneer Tikka";
                    break;
                case 3:
                    loginDataBaseAdapter.insertEntry_FOOD("Paneer Bhurji",150);
                    total = total + 150;
                    //return "Paneer Bhurji";
                    break;
                case 4:
                    loginDataBaseAdapter.insertEntry_FOOD("Veg Jaipuri",150);
                    total = total + 150;
                    //return "Veg Jaipuri";
                    break;
                case 5:
                    loginDataBaseAdapter.insertEntry_FOOD("Veg Hydrabadi",150);
                    total = total + 150;
                    //return "Veg Hydrabadi";
                    break;
            }
        }
        else
        if(GP==4){
            switch (CP){
                case 0:
                    loginDataBaseAdapter.insertEntry_FOOD("Pizza",250);
                    total = total + 250;
                   // return "Pizza";
                    break;
                case 1:
                    loginDataBaseAdapter.insertEntry_FOOD("Sandwich",150);
                    total = total + 150;
                  //  return "Sandwich";
                    break;
                case 2:
                    loginDataBaseAdapter.insertEntry_FOOD("Hot Dog",60);
                    total = total + 60;
                  //  return "Hot Dog";
                    break;
                case 3:
                    loginDataBaseAdapter.insertEntry_FOOD("French Fries",90);
                    total = total + 90;
                   // return "French Fries";
                    break;
                case 4:
                    loginDataBaseAdapter.insertEntry_FOOD("Mccain",90);
                    total = total + 90;
                  //  return "Mccain";
                    break;
                case 5:
                    loginDataBaseAdapter.insertEntry_FOOD("Burger",90);
                    total = total + 90;
                  //  return "Burger";
                    break;
            }
        }
        else
        if(GP==5){
            switch (CP){
                case 0:
                    loginDataBaseAdapter.insertEntry_FOOD("Icecream",50);
                    total = total + 50;
                  //  return "Icecream";
                    break;
                case 1:
                    loginDataBaseAdapter.insertEntry_FOOD("Brownie",150);
                    total = total + 150;
                 //   return "Brownie";
                    break;
                case 2:
                    loginDataBaseAdapter.insertEntry_FOOD("Chaas",30);
                    total = total + 30;
                 //   return "Chaas";
                    break;
                case 3:
                    loginDataBaseAdapter.insertEntry_FOOD("Lassi",50);
                    total = total + 50;
                 //   return "Lassi";
                    break;
                case 4:
                    loginDataBaseAdapter.insertEntry_FOOD("Colddrink",40);
                    total = total + 40;
                 //   return "Colddrink";
                    break;
            }
        }
    }
}
