package com.example.appnbrone.ui.notifications;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.appnbrone.R;
import com.example.appnbrone.databinding.FragmentNotificationsBinding;
import com.google.android.material.button.MaterialButtonToggleGroup;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private FragmentNotificationsBinding binding;
    private NumberPicker picker1;
    private String[] pickerValues;
    private String myNumbers;
    private MaterialButtonToggleGroup myButtons;
    private CountDownTimer myTimer;
    int valueOnPicker1 =0;
    int secondsRemaining;
    private static final String TAG = "MyActivity";
    Uri myRingtoneUri;
    Ringtone myAlarm;

    @Override
    public void onStart() {


        // määritetään numeroiden valitsija ajastimeen:
        picker1 = binding.numberpicker1;
        picker1.setMaxValue(60);
        picker1.setMinValue(0);

        pickerValues = new String[61];

        for(int i=0;i<=60;i++){
            //String strval = String.valueOf(i);
            //myNumbers=i + " s";
            pickerValues[i]=i +" S";
        }
        picker1.setDisplayedValues(pickerValues);

        picker1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
        public void onValueChange(NumberPicker numberPicker, int i, int i1){
                valueOnPicker1 = picker1.getValue();

        }
        });

        //Hälytysäänen määrittely:
        myRingtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        myAlarm = RingtoneManager.getRingtone(getContext(), myRingtoneUri);

        // Määritellään animaatio:
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.ringinganimation);


        //Näppäinryhmän asetukset:
        myButtons= binding.toggleGroup;
        myButtons.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                if(isChecked){
                    if(checkedId == R.id.button1){
                        myTimer =new CountDownTimer(valueOnPicker1*1000,1000){
                            public void onTick(long millisUntilFinished) {
                                binding.textNotifications.setText("Time remaining "+ millisUntilFinished / 1000);
                                secondsRemaining=(int)millisUntilFinished/1000;
                                picker1.setValue(secondsRemaining);
                            }
                            public void onFinish() {
                                myAlarm.play();
                                binding.textNotifications.setText("end!");
                                binding.textNotifications.startAnimation(animation);
                            }
                        }.start();
                    } else if(checkedId == R.id.button2){
                        myTimer.cancel();
                        valueOnPicker1 = secondsRemaining;
                        binding.textNotifications.setText("Timer paused");
                    }else if(checkedId == R.id.button3){
                        myAlarm.stop();
                        myTimer.cancel();
                        picker1.setValue(0);
                        binding.textNotifications.clearAnimation();
                        binding.textNotifications.setText("Countdown Stopped!");

                    }
                }
            }
        });


        super.onStart();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textNotifications;
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText("the final countdown");
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}