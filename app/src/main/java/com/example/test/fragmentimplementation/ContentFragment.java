package com.example.test.fragmentimplementation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class ContentFragment extends Fragment {
    private Button button;
    private EditText message;
    CallBackInterface callBackInterface;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_content,container,false);
        button=view.findViewById(R.id.display);
        message=view.findViewById(R.id.message);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(callBackInterface!=null){
                    callBackInterface.callBackMethod(message.getText().toString());
                }
            }
        });
        return view;
    }

    public void setCallBackInterface(CallBackInterface callBackInterface){
        this.callBackInterface=callBackInterface;
    }
}
