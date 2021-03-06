package com.example.easyxe.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.easyxe.models.Product;
import com.example.easyxe.R;

import java.util.List;

public class CustomListAdapter extends BaseAdapter {

    List<Product> productList;
    LayoutInflater layoutInflater;
    Context context;

    public CustomListAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
        this.layoutInflater = LayoutInflater.from(context);

    }



    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {
        ImageView product_thumbnail;
        TextView name;
        TextView price;
        TextView location;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_layout, null);
            holder = new ViewHolder();
            holder.product_thumbnail = (ImageView) convertView.findViewById(R.id.product_thumbnail);
            holder.name = (TextView) convertView.findViewById(R.id.product_name);
            holder.price = (TextView) convertView.findViewById(R.id.product_price);
            holder.location = (TextView) convertView.findViewById(R.id.product_location);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Product product = this.productList.get(position);
        holder.name.setText(product.getName());
        holder.price.setText(product.getPrice() + " đ");
        holder.location.setText(product.getLocation());
        holder.product_thumbnail.setImageResource(product.getImage());
        //int imageId = this.getMipmapResIdByName(product.image);

        //holder.product_thumbnail.setImageResource(imageId);

        return convertView;
    }

    // Tìm ID của Image ứng với tên của ảnh (Trong thư mục mipmap).
    public int getMipmapResIdByName(String resName)  {
        String pkgName = context.getPackageName();

        // Trả về 0 nếu không tìm thấy.
        int resID = context.getResources().getIdentifier(resName , "mipmap", pkgName);
        Log.i("CustomListView", "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }
}
