
package com.example.minimallauncher;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.*;

public class MainActivity extends Activity {
    private final int bg = Color.rgb(16, 16, 16);

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        showLauncher();
    }

    @Override
    protected void onResume() {
        super.onResume();
        showLauncher();
    }

    private int dp(int n) {
        return (int)(n * getResources().getDisplayMetrics().density + 0.5f);
    }

    private void showLauncher() {
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setBackgroundColor(bg);
        root.setPadding(dp(32), dp(20), dp(32), dp(20));

        TextView title = new TextView(this);
        title.setText("MY TV");
        title.setTextColor(Color.WHITE);
        title.setTextSize(30);
        title.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        root.addView(title);

        TextView subtitle = new TextView(this);
        subtitle.setText("Choose an app");
        subtitle.setTextColor(Color.LTGRAY);
        subtitle.setTextSize(16);
        LinearLayout.LayoutParams sp = new LinearLayout.LayoutParams(-1, -2);
        sp.bottomMargin = dp(14);
        root.addView(subtitle, sp);

        ScrollView scroll = new ScrollView(this);
        LinearLayout list = new LinearLayout(this);
        list.setOrientation(LinearLayout.VERTICAL);

        PackageManager pm = getPackageManager();
        Map<String, ResolveInfo> apps = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

        Intent tv = new Intent(Intent.ACTION_MAIN);
        tv.addCategory("android.intent.category.LEANBACK_LAUNCHER");
        for (ResolveInfo r : pm.queryIntentActivities(tv, 0)) {
            if (r.activityInfo != null) apps.put(
                r.loadLabel(pm).toString(), r);
        }

        Intent normal = new Intent(Intent.ACTION_MAIN);
        normal.addCategory(Intent.CATEGORY_LAUNCHER);
        for (ResolveInfo r : pm.queryIntentActivities(normal, 0)) {
            if (r.activityInfo != null) apps.put(
                r.loadLabel(pm).toString(), r);
        }

        for (ResolveInfo app : apps.values()) {
            final String pkg = app.activityInfo.packageName;
            if (pkg.equals(getPackageName())) continue;

            Button b = new Button(this);
            b.setText(app.loadLabel(pm));
            b.setTextColor(Color.WHITE);
            b.setTextSize(18);
            b.setAllCaps(false);
            b.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
            b.setBackgroundColor(Color.rgb(43, 43, 43));
            b.setFocusable(true);

            LinearLayout.LayoutParams p =
                new LinearLayout.LayoutParams(-1, dp(58));
            p.bottomMargin = dp(8);
            list.addView(b, p);

            b.setOnClickListener(v -> {
                try {
                    Intent launch = pm.getLaunchIntentForPackage(pkg);
                    if (launch != null) {
                        startActivity(launch);
                    } else {
                        Toast.makeText(this, "Cannot open this app",
                            Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(this, "Could not open app",
                        Toast.LENGTH_SHORT).show();
                }
            });
        }

        scroll.addView(list);
        root.addView(scroll, new LinearLayout.LayoutParams(-1, 0, 1));
        setContentView(root);
    }
}
