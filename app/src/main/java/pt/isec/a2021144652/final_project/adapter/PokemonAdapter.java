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
    final int VIEW_TYPE_LOADING = 0;
    final int VIEW_TYPE_ITEM = 1;
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
        if(viewType == VIEW_TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
            return new ViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
            return new LoadingHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonAdapter.ViewHolder holder, int position) {
        if(holder instanceof LoadingHolder){
            return;
        } else {
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

    }

    @Override
    public int getItemViewType(int position) {
        if (position == pokemons.size() - 1 && (position + 1) % 151 == 0) {
            return VIEW_TYPE_LOADING; // Último elemento divisível por 151
        } else {
            return VIEW_TYPE_ITEM;
        }
    }

    private class LoadingHolder extends PokemonAdapter.ViewHolder {
        public LoadingHolder(View view) {
            super(view);
        }
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }
}
