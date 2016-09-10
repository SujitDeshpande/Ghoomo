package com.ghoomo.ghoomo.ui.userinterface;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ghoomo.ghoomo.R;
import com.ghoomo.ghoomo.ui.adapter.GridAdapter;
import com.ghoomo.ghoomo.ui.model.GridItemObject;

import java.util.ArrayList;

public class LandingGridActivity extends BaseFragmentActivity {

    private GridLayoutManager lLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_grid);

        setActionBar(R.string.app_name, 0, "", false, false);

        initUiComponenets();
    }

    /**
     * initialize ui componnets
     */
    private void initUiComponenets() {
        ArrayList<GridItemObject> gridItemObjectArrayList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            GridItemObject gridItemObject = new GridItemObject();
            gridItemObject.setSource("");
            gridItemObject.setDestination("");
            gridItemObjectArrayList.add(gridItemObject);
        }

        lLayout = new GridLayoutManager(LandingGridActivity.this, 2);

        RecyclerView rView = (RecyclerView) findViewById(R.id.recycler_view);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);

        GridAdapter rcAdapter = new GridAdapter(LandingGridActivity.this, gridItemObjectArrayList);
        rView.setAdapter(rcAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.create_new_trip_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click action
                Intent intent = new Intent(LandingGridActivity.this, CreateNewTripActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDialogButtonClick(DialogInterface dialog, int which, int alertCode) {

    }
}
