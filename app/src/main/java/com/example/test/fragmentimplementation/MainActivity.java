package com.example.test.fragmentimplementation;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button add,remove,replace,pop;
    private TextView displaycount;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Button newActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add=findViewById(R.id.addFragment);
        remove=findViewById(R.id.removeFragment);
        replace=findViewById(R.id.replaceFragment);
        pop=findViewById(R.id.popFragment);
        displaycount=findViewById(R.id.displayCount);
        fragmentManager=getSupportFragmentManager();
        newActivity=findViewById(R.id.newActivity);

        displaycount.setText("Fragment Count in back stack"+fragmentManager.getBackStackEntryCount());

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFragment();
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeFragment();
            }
        });

        replace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment();
            }
        });

        pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popFragment();
            }
        });

        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                displaycount.setText("Fragment Count in back stack  "+fragmentManager.getBackStackEntryCount());

                StringBuilder status=new StringBuilder("Current status of transaction back stack  is"+fragmentManager.getBackStackEntryCount()+"\n");
                for(int index=fragmentManager.getBackStackEntryCount()-1;index>=0;index--){
                    FragmentManager.BackStackEntry entry=fragmentManager.getBackStackEntryAt(index);
                    status.append(entry.getName()+"\n");
                }

                Log.d("Back stack message",status.toString());
            }
        });

        newActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextAcitivity=new Intent(MainActivity.this,SecondActivity.class);
                startActivity(nextAcitivity);
            }
        });
    }

    private void popFragment() {
        fragmentManager.popBackStack();
    }

    private void replaceFragment() {
        Fragment fragment;
        fragment = fragmentManager.findFragmentById(R.id.fragmentContainer);
        if(fragment instanceof FirstFragment){
            fragment = new SecondFragment();
        }else if(fragment instanceof SecondFragment){
            fragment = new ThirdFragment();
        }else if(fragment instanceof ThirdFragment){
            fragment = new FirstFragment();
        }else{
            fragment = new FirstFragment();
        }
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer,fragment).addToBackStack("replace").commit();
    }

    private void removeFragment() {
        Fragment fragment=fragmentManager.findFragmentById(R.id.fragmentContainer);
        if(fragment!=null){
            fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.remove(fragment);
            fragmentTransaction.addToBackStack("remove");
            fragmentTransaction.commit();
        }
        else{
            Toast.makeText(this,"No Fragment to remove",Toast.LENGTH_SHORT).show();
        }
    }

    private void addFragment() {
        Fragment fragment;
        fragment = fragmentManager.findFragmentById(R.id.fragmentContainer);
        if(fragment instanceof FirstFragment){
            fragment = new SecondFragment();
        }else if(fragment instanceof SecondFragment){
            fragment = new ThirdFragment();
        }else if(fragment instanceof ThirdFragment){
            fragment = new FirstFragment();
        }else{
            fragment = new FirstFragment();
        }
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmentContainer,fragment).addToBackStack("add").commit();
    }

}
