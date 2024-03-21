package pt.isec.a2021144652.final_project.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import pt.isec.a2021144652.final_project.R;
import pt.isec.a2021144652.final_project.activities.MainFragment;
import pt.isec.a2021144652.final_project.models.PokemonList;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolder>{
    private ArrayList<PokemonList> pokemons;
    private ItemClicked activity;

    public interface ItemClicked {
        void onItemClicked(int position);
    }

    public PokemonAdapter (MainFragment context, ArrayList<PokemonList> pokemons){
        this.pokemons = pokemons;
        activity = (ItemClicked) context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView ivPokemonImage;
        TextView tvPokemonName;
        public ViewHolder(@NonNull View parent) {
            super(parent);

            ivPokemonImage = parent.findViewById(R.id.ivPokemonImage);
            tvPokemonName = parent.findViewById(R.id.tvPokemonName);

            parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    activity.onItemClicked(pokemons.indexOf((PokemonList) v.getTag()));
                }
            });
        }
    }
    @NonNull
    @Override
    public PokemonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonAdapter.ViewHolder holder, int position) {

        holder.itemView.setTag(pokemons.get(position));

        holder.tvPokemonName.setText(pokemons.get(position).getName());

        String url = pokemons.get(position).getUrl();
        String[] parts = url.split("/");
        String id = parts[parts.length - 1];
        String imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/" + id + ".png";;
        Glide.with(holder.itemView.getContext())
                .load(imageUrl)
                .into(holder.ivPokemonImage);

    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }
}
