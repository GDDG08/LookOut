package com.gddg.lookout;

import static com.gddg.lookout.SecondFragment.api;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.gddg.lookout.databinding.FragmentFirstBinding;
import com.gddg.lookout.utils.api.APICallback;
import com.gddg.lookout.utils.api.APIHandler;
import com.gddg.lookout.utils.api.javabean.BasicRTN;
import com.gddg.lookout.utils.api.javabean.Cloud.STT_RTN;
import com.gddg.lookout.utils.api.javabean.Cloud.TTS_RTN;
import com.gddg.lookout.utils.api.javabean.User.UserLoginRTN;
import com.gddg.lookout.utils.api.javabean.User.UserWXLoginRQT;
import com.gddg.lookout.utils.tool.VoiceUtils;

import org.w3c.dom.Text;

public class FirstFragment extends Fragment {

    private static FragmentFirstBinding binding;
    private TextView textview1;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(view12 -> NavHostFragment.findNavController(FirstFragment.this)
                .navigate(R.id.action_FirstFragment_to_SecondFragment));

        textview1= binding.textviewFirst;

        STT_test(0);
        binding.buttonStt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        STT_test(1);
                        break;
                    case MotionEvent.ACTION_UP:
                        STT_test(2);
                        break;
                }
                return false;
            }
        });
        binding.buttonTts.setOnClickListener(view2 -> TTS_test());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    //    public static void onAuth(String code) {
//        binding.textviewFirst.setText("获取到的Code：" + code);
//        APIHandler api = new APIHandler();
//        api.onUserWXLogin(code, new APICallback() {
//            @Override
//            public void onSuccess(String msg, BasicRTN rtn) {
//                UserLoginRTN data  =(UserLoginRTN) rtn;
//                binding.textviewFirst.setText(msg+"\nrtn:\n"+data.getUserID());
//            }
//
//            @Override
//            public void onError(String info) {
//                binding.textviewFirst.setText(info);
//            }
//        });
//    }
    public void TTS_test() {
        APIHandler api = new APIHandler();
        api.cloudTTS("你好世界", new APICallback() {

            @Override
            public void onSuccess(String msg, BasicRTN rtn, String originData) {
                TTS_RTN data = (TTS_RTN) rtn;
                byte[] audio = data.getAudio();
                VoiceUtils voice = new VoiceUtils();
                voice.playSound(audio);
                Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(String info) {
                Toast.makeText(getContext(), info, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void STT_test(int mode) {
        if (mode == 0) {
            VoiceUtils.recordInit();
        } else if (mode == 1) {
            VoiceUtils.recordStart();
        } else if (mode == 2) {
            byte[] record = VoiceUtils.recordStop();
            VoiceUtils.playSound4Rec(record);
            api.cloudSTT(record, new APICallback() {
                @Override
                public void onSuccess(String msg, BasicRTN rtn, String originData) {
                   STT_RTN data =  (STT_RTN) rtn;
                   String text = data.getText();
                   textview1.setText(text);
                    Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
                }

                @Override
                public void onError(String info) {
                    Toast.makeText(getContext(), info, Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}