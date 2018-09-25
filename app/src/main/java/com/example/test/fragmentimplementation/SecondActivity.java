package com.example.test.fragmentimplementation;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity implements CallBackInterface {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        fragmentManager = getSupportFragmentManager();
        button=findViewById(R.id.addFragment);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button.setVisibility(View.GONE);
                addContentFragment();
                button.setClickable(false);
            }
        });

    }

    private void addContentFragment(){
        fragmentTransaction=fragmentManager.beginTransaction();

        ContentFragment contentFragment=new ContentFragment();
        contentFragment.setCallBackInterface(this);

        fragmentTransaction.add(R.id.containerFragment,contentFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void callBackMethod(String message) {
            fragmentTransaction=fragmentManager.beginTransaction();
            Bundle bundle=new Bundle();
            DisplayFragment displayFragment=new DisplayFragment();
            bundle.putString("message",message);
            displayFragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.containerFragment,displayFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
    }
}
