package com.rasyidf.kontakku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.ThemeUtils;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.PointerIcon;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.microsoft.fluentui.appbarlayout.AppBarLayout;
import com.microsoft.fluentui.search.Searchbar;
import com.microsoft.fluentui.util.ThemeUtil;
import com.microsoft.fluentui.util.ThemeUtilsKt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener,CustomAdapter.ContactsAdapterListener {
    Searchbar searchbar;
    AppBarLayout app_bar;


    protected RecyclerView.LayoutManager mLayoutManager;
    protected RecyclerView mRecyclerView;
    protected CustomAdapter mAdapter;

    List<KontakItem> listNama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_KontakKu);
        setContentView(R.layout.activity_main);
        initDB();
        initStatusbar();

        enumerateList();
        initList();

    }

    private void initDB() {
        // TODO : BESOK SAJA
        // DBHandler db = new DBHandler(this);



    }


    private void enumerateList() {
        KontakItem[] contacs =  new KontakItem[]{
                new KontakItem(1,"Ani", "085123456789"),
                new KontakItem(2,"Budi", "085123456789"),
                new KontakItem(3,"Caca", "085123456789"),
                new KontakItem(4,"Danu", "085123456789"),
                new KontakItem(5,"Ervan", "085123456789"),
                new KontakItem(6,"Fatimah", "085123456789"),
                new KontakItem(7,"Inayah", "085123456789"),
                new KontakItem(8,"Ilham", "085123456789"),
                new KontakItem(9,"Eris", "085123456789"),
                new KontakItem(10,"Gita", "085123456789"),
                new KontakItem(11,"Maul", "085123456789"),
                new KontakItem(12,"Fikri", "085123456789"),
                new KontakItem(13,"Vian", "085123456789"),
                new KontakItem(14,"Lutfi", "085123456789"),
                new KontakItem(15,"Sobari", "085123456789"),
                new KontakItem(16,"Hakim", "085123456789"),
                new KontakItem(17,"Tyas", "085123456789"),
                new KontakItem(18,"Rasyid", "085123456789"),
        };
        listNama = new ArrayList<>();
        for (KontakItem ki: contacs
        ) {
            this.listNama.add(ki);
        }
    }

    private void initList() {

        mRecyclerView = findViewById(R.id.lstKontak);

        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter= new CustomAdapter(getApplicationContext(), listNama, this);
        mRecyclerView.setAdapter(mAdapter);


        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ms_row_divider));
        mRecyclerView.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnuSettings:
                Toast.makeText(getApplicationContext(), R.string.app_name, Toast.LENGTH_LONG).show();
                break;
            case R.id.mnuAbout:
                AlertDialog.Builder adbs = new AlertDialog.Builder(this);
                adbs.setTitle(getResources().getString(R.string.app_name));
                adbs.setIcon(R.drawable.logo);
                adbs.setMessage(R.string.copyright);
                adbs.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog ad = adbs.create();
                ad.show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initStatusbar() {
        Drawable searchIcon = getDrawable(R.drawable.ms_ic_search_24_filled);
        app_bar = findViewById(R.id.app_bar);
        app_bar.setScrollBehavior(AppBarLayout.ScrollBehavior.COLLAPSE_TOOLBAR);
        searchbar = new Searchbar(this);
        searchbar.setOnQueryTextListener(this);
        searchbar.setActionMenuView(false);
        searchbar.setLeft(16);
        app_bar.getToolbar().setTitle("Kontak Ku");
        app_bar.setAccessoryView(searchbar);


    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        mAdapter.getFilter().filter(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        mAdapter.getFilter().filter(newText);
        return true;
    }

    @Override
    public void onContactSelected(KontakItem contact) {
    }
}