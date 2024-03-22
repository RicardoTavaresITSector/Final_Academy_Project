package pt.isec.a2021144652.final_project.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import pt.isec.a2021144652.final_project.R;
import pt.isec.a2021144652.final_project.adapter.MovesAdapter;
import pt.isec.a2021144652.final_project.adapter.TypeAdapter;
import pt.isec.a2021144652.final_project.models.ApiService;
import pt.isec.a2021144652.final_project.models.Pokemon;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class DetailFragment extends Fragment {
    ImageView ivFavorite;
    Toolbar detailsToolbar;
    RecyclerView rvTypes;
    RecyclerView rvMoves;
    TypeAdapter typeAdapter;
    MovesAdapter moveAdapter;
    ImageView ivPokemonDetailImage;
    TextView tvDetailPokemonName;
    TextView tvWeight;
    TextView tvHeight;
    public DetailFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        ivPokemonDetailImage = view.findViewById(R.id.ivPokemonDetailImage);
        tvDetailPokemonName = view.findViewById(R.id.tvDetailPokemonName);
        tvWeight = view.findViewById(R.id.tvWeight);
        tvHeight = view.findViewById(R.id.tvHeight);
        detailsToolbar = view.findViewById(R.id.detailsToolbar);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(detailsToolbar);
        if (((AppCompatActivity) requireActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) requireActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        detailsToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().onBackPressed();
            }
        });
        ivFavorite = view.findViewById(R.id.ivFavorite);
        ivFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(), "Added to favorites", Toast.LENGTH_SHORT).show();
                if(ivFavorite.getColorFilter() == null)
                    ivFavorite.setColorFilter(getResources().getColor(R.color.Gold));
                else
                    ivFavorite.setColorFilter(null);
            }
        });
        rvTypes = view.findViewById(R.id.rvTypes);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvTypes.setLayoutManager(layoutManager);

        rvMoves = view.findViewById(R.id.rvMoves);
        LinearLayoutManager movesLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvMoves.setLayoutManager(movesLayoutManager);

        Bundle args = getArguments();
        if (args != null) {
            String pokemonId = args.getString("pokemon_id");

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://pokeapi.co/api/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ApiService apiService = retrofit.create(ApiService.class);
            Call<Pokemon> pokemonCall = apiService.getPokemon(pokemonId);
            pokemonCall.enqueue(new Callback<Pokemon>() {
                @Override
                public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                    if (response.isSuccessful()) {
                        Pokemon pokemon = response.body();
                        if(pokemon != null) {
                            tvDetailPokemonName.setText(pokemon.getName());
                            tvWeight.setText(pokemon.getWeight() + "kg");
                            tvHeight.setText(pokemon.getHeight() + "m");
                            List<String> types = pokemon.getTypes();
                            typeAdapter = new TypeAdapter(types);
                            rvTypes.setAdapter(typeAdapter);
                            List<String> moves = pokemon.getMoves();
                            moveAdapter = new MovesAdapter(moves);
                            rvMoves.setAdapter(moveAdapter);

                            String imageUrl = pokemon.getImg();
                            Glide.with(DetailFragment.this)
                                    .load(imageUrl)
                                    .into(ivPokemonDetailImage);

                        }
                    } else {
                        Toast.makeText(getContext(), "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Pokemon> call, Throwable t) {
                    Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        return view;
    }
}
