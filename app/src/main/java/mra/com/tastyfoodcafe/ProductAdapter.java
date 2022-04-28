package mra.com.tastyfoodcafe;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.List;



public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>
{
    private Context mcontext;
    private List<Bakeryitemss> items;
    private DatabaseReference reference;
    private FirebaseAuth mAuth;
    Button Follow;
    int overtotal=0;
    public ProductAdapter(Context mcontext,List<Bakeryitemss> items)
    {
        this.mcontext=mcontext;
        this.items=items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mcontext).inflate(R.layout.cardmainproduct,parent,false);


        return new ProductAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Bakeryitemss b=items.get(position);
        holder.prductname.setText(b.getPname());

        if (b.getPimg().equals("default"))
        {
            holder.productImage.setImageResource(R.drawable.cart);
        }
        else
        {
            Glide.with(mcontext).load(b.getPimg()).into(holder.productImage);
        }


        //int productprize=((Integer.valueOf(b.getPrise())));

        //overtotal = overtotal + productprize;


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(mcontext,ProductActivity.class);
                i.putExtra("id",b.getId());
                i.putExtra("total",String.valueOf(overtotal));
                mcontext.startActivity(i);
            }
        });





    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {

        TextView prductname;
        ImageView productImage;

        public ViewHolder(View itemView) {
            super(itemView);

            prductname=itemView.findViewById(R.id.im);
            productImage=itemView.findViewById(R.id.productimage);


        }
    }
}

