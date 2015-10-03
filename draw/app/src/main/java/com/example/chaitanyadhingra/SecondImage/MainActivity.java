package com.example.chaitanyadhingra.SecondImage;
import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    ListView mDrawerList,mSettingList;
    RelativeLayout mDrawerPane;
    int mSelectedItem;

   // private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    ArrayList<NavItem> mNavItems = new ArrayList<NavItem>();
    ArrayList<NavItem> mSettingItem=new ArrayList<NavItem>();

    class NavItem {
        String mTitle;
        String mSubtitle;
        int mIcon;

        public NavItem(String title, String subtitle, int icon) {
            mTitle = title;
            mSubtitle = subtitle;
            mIcon = icon;
        }
    }

    class DrawerListAdapter extends BaseAdapter{

        Context mContext;
        ArrayList<NavItem> mNavItems;

        public DrawerListAdapter(Context context, ArrayList<NavItem> navItems) {
            mContext = context;
            mNavItems = navItems;
        }

        @Override
        public int getCount() {
            return mNavItems.size();
        }

        @Override
        public Object getItem(int position) {
            return mNavItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.drawer_item, null);
            }
            else {
                view = convertView;
            }

            TextView titleView = (TextView) view.findViewById(R.id.title_item);
            TextView subtitleView = (TextView) view.findViewById(R.id.counter_item);
            ImageView iconView = (ImageView) view.findViewById(R.id.icon_item);

            titleView.setText( mNavItems.get(position).mTitle );
            subtitleView.setText( mNavItems.get(position).mSubtitle );
            iconView.setImageResource(mNavItems.get(position).mIcon);


            return view;
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNavItems.add(new NavItem("Messages", "16", R.drawable.messages));
        mNavItems.add(new NavItem("Photo", "54", R.drawable.photo));
        mNavItems.add(new NavItem("Friends", "192", R.drawable.friend));
        mNavItems.add(new NavItem("Music", "", R.drawable.music_pic));
        mNavItems.add(new NavItem("Notifications", "18", R.drawable.notification));

        mSettingItem.add(new NavItem("Settings","",R.drawable.setting));

        mDrawerPane = (RelativeLayout) findViewById(R.id.drawerPane);
        mDrawerList = (ListView) findViewById(R.id.navList);

        mSettingList=(ListView)findViewById(R.id.navListSetting);

        DrawerListAdapter adapter = new DrawerListAdapter(this, mNavItems);
        mDrawerList.setAdapter(adapter);


        DrawerListAdapter adapter2=new DrawerListAdapter(this,mSettingItem);
        mSettingList.setAdapter(adapter2);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mSelectedItem = position;
                String text="Option is chosen";
                Toast.makeText(MainActivity.this,
                        text, Toast.LENGTH_LONG).show();
              //  view.setBackgroundColor(0xff000000 + Integer.parseInt(13369395+"",16));

                /*
                for (int ctr=0;ctr<=mDrawerList.getCount();ctr++){
                    if(position==ctr){
                        mDrawerList.getChildAt(position).setBackgroundColor(Color.CYAN);
                    }else{
                        mDrawerList.getChildAt(position).setBackgroundColor(Color.YELLOW);
                    }
                }
                */
                //mDrawerList.getChildAt(position).setBackgroundColor(Color.YELLOW);

                //mDrawerList.getChildAt(mDrawerList.getSelectedItemPosition()).setBackgroundColor(Color.RED);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
