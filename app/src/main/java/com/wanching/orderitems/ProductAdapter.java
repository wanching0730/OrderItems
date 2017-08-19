package com.wanching.orderitems;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

/**
 * Created by WanChing on 19/8/2017.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder>{

    private Context context;
    private List<Product> listProducts;
    private DbQueries dbq;

    public ProductAdapter(Context context, List<Product> listProducts){
        this.context = context;
        this.listProducts = listProducts;
        dbq = new DbQueries(new DbHelper(context));
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_layout, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        final Product currentProduct = listProducts.get(position);
        holder.name.setText(currentProduct.getName());
        holder.editProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTaskDialog(currentProduct);
            }
        });

        holder.deleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbq.deleteProduct(currentProduct.getId());
                ((Activity)context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listProducts.size();
    }

    private void editTaskDialog(final Product product){
        LayoutInflater inflater = LayoutInflater.from(context);
        View subView = inflater.inflate(R.layout.add_product_layout, null);
        final EditText nameField = (EditText) subView.findViewById(R.id.enter_name);
        final EditText quantityField = (EditText) subView.findViewById(R.id.enter_quantity);
        if(product != null){
            nameField.setText(product.getName());
            quantityField.setText(String.valueOf(product.getQuantity()));
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Edit Product")
                .setView(subView)
                .create();

        builder.setPositiveButton("Edit Product", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                final String name = nameField.getText().toString();
                final int quantity = Integer.parseInt(quantityField.getText().toString());
                if(TextUtils.isEmpty(name) || quantity <= 0){
                    Toast.makeText(context, "Something went wrong.", Toast.LENGTH_SHORT).show();
                }else {
                    dbq.updateProduct(new Product(product.getId(), name, quantity));
                    //Refresh activity
                    ((Activity)context).finish();
                    context.startActivity(((Activity)context).getIntent());
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(context, "Task cancelled", Toast.LENGTH_SHORT).show();
            }
        });

        builder.show();
    }
}

