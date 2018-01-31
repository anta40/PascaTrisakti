package net.devbyzero.app.pascatrisakti;

import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.graphics.Color;
import android.util.TypedValue;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter;
import com.google.firebase.messaging.FirebaseMessaging;

import net.devbyzero.app.pascatrisakti.fragment.ContactFragment;
import net.devbyzero.app.pascatrisakti.fragment.KalenderFragment;
import net.devbyzero.app.pascatrisakti.fragment.NewsFragment;
import net.devbyzero.app.pascatrisakti.util.NotificationConfig;
import net.devbyzero.app.pascatrisakti.util.NotificationUtil;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Context mContext;
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private AHBottomNavigation bottomNavigation;
    private AHBottomNavigationAdapter bottomAdapter;

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.SHOW_WHEN_ACTIVE);
        bottomAdapter = new AHBottomNavigationAdapter(this, R.menu.bottom_menu);
        int[] tabColors = getResources().getIntArray(R.array.tab_colors);
        bottomAdapter.setupWithBottomNavigation(bottomNavigation, tabColors);

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {

                FragmentManager fragManager = getSupportFragmentManager();
                FragmentTransaction transaction;

                switch (position){
                    case 0:
                        NewsFragment fragment = new NewsFragment();
                        transaction = fragManager.beginTransaction();
                        transaction.replace(R.id.main, fragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                        break;

                    case 1:
                        KalenderFragment fragment2 = new KalenderFragment();
                        transaction = fragManager.beginTransaction();
                        transaction.replace(R.id.main, fragment2);
                        transaction.addToBackStack(null);
                        transaction.commit();
                        break;

                    case 2:
                        ContactFragment fragment3 = new ContactFragment();
                        transaction = fragManager.beginTransaction();
                        transaction.replace(R.id.main, fragment3);
                        transaction.addToBackStack(null);
                        transaction.commit();
                        break;
                }
                return true;
            }
        });

        mContext = getApplicationContext();

        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // checking for type intent filter
                if (intent.getAction().equals(NotificationConfig.REGISTRATION_COMPLETE)) {
                    // gcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    FirebaseMessaging.getInstance().subscribeToTopic(NotificationConfig.TOPIC_GLOBAL);

                  //  displayFirebaseRegId();

                } else if (intent.getAction().equals(NotificationConfig.PUSH_NOTIFICATION)) {
                    // new push notification is received

                    String message = intent.getStringExtra("message");

                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

                    //txtMessage.setText(message);
                }
            }
        };

 //       displayFirebaseRegId();

        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        String fromMenu = getIntent().getStringExtra("menu");

        if (fromMenu != null){

        }
        else {
            tx.replace(R.id.main,
                    Fragment.instantiate(MainActivity.this, "net.devbyzero.app.pascatrisakti.fragment.NewsFragment"));
            tx.addToBackStack(null);
            tx.commit();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
           // super.onBackPressed();
            if(String.valueOf(getSupportFragmentManager().getBackStackEntryCount()).equals("1")){
                menuExit();
            }else{

                FragmentManager fm = getSupportFragmentManager();
                for (int x = 1; x < fm.getBackStackEntryCount(); x++){
                    fm.popBackStack();
                }
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // register GCM registration complete receiver
        LocalBroadcastManager.getInstance(mContext).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(NotificationConfig.REGISTRATION_COMPLETE));

        // register new push message receiver
        // by doing this, the activity will be notified each time a new message arrives
        LocalBroadcastManager.getInstance(mContext).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(NotificationConfig.PUSH_NOTIFICATION));

        // clear the notification area when the app is opened
        NotificationUtil.clearNotifications(getApplicationContext());
    }

    // Fetches reg id from shared preferences
    // and displays on the screen
    private void displayFirebaseRegId() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(NotificationConfig.SHARED_PREF, 0);
        String regId = pref.getString("regId", null);

        //Log.e(TAG, "Firebase reg id: " + regId);
        Log.e(TAG, "Firebase reg ID: "+regId);

        if (!TextUtils.isEmpty(regId))
            Toast.makeText(getApplicationContext(), "Firebase Reg Id: " + regId, Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(), "Firebase Reg Id is not received yet!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(mContext).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       // if (id == R.id.action_settings) {
       //     return true;
       // }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home){
               FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
            tx.replace(R.id.main,
                    Fragment.instantiate(MainActivity.this, "net.devbyzero.app.pascatrisakti.fragment.NewsFragment"));
            tx.addToBackStack(null);
            tx.commit();
         //   tx.addToBackStack(null);
        }
        else if (id == R.id.nav_pengantar){
            FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
            tx.replace(R.id.main,
                    Fragment.instantiate(MainActivity.this, "net.devbyzero.app.pascatrisakti.fragment.PengantarFragment"));
            tx.addToBackStack(null);
            tx.commit();
          //  tx.addToBackStack(null);
        }
        else if (id == R.id.nav_fasilitas){
            FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
            tx.replace(R.id.main,
                    Fragment.instantiate(MainActivity.this, "net.devbyzero.app.pascatrisakti.fragment.FasilitasFragment"));
            tx.addToBackStack(null);
            tx.commit();
       }
        else if (id == R.id.nav_sejarah){
            FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
            tx.replace(R.id.main,
                    Fragment.instantiate(MainActivity.this, "net.devbyzero.app.pascatrisakti.fragment.SejarahFragment"));
            tx.addToBackStack(null);
            tx.commit();
          //  tx.addToBackStack(null);
        }
        else if (id == R.id.nav_visi_misi_tujuan){
            FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
            tx.replace(R.id.main,
                    Fragment.instantiate(MainActivity.this, "net.devbyzero.app.pascatrisakti.fragment.VisiFragment"));
            tx.addToBackStack(null);
            tx.commit();
        //    tx.addToBackStack(null);
        }
        else if (id == R.id.nav_pendaftaran){
            FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
            tx.replace(R.id.main,
                    Fragment.instantiate(MainActivity.this, "net.devbyzero.app.pascatrisakti.fragment.PendaftaranFragment"));
            tx.addToBackStack(null);
            tx.commit();
          //  tx.addToBackStack(null);
        }
        else if (id == R.id.nav_faq){
            FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
            tx.replace(R.id.main,
                    Fragment.instantiate(MainActivity.this, "net.devbyzero.app.pascatrisakti.fragment.FAQFragment"));
            tx.addToBackStack(null);
            tx.commit();
           // tx.addToBackStack(null);
        }
        else if (id == R.id.nav_peta){
            FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
            tx.replace(R.id.main,
                    Fragment.instantiate(MainActivity.this, "net.devbyzero.app.pascatrisakti.fragment.PetaFragment"));
            tx.addToBackStack(null);
            tx.commit();
           // tx.addToBackStack(null);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void menuExit(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Pasca Trisakti");
        //builder.setIcon(R.drawable.ic_your_logo);
        builder.setMessage("Anda yakin ingin keluar?");
        builder.setPositiveButton("Batal",
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        dialog.cancel();
                    }
                });

        builder.setNegativeButton("Keluar",
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        System.exit(0);
                        finish();
                    }
                });

        builder.show();
    }
}
