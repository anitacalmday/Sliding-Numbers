package com.techexchange.mobileapps.application1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.*;

public class MainActivity extends AppCompatActivity {


private static final int col = 3;
private static final int dimensions = col*col;
private static String[] tiles;
private static GridView gv;
private static GridViewAdapter gva;
private Context context;
private int emptyTileIndex;
private Bundle savedState;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArray("tiles", tiles);
        outState.putInt("emptyTileIndex", emptyTileIndex);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (savedState != null)
        {
            display(context);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
        //cutSpriteSheet();
        gv = findViewById(R.id.grid_view);

        init();
        scramble();
        display(getApplicationContext());
        context = getApplicationContext();
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                swap(getApplicationContext(), position);
                if (isSolved()){
                    Toast toast = Toast.makeText(getApplicationContext(), "Congrats! You won.", Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });

        if(savedInstanceState != null){
            emptyTileIndex = savedInstanceState.getInt("emptyTileIndex");
            tiles = savedInstanceState.getStringArray("tiles");
            savedState = savedInstanceState ;
        }

    }

    private void init() {
        tiles = new String[dimensions];
        for (int i = 0; i < dimensions; i++){
            tiles[i] = String.valueOf(i);
        }
    }

    private void display(Context context) {
        ArrayList<ImageView> images = new ArrayList<>();
        ImageView image;

        for (int i = 0; i < tiles.length; i++){
            image = new ImageView(context);

            if (tiles[i].equals("0"))
                image.setBackgroundResource(R.drawable.tile001);
            else if (tiles[i].equals("1"))
                image.setBackgroundResource(R.drawable.tile002);
            else if (tiles[i].equals("2"))
                image.setBackgroundResource(R.drawable.tile003);
            else if (tiles[i].equals("3"))
                image.setBackgroundResource(R.drawable.tile004);
            else if (tiles[i].equals("4"))
                image.setBackgroundResource(R.drawable.tile005);
            else if (tiles[i].equals("5"))
                image.setBackgroundResource(R.drawable.tile006);
            else if (tiles[i].equals("6"))
                image.setBackgroundResource(R.drawable.tile007);
            else if (tiles[i].equals("7"))
                image.setBackgroundResource(R.drawable.tile008);
            else if (tiles[i].equals("8")) {
                image.setBackgroundColor(0);
                emptyTileIndex = i;
            }

            images.add(image);

        }
            gva = new GridViewAdapter(images, 350,350, context);
            gv.setAdapter(gva);

    }

    private void scramble() {
        int index;
        String temp;
        Random r = new Random();

        emptyTileIndex = 8;
        ArrayList<Integer> validIndices = new ArrayList<>();
        for (int i = 50; i > 0; i--){
            if (emptyTileIndex + 3 < tiles.length-1)
            {
                validIndices.add(emptyTileIndex  + 3);
            }
            if (emptyTileIndex - 3 >= 0 ){
                validIndices.add(emptyTileIndex - 3);
            }
            if (emptyTileIndex + 1 < tiles.length-1){
                validIndices.add(emptyTileIndex + 1);
            }
            if (emptyTileIndex - 1 >= 0 ){
                validIndices.add(emptyTileIndex-1);
            }

            int pos =  r.nextInt(validIndices.size()); //get rand int by len
            index  = validIndices.get(pos);
            temp = tiles[index];
            tiles[index] = tiles[emptyTileIndex];
            tiles[emptyTileIndex] = temp;
//ughhhhhhhh
            emptyTileIndex = index;

        }

    }
    private void swap(Context c, int position){
        String temp;
        if (Math.abs(position-emptyTileIndex) == 1 || Math.abs(position-emptyTileIndex) == 3)
        {
            temp = tiles[emptyTileIndex];
            tiles[emptyTileIndex]=tiles[position];
            tiles[position] = temp;
            emptyTileIndex = position;
            display(c);

        }
        display(c);
    }

    private boolean isSolved()  {
        boolean solved = false;
        for (int i = 0; i < tiles.length; i++){
            if (tiles[i].equals(String.valueOf(i))){
                solved = true;
            } else {
                solved = false;
                break;
            }
        }
        return solved;

    }


}
