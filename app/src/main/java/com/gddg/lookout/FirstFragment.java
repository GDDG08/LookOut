package com.gddg.lookout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.gddg.lookout.databinding.FragmentFirstBinding;
import com.gddg.lookout.utils.api.APICallback;
import com.gddg.lookout.utils.api.APIHandler;
import com.gddg.lookout.utils.api.javabean.BasicRTN;
import com.gddg.lookout.utils.api.javabean.User.UserLoginRTN;
import com.gddg.lookout.utils.api.javabean.User.UserWXLoginRQT;

public class FirstFragment extends Fragment {

    private static FragmentFirstBinding binding;

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

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
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
}