package com.abrsoftware.speeddytest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.abrsoftware.speeddytest.helper.FirebaseHelper;
import com.abrsoftware.speeddytest.service.ApiServiceSingleton;
import com.abrsoftware.speeddytest.view.LoginView.LoginView;
import com.abrsoftware.speeddytest.view.detailqoute.DetailQouteView;
import com.abrsoftware.speeddytest.view.homeView.HomeView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private BottomSheetDialog bottomSheet;
    public Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);
        toolbar = findViewById(R.id.toolbar);
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
            toolbar.setNavigationOnClickListener(v -> onBackPressed());
        }
    }

    public void inflateBottomSheet(int layout, int style) {
        bottomSheet = new BottomSheetDialog(MainActivity.this, style);
        bottomSheet.setContentView(layout);
        ButterKnife.bind(MainActivity.this, bottomSheet);
    }

    public void showBottomSheet() {
        bottomSheet.show();
    }

    public void dissmissBottomSheet() {
        bottomSheet.dismiss();
    }

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    @OnClick(R.id.closeMenu)
    public void initCloseMenu() {
        dissmissBottomSheet();
    }

    @OnClick(R.id.detailQoute)
    public void detailQoute() {
        dissmissBottomSheet();
        changeFragment(DetailQouteView.class, bundle);
    }

    @OnClick(R.id.optionShare)
    public void optionShare() {
        dissmissBottomSheet();
        Fragment fragmentView = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        Snackbar.make(fragmentView.getView(), getString(R.string.app_name), Snackbar.LENGTH_SHORT).show();
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
