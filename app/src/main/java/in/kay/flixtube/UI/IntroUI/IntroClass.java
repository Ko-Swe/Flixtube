package in.kay.flixtube.UI.IntroUI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.view.View;

import com.codemybrainsout.onboarder.AhoyOnboarderActivity;
import com.codemybrainsout.onboarder.AhoyOnboarderCard;

import java.util.ArrayList;
import java.util.List;

import in.kay.flixtube.R;

public class IntroClass extends AhoyOnboarderActivity {
    AhoyOnboarderCard ahoyOnboarderCard1,ahoyOnboarderCard2,ahoyOnboarderCard3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TakePermission();
        FirstTimeLogic();
        hideSystemUI();
    }

    private void TakePermission() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Intent intent = new Intent();
            String packageName = getPackageName();
            PowerManager pm = (PowerManager) getSystemService(POWER_SERVICE);
            if (!pm.isIgnoringBatteryOptimizations(packageName)) {
                intent.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
                intent.setData(Uri.parse("package:" + packageName));
                startActivity(intent);
            }
        }
    }

    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    private void FirstTimeLogic() {
        final Boolean isFirstTime;
        SharedPreferences app_preferences = PreferenceManager
                .getDefaultSharedPreferences(IntroClass.this);
        SharedPreferences.Editor editor = app_preferences.edit();
        isFirstTime = app_preferences.getBoolean("isFirstTime", true);
        if (isFirstTime) {
            Initz();
            editor.putBoolean("isFirstTime", false);
            editor.commit();
        }
        else
        {
            startActivity(new Intent(getApplicationContext(),LandingActivity.class));
            this.finish();
        }
    }

    private void Initz() {
        Sheet1();
        Sheet2();
        Sheet3();
        List<AhoyOnboarderCard> pages = new ArrayList<>();
        pages.add(ahoyOnboarderCard1);
        pages.add(ahoyOnboarderCard2);
        pages.add(ahoyOnboarderCard3);
        setOnboardPages(pages);
        setImageBackground(R.drawable.intro_back);
        Typeface face = Typeface.createFromAsset(getAssets(), "Brandon.ttf");
        setFont(face);
        setFinishButtonTitle("Let's Stream");
    }

    private void Sheet3() {
        ahoyOnboarderCard3 = new AhoyOnboarderCard("Amazing Content", "Watch thousands of hit movies and TV series for free. Flixtube is 100% legal unlimited streaming app.", R.drawable.ic_popcorn);
        ahoyOnboarderCard3.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard3.setTitleColor(R.color.white);
        ahoyOnboarderCard3.setDescriptionColor(R.color.white);
        ahoyOnboarderCard3.setTitleTextSize(dpToPixels(14, this));
        ahoyOnboarderCard3.setDescriptionTextSize(dpToPixels(6, this));
        ahoyOnboarderCard3.setIconLayoutParams(400, 400, 100, 10, 10, 10);
    }

    private void Sheet2() {
        ahoyOnboarderCard2 = new AhoyOnboarderCard("Security", "??? Care to be aware! ???\nWe provide top notch content with end to end encryption.", R.drawable.ic_secure);
        ahoyOnboarderCard2.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard2.setTitleColor(R.color.white);
        ahoyOnboarderCard2.setDescriptionColor(R.color.white);
        ahoyOnboarderCard2.setTitleTextSize(dpToPixels(14, this));
        ahoyOnboarderCard2.setDescriptionTextSize(dpToPixels(6, this));
        ahoyOnboarderCard2.setIconLayoutParams(400, 400, 100, 10, 10, 10);
    }

    private void Sheet1() {
        ahoyOnboarderCard1 = new AhoyOnboarderCard("Flixtube", "Flixtube is one of the largest internet TV and Movies provider in the world.", R.drawable.ic_flixtube);
        ahoyOnboarderCard1.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard1.setTitleColor(R.color.white);
        ahoyOnboarderCard1.setDescriptionColor(R.color.white);
        ahoyOnboarderCard1.setTitleTextSize(dpToPixels(14, this));
        ahoyOnboarderCard1.setDescriptionTextSize(dpToPixels(6, this));
        ahoyOnboarderCard1.setIconLayoutParams(400, 400, 100, 10, 10, 10);
    }

    @Override
    public void onFinishButtonPressed() {
        startActivity(new Intent(IntroClass.this,LandingActivity.class));
    }
}