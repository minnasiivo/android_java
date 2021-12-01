package com.example.appnbrone.ui.home;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.appnbrone.AirplanemodeReceiver;
import com.example.appnbrone.Guess;
import com.example.appnbrone.companies.Companies;
import com.example.appnbrone.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    AirplanemodeReceiver airplanemodeReceiver = new AirplanemodeReceiver();

    @Override
    public void onStart() {
        super.onStart();



        final Button button = binding.playButton;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), Guess.class);
                    v.getContext().startActivity(intent);
    }});



        final Button searchButton = binding.searchBtn;
        searchButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String searchValue = binding.editText.getText().toString();
                Intent intent2 = new Intent(view.getContext(), Companies.class);
                intent2.putExtra("Value1", searchValue);
                view.getContext().startActivity(intent2);
            }});


        final Button button2 = binding.myButton;
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                button2.setText("uusi teksti");
                TextView helloView = binding.myTextView;
                if(helloView.getVisibility()==View.INVISIBLE){
                    helloView.setVisibility(View.VISIBLE);}
                else{
                    helloView.setVisibility(View.INVISIBLE);
                    button2.setText("vanha teksti :D");
                }

            }
        });


    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        IntentFilter filter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        requireActivity().registerReceiver(airplanemodeReceiver, filter);

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }






    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        requireActivity().unregisterReceiver(airplanemodeReceiver);
    }

}