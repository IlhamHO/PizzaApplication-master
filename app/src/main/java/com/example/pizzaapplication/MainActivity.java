package com.example.pizzaapplication;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button btnOrder;
    private CoordinatorLayout myLayout;

    private Button btnFormatOrder;
    private String [] listFormat;
    private TextView formatSelected;
    private String chooseItem;

    private Button btnToppingsOrder;
    private String[] listItems;
    private TextView itemSelected;
    private boolean[] checkedItems;
    private ArrayList<Integer> mUserItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myLayout = (CoordinatorLayout)findViewById(R.id.myLayout);
        btnOrder= (Button)findViewById(R.id.btn_order);
        btnFormatOrder = (Button)findViewById(R.id.btn_formatOrder);
        btnToppingsOrder = (Button)findViewById(R.id.btn_toppingsOrder);
        itemSelected = (TextView)findViewById(R.id.tv_order);
        formatSelected =(TextView)findViewById(R.id.tv_format);

        listItems = getResources().getStringArray(R.array.shopping_item);
        checkedItems = new boolean[listItems.length];

        listFormat = getResources().getStringArray(R.array.shopping_format_item);
        btnFormatOrder.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Choose format");
                builder.setSingleChoiceItems(listFormat, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        chooseItem= listFormat[which];
                    }
                });
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        formatSelected.setText(chooseItem);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setNeutralButton("Clear all", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        formatSelected.setText("");

                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });

       btnToppingsOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                mBuilder.setTitle(R.string.dialog_titel);
                mBuilder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position, boolean isChecked) {
                        if(isChecked){
                            if(!mUserItems.contains(position)){
                                mUserItems.add(position);
                            } else if (mUserItems.contains(position)){
                                mUserItems.remove(position);
                            }
                                           }

                    }
                });
            mBuilder.setCancelable(false);
            mBuilder.setPositiveButton(R.string.ok_label, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    String item = "";
                    for(int i =0; i < mUserItems.size(); i++ ) {
                        item = item + listItems[mUserItems.get(i)];
                        if(i != mUserItems.size() -1){
                            item = item + ",";
                        }
                    }
                    itemSelected.setText(item);
                    }
            });
            mBuilder.setNegativeButton(R.string.dismiss_label, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    dialogInterface.dismiss();
                }
            });
            mBuilder.setNeutralButton(R.string.clear_all_label, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                for(int i = 0; i< checkedItems.length; i++){
                    checkedItems[i]= false;
                    mUserItems.clear();
                    itemSelected.setText("");
                }
                }
            });
            AlertDialog mDialog = mBuilder.create();
            mDialog.show();
            }
        });


       btnOrder.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
Snackbar sb;
sb=Snackbar.make(myLayout,"Order within",Snackbar.LENGTH_LONG);
sb.setAction("Cancel", new View.OnClickListener() {
    @Override
    public void onClick(View v) {

    }
});
               sb.show();
           }
       });
    }
}
