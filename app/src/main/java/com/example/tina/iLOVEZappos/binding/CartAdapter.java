package com.example.tina.iLOVEZappos.binding;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
//        import android.widget.ImageView;
//        import android.widget.LinearLayout;
//        import android.widget.TextView;

import java.util.List;

import com.example.tina.iLOVEZappos.BR;
import com.example.tina.iLOVEZappos.R;
import com.example.tina.iLOVEZappos.activity.MainActivity4_cart;
import com.example.tina.iLOVEZappos.model.Product;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.BindingHolder> {

    static private List<Product> productList;
    private int rowLayout;
    private Context context;


    public static class BindingHolder extends RecyclerView.ViewHolder {
        //data-binding
        private ViewDataBinding binding;



        public BindingHolder(View v) {
            super(v);
            binding = DataBindingUtil.bind(v);


            final Product item = productList.get(getAdapterPosition());


            //delete item button
            final ImageView removeBtn = (ImageView) v.findViewById(R.id.remove);
            removeBtn.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.i("cart", "onClick: click!!!delete!");
                            MainActivity4_cart.remove(item);
                        }
                    }


            );


        }

        public ViewDataBinding getBinding(){
            return binding;
        }




    }

    public CartAdapter(List<Product> productList, int rowLayout, Context context) {
        this.productList = productList;
        this.rowLayout = rowLayout;
        this.context = context;
    }




    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent,
                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new BindingHolder(view);
    }


    @Override
    public void onBindViewHolder(BindingHolder holder, final int position) {

        Product p = productList.get(position);
        holder.getBinding().setVariable(BR.product, p);
        holder.getBinding().executePendingBindings();

    }




    @Override
    public int getItemCount() {
        return productList.size();
    }
}