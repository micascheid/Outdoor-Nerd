package edu.western.cs.outdoornerd;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.western.cs.outdoornerd.models.DataW;
import io.realm.Realm;
import io.realm.RealmResults;

public class QueryListActivity extends AppCompatActivity {

    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String,List<String>> listHash;
    private android.support.v7.widget.Toolbar toolbar;
    private ActionBar actionBar;
    private RealmResults<DataW> mRealmResults;
    private Realm mRealm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.query_list_expand);

//        toolbar = findViewById(R.id.my_toolbar);
//        toolbar.setTitle("Query Options");
//        setSupportActionBar(toolbar);
//        actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);



        mRealm = Realm.getDefaultInstance();
        mRealmResults = mRealm.where(DataW.class).findAll();
        Log.d("RESULTS", String.valueOf(mRealmResults.size()));





        listView = (ExpandableListView)findViewById(R.id.lvExp);
        initData();

        listAdapter = new ExpandableListAdapter(this,listDataHeader,listHash);
        listView.setAdapter(listAdapter);

    }

    private void initData() {

        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        listDataHeader.add("Time");
        listDataHeader.add("Temperature");
        listDataHeader.add("Wind");
        listDataHeader.add("Rain");
        listDataHeader.add("Snow Depth");
        listDataHeader.add("Soil Humidity");
        listDataHeader.add("Stream Depth");
        listDataHeader.add("Ground Temp");
        listDataHeader.add("Cloud Cover");


        List<String> time = new ArrayList<>();
        time.add("Time Select");

        List<String> temperature = new ArrayList<>();
        //temperature.add("Expandable ListView");


        List<String> wind = new ArrayList<>();
        //wind.add("wind Expandable ListView");

        List<String> rain = new ArrayList<>();
        //wind.add("rain Expandable ListView");

        listHash.put(listDataHeader.get(0),time);
        listHash.put(listDataHeader.get(1),temperature);
        listHash.put(listDataHeader.get(2),wind);
        listHash.put(listDataHeader.get(3),rain);

        Button query = findViewById(R.id.query);
        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QueryListActivity.this, ResultActivity.class);
                startActivity(intent);
            }
        });


    }
}
