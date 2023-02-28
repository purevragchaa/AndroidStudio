/*
 * Copyright (C) 2018 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.droidcafe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

/**
 * This activity is blank for now. Subsequent versions of the app
 * provide input controls and a delivery method for an order.
 */
public class OrderActivity extends AppCompatActivity {
    CheckBox checkBox1;
    CheckBox checkBox2;
    CheckBox checkBox3;
    CheckBox checkBox4;
    CheckBox checkBox5;
    String name1;
    String name2;
    String name3;
    String name4;
    String name5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        checkBox1 = (CheckBox) findViewById(R.id.Chocolate_Syrup_checkBox);
        checkBox2 = (CheckBox) findViewById(R.id.Sprinkles_checkBox);
        checkBox3 = (CheckBox) findViewById(R.id.Crushed_nuts_checkBox);
        checkBox4 = (CheckBox) findViewById(R.id.Cherries_checkBox);
        checkBox5 = (CheckBox) findViewById(R.id.Orio_checkBox);
        name1 = getString(R.string.chocolate_syrup);
        name2 = getString(R.string.sprinkles);
        name3 = getString(R.string.crushed_nuts);
        name4 = getString(R.string.cherries);
        name5 = getString(R.string.orio);
    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }

    public void show_toast(View view) {
        // Is the button now checked?
        //boolean checked = ((CheckBox) view).isChecked();
        // Check which radio button was clicked.
        checkBox1.isChecked();
            displayToast(getString(
                    R.string.toast_message)+name1);

        checkBox2.isChecked();
            displayToast(getString(
                R.string.toast_message)+name2);

        checkBox3.isChecked();
            displayToast(getString(
                R.string.toast_message)+name3);

        checkBox4.isChecked();
            displayToast(getString(
                R.string.toast_message)+name4);

        checkBox5.isChecked();
            displayToast(getString(
                R.string.toast_message)+name5);

        }

}


