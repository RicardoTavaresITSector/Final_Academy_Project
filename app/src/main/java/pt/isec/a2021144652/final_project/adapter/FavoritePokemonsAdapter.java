package pt.isec.a2021144652.final_project.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import pt.isec.a2021144652.final_project.R;
import pt.isec.a2021144652.final_project.activities.MainFragment;
import pt.isec.a2021144652.final_project.models.FavoritePokemon;
import pt.isec.a2021144652.final_project.models.PokemonList;
import pt.isec.a2021144652.final_project.room.PokemonViewModel;

public class FavoritePokemonsAdapter extends RecyclerView.Adapter<FavoritePokemonsAdapter.FavoritePokemonViewHolder>{
    private ArrayList<FavoritePokemon> pokemons;
    private PokemonViewModel viewModel;

    public FavoritePokemonsAdapter(List<FavoritePokemon> pokemons, PokemonViewModel viewModel) {
        this.pokemons = (ArrayList<FavoritePokemon>) pokemons;
        this.viewModel = viewModel;
    }

    public void setPokemons(List<FavoritePokemon> pokemons) {
        this.pokemons = (ArrayList<FavoritePokemon>) pokemons;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public FavoritePokemonsAdapter.FavoritePokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_favorite_pokemons, parent, false);
        return new FavoritePokemonViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritePokemonsAdapter.FavoritePokemonViewHolder holder, int position) {
        FavoritePokemon pokemon = pokemons.get(position);
        holder.tvFavoritePokemonName.setText(pokemon.getName());
        Glide.with(holder.ivFavoritePokemonImage.getContext()).load(pokemon.getImg()).into(holder.ivFavoritePokemonImage);
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    public class FavoritePokemonViewHolder extends RecyclerView.ViewHolder {
        ImageView ivFavorite;
        ImageView ivFavoritePokemonImage;
        TextView tvFavoritePokemonName;

        public FavoritePokemonViewHolder(@NonNull View itemView) {
            super(itemView);
            ivFavorite = itemView.findViewById(R.id.ivFavorite);
            ivFavoritePokemonImage = itemView.findViewById(R.id.ivFavoritePokemonImage);
            tvFavoritePokemonName = itemView.findViewById(R.id.tvFavoritePokemonName);

            ivFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    FavoritePokemon pokemon = pokemons.get(position);
                    viewModel.removePokemonFromFavorites(pokemon.getId());
                }
            });
        }
    }
}
