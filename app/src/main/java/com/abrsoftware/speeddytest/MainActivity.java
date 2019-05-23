package com.abrsoftware.speeddytest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.abrsoftware.speeddytest.helper.FirebaseHelper;
import com.abrsoftware.speeddytest.service.ApiServiceSingleton;
import com.abrsoftware.speeddytest.view.LoginView.LoginView;
import com.abrsoftware.speeddytest.view.homeView.HomeView;
import com.abrsoftware.speeddytest.view.thankyou.ThankYouView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private ResponsiveUIstate state;
    private Toolbar toolbar;
    private TelephonyManager tMgr;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);
        toolbar = findViewById(R.id.toolbar);
        tMgr = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        setSupportActionBar(toolbar);
        ApiServiceSingleton.getInstance().apiService.initApiService();
        if (FirebaseHelper.getInstance().getAuthReference().getCurrentUser() == null) {
            changeFragment(LoginView.class, null);
        } else {
            changeFragment(HomeView.class, null);
        }
    }



    public void changeFragment(Class<? extends Fragment> fragmentClass, Bundle bundle) {
        try {
            Fragment fragment = fragmentClass.newInstance();
            if (bundle != null) {
                fragment.setArguments(bundle);
            }
            FragmentManager manager = this.getSupportFragmentManager();
            FragmentTransaction tx = manager.beginTransaction();
            tx.replace(R.id.fragment_container, fragment, fragmentClass.getCanonicalName());
            tx.addToBackStack(null);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showToolbar(boolean show, String title) {
        toolbar.setVisibility(show ? View.VISIBLE : View.GONE);
        toolbar.setTitle(title);
    }

    public void showBackBtn(boolean showBackBtn) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(showBackBtn);
            getSupportActionBar().setDisplayShowHomeEnabled(showBackBtn);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        Fragment fragmentView = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (fragmentView instanceof HomeView || fragmentView instanceof LoginView) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

    public TelephonyManager gettMgr() {
        return tMgr;
    }

    public void settMgr(TelephonyManager tMgr) {
        this.tMgr = tMgr;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Fragment fragmentView = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        switch (requestCode) {
            case 1:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    @SuppressLint("MissingPermission") String number = tMgr.getLine1Number();
                    String numberText = getString(R.string.tu_msisdn_es) + " " + number;
                    ((ThankYouView) fragmentView).onResultActivity(numberText);
                } else {
                    ((ThankYouView) fragmentView).onResultActivity(getString(R.string.error_msidn));
                }
                break;

            default:
                break;
        }
    }

    public interface onResult {
        void onResultActivity(String number);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                changeFragment(LoginView.class, null);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
