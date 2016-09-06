package com.nikkay.canedo.shoppingcartappmidterm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.nikkay.canedo.shoppingcartappmidterm.R;
import com.nikkay.canedo.shoppingcartappmidterm.ShoppingListScreen;
import com.nikkay.canedo.shoppingcartappmidterm.model.ShoppingList;

import java.util.List;

/**
 * Created by NIKKAY on 9/6/2016.
 */
public class ShoppingListAdapter extends ArrayAdapter<ShoppingList> {

    private Context context;
    private int rsrclayoutID;
    private List<ShoppingList> shopList;

    public ShoppingListAdapter(Context context, int resource, List<ShoppingList> shoppedObjects) {
        super(context, resource, shoppedObjects);

        this.context = context;
        rsrclayoutID = resource;
        shopList = shoppedObjects;
    }

    private class ViewHolder{

        TextView tvItem;
        TextView tvQuantity;
        TextView tvPrice;
        TextView tvTotal;


        public ViewHolder(View view){
            tvItem = (TextView) view.findViewById(R.id.ItemName);
            tvQuantity = (TextView) view.findViewById(R.id.Quantity);
            tvPrice = (TextView) view.findViewById(R.id.Price);
            tvTotal = (TextView) view.findViewById(R.id.TotalPrice);

        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(rsrclayoutID, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();

        }


        ShoppingList shop = shopList.get(position);
        if (shop!=null) {
            if (holder.tvItem != null){
                holder.tvItem.setText("Item Name: "+ shop.getItemName()+"");
            }
            if (holder.tvQuantity !=null){
                holder.tvQuantity.setText("Quantity: "+ shop.getItemQuantity()+"");

            }
            if (holder.tvPrice !=null){
                holder.tvPrice.setText("Item Price: "+ shop.getItemPrice()+"");

            }
            if (holder.tvTotal !=null){
                holder.tvTotal.setText("Total Purchased Item Price: "+ shop.getItemTotalPrice()+"");

            }


        }

        return convertView;
    }

}
