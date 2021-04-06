package com.rasyidf.kontakku;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.microsoft.fluentui.persona.AvatarView;
import com.microsoft.fluentui.popupmenu.PopupMenu;
import com.microsoft.fluentui.popupmenu.PopupMenuItem;
import com.microsoft.fluentui.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>
        implements Filterable {
    private Context context;
    private List<KontakItem> contactList;
    private List<KontakItem> contactListFiltered;
    private ContactsAdapterListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, phone;
        public AvatarView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            phone = view.findViewById(R.id.phone);
            thumbnail = view.findViewById(R.id.thumbnail);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onContactSelected(contactListFiltered.get(getAdapterPosition()));
                }
            });

            PopupMenuItem[] popupMenuItems = new PopupMenuItem[]{
                    new PopupMenuItem(R.id.mnuViewData, "Lihat Data", null, false, true ),
                    new PopupMenuItem(R.id.mnuViewNumber, "Lihat Nomor")
            };

            ArrayList<PopupMenuItem> list = new ArrayList<>();
            Collections.addAll(list, popupMenuItems);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    KontakItem kontakItem = contactListFiltered.get(getAdapterPosition());
                    PopupMenu popup = new PopupMenu(view.getContext(), v, list, PopupMenu.ItemCheckableBehavior.NONE);

                    popup.setOnItemClickListener(popupMenuItem -> {
                        switch (popupMenuItem.getId()) {

                            case R.id.mnuViewData:
                                Intent i = new Intent(view.getContext(), ContactDetailActivity.class);
                                Bundle b = new Bundle();
                                b.putString("nama", kontakItem.getName());
                                b.putString("nomor", kontakItem.getPhone());
                                i.putExtras(b);
                                startActivity(view.getContext(),i,b);
                                break;
                            case R.id.mnuViewNumber:
                                Toast.makeText(view.getContext(), "Nomor :" +   kontakItem.getPhone(), Toast.LENGTH_LONG).show();
                                break;
                            default:
                                break;
                        }
                    });
                    // here you can inflate your menu
                    popup.setDropDownGravity(Gravity.RIGHT);
                    popup.show();

                }
            });
        }
    }


    public CustomAdapter(Context context, List<KontakItem> contactList, ContactsAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.contactList = contactList;
        this.contactListFiltered = contactList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.kontak_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final KontakItem contact = contactListFiltered.get(position);
        holder.name.setText(contact.getName());
        holder.phone.setText(contact.getPhone());
        holder.thumbnail.setName(contact.getName());
    }

    @Override
    public int getItemCount() {
        return contactListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    contactListFiltered = contactList;
                } else {
                    List<KontakItem> filteredList = new ArrayList<>();
                    for (KontakItem row : contactList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase()) || row.getPhone().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    contactListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = contactListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                contactListFiltered = (ArrayList<KontakItem>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface ContactsAdapterListener {
        void onContactSelected(KontakItem contact);
    }
}