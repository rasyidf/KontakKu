package com.rasyidf.kontakku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.TextView;

import com.microsoft.fluentui.toolbar.Toolbar;

import static androidx.appcompat.widget.ThemeUtils.getThemeAttrColor;

public class ContactDetailActivity extends AppCompatActivity {

    Toolbar tb;
    TextView tNama, tNomor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        InitToolbar();

        tNama = findViewById(R.id.tvNama);
        tNomor = findViewById(R.id.tvNomor);

        Bundle bundle = getIntent().getExtras();
        String nama = bundle.getString("nama");
        String nomor = bundle.getString("nomor");

        tNama.setText(nama);
        tNomor.setText(nomor);

    }

    private void InitToolbar() {
        tb = findViewById(R.id.toolbar);
        Drawable backArrow = ContextCompat.getDrawable(this, R.drawable.ms_ic_arrow_left_24_filled_toolbar);
        backArrow.setTint(getColor(R.color.fluentui_white));
        if (backArrow != null){
            tb.setNavigationIcon(backArrow);
        }
        tb.setNavigationOnClickListener((v)->
        {
            onBackPressed();
        });
    }
}