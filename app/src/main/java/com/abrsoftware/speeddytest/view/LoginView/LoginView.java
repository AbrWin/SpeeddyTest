package com.abrsoftware.speeddytest.view.LoginView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.abrsoftware.speeddytest.MainActivity;
import com.abrsoftware.speeddytest.R;
import com.abrsoftware.speeddytest.ResponsiveUIstate;
import com.abrsoftware.speeddytest.view.LoginView.LoginMVP.LoginMVP;
import com.abrsoftware.speeddytest.view.LoginView.LoginMVP.LoginPresenterImp;


public class LoginView extends Fragment implements LoginMVP.View {

    private LoginPresenterImp presenterImp;
    private View rootView;

    public LoginView() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.view_login, container, false);
        final EditText user = rootView.findViewById(R.id.inputUser);
        final EditText password = rootView.findViewById(R.id.inputPassword);

        CardView btnLogin = rootView.findViewById(R.id.btn_login);
        presenterImp = new LoginPresenterImp(this);
        presenterImp.oncreate();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String emailTxt = user.getText().toString().trim();
                final String passwordTxt = password.getText().toString().trim();
                presenterImp.loginUser(emailTxt, passwordTxt);
            }
        });
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenterImp.ondestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenterImp.ondestroy();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showMsj(String msj) {
        Toast.makeText(getContext(), msj, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void succesUser() {
        ((MainActivity) getActivity()).changeFragment(ResponsiveUIstate.HOME);
    }
}
