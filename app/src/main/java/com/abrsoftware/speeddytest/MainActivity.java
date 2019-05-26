package com.abrsoftware.speeddytest;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.abrsoftware.speeddytest.helper.FirebaseHelper;
import com.abrsoftware.speeddytest.service.ApiServiceSingleton;
import com.abrsoftware.speeddytest.view.LoginView.LoginView;
import com.abrsoftware.speeddytest.view.detailqoute.DetailQouteReact;
import com.abrsoftware.speeddytest.view.detailqoute.DetailQouteView;
import com.abrsoftware.speeddytest.view.homeView.HomeView;
import com.facebook.react.LifecycleState;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.shell.MainReactPackage;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements DefaultHardwareBackBtnHandler {

    private Toolbar toolbar;
    private BottomSheetDialog bottomSheet;
    public Bundle bundle;
    private final int OVERLAY_PERMISSION_REQ_CODE = 1;
    private View currentView;
    private ReactInstanceManager mReactInstanceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        createInstanceReact();
        checkPer();
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
        setCurrentView(getSupportFragmentManager().findFragmentById(R.id.fragment_container).getView());
        showToolbar(false, "");
        changeFragment(DetailQouteReact.class, bundle);
    }


    @OnClick(R.id.optionShare)
    public void optionShare() {
        dissmissBottomSheet();
        changeFragment(DetailQouteView.class, bundle);
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

    private void checkPer() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == OVERLAY_PERMISSION_REQ_CODE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (Settings.canDrawOverlays(this)) {

                } else {
                    Toast.makeText(this, "ACTION_MANAGE_OVERLAY_PERMISSION权限已被拒绝", Toast.LENGTH_SHORT).show();

                }
            }
        }
        mReactInstanceManager.onActivityResult(requestCode, resultCode, data);

    }

    public WindowManager.LayoutParams fixAndroid() {
        WindowManager.LayoutParams params;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            params = new WindowManager.LayoutParams(
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    PixelFormat.TRANSLUCENT);
        } else {
            params = new WindowManager.LayoutParams(
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.TYPE_SYSTEM_ERROR,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    PixelFormat.TRANSLUCENT);
        }
        return params;
    }

    public WindowManager getServiceW() {
        return (WindowManager) getSystemService(WINDOW_SERVICE);
    }

    public View getCurrentView() {
        return currentView;
    }

    public void setCurrentView(View currentView) {
        this.currentView = currentView;
    }

    private void createInstanceReact() {
        mReactInstanceManager = ReactInstanceManager.builder()
                .setApplication(getApplication())
                .setBundleAssetName("index.android.bundle")
                .setJSMainModuleName("index.android")
                .addPackage(new MainReactPackage())
                .setUseDeveloperSupport(BuildConfig.DEBUG)
                .setInitialLifecycleState(LifecycleState.RESUMED)
                .build();

    }

    public ReactInstanceManager getmReactInstanceManager() {
        return mReactInstanceManager;
    }

    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mReactInstanceManager != null) {
            mReactInstanceManager.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mReactInstanceManager != null) {
            mReactInstanceManager.onResume(this, this);
        }
    }


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU && mReactInstanceManager != null) {
            mReactInstanceManager.showDevOptionsDialog();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }
}
