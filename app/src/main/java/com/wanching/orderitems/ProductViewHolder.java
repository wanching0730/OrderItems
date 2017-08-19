package com.wanching.orderitems;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by WanChing on 19/8/2017.
 */

public class ProductViewHolder extends RecyclerView.ViewHolder{

    public TextView name;
    public ImageView deleteProduct;
    public ImageView editProduct;

    public ProductViewHolder (View view){
        super(view);

        name = (TextView) view.findViewById(R.id.product_name);
        deleteProduct = (ImageView)view.findViewById(R.id.delete_product);
        editProduct = (ImageView)view.findViewById(R.id.edit_product);
    }

}
