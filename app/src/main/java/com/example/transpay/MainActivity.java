package com.example.transpay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.transpay.databinding.ActivityMainBinding;
import com.example.transpay.databinding.UserSigninBinding;

public class MainActivity extends AppCompatActivity {

    private AutoCompleteTextView userTypeTextView;
    private UserSigninBinding signinBinding;
    private ActivityMainBinding mainBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        signinBinding = DataBindingUtil.bind(mainBinding.signinLayoutInclude.getRoot());

        setUserTypeAutocompleteTextView();


    }

    private void setUserTypeAutocompleteTextView() {
        userTypeTextView = signinBinding.signinUserTypeAutoCompleteTv;
        ArrayAdapter<String> userTypesAdapter = new ArrayAdapter<>
                (this, R.layout.user_type_item, getResources().getStringArray(R.array.user_types));
        userTypeTextView.setAdapter(userTypesAdapter);

        userTypeTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                userTypeTextView.setEnabled(false);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(userTypeTextView.isPerformingCompletion()){
                    userTypeTextView.dismissDropDown();
                    userTypeTextView.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}