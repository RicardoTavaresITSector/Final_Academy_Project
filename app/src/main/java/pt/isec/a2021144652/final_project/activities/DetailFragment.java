package pt.isec.a2021144652.final_project.activities;

import static kotlinx.coroutines.CoroutineScopeKt.CoroutineScope;
import static pt.isec.a2021144652.final_project.MyApplication.appDatabase;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
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
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;

import pt.isec.a2021144652.final_project.R;
import pt.isec.a2021144652.final_project.adapter.MovesAdapter;
import pt.isec.a2021144652.final_project.adapter.TypeAdapter;
import pt.isec.a2021144652.final_project.models.ApiService;
import pt.isec.a2021144652.final_project.models.FavoritePokemon;
import pt.isec.a2021144652.final_project.models.Pokemon;
import pt.isec.a2021144652.final_project.room.FavoritePokemonDao;
import pt.isec.a2021144652.final_project.room.PokemonViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import pt.isec.a2021144652.final_project.room.AppDatabase;

public class DetailFragment extends Fragment {
    Pokemon auxPokemon;
    private PokemonViewModel pokemonViewModel;
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
        pokemonViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication())).get(PokemonViewModel.class);
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
        ivFavorite = view.findViewById(R.id.ivFavorite);
        rvTypes = view.findViewById(R.id.rvTypes);
        rvMoves = view.findViewById(R.id.rvMoves);
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
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvTypes.setLayoutManager(layoutManager);
        LinearLayoutManager movesLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvMoves.setLayoutManager(movesLayoutManager);

        Bundle args = getArguments();
        if (args != null) {
            String pokemonId = args.getString("pokemon_id");
            loadPokemonDetails(pokemonId);
            verifyIfPokemonIsFavorite(pokemonId);
        }

        return view;
    }

    private void loadPokemonDetails(String pokemonId) {
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
                    auxPokemon = response.body();
                    if(auxPokemon != null) {
                        tvDetailPokemonName.setText(auxPokemon.getName());
                        tvWeight.setText(auxPokemon.getWeight() + "kg");
                        tvHeight.setText(auxPokemon.getHeight() + "m");
                        List<String> types = auxPokemon.getTypes();
                        typeAdapter = new TypeAdapter(types);
                        rvTypes.setAdapter(typeAdapter);
                        List<String> moves = auxPokemon.getMoves();
                        moveAdapter = new MovesAdapter(moves);
                        rvMoves.setAdapter(moveAdapter);

                        String imageUrl = auxPokemon.getImg();
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

    private void verifyIfPokemonIsFavorite(String pokemonId) {
        pokemonViewModel.getPokemonById(pokemonId).observe(getViewLifecycleOwner(), pokemon -> {
            if (pokemon != null) {
                ivFavorite.setColorFilter(getResources().getColor(R.color.Gold));
            } else {
                ivFavorite.setColorFilter(null);
            }

            ivFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (pokemon != null) {
                        pokemonViewModel.removePokemonFromFavorites(Integer.parseInt(pokemonId));
                        ivFavorite.setColorFilter(null);
                        Toast.makeText(getContext(), "Removed from favorites", Toast.LENGTH_SHORT).show();
                    } else {
                        // Adicionar o Pokémon à base de dados
                        String pokemonName = auxPokemon.getName();
                        String imageUrl = auxPokemon.getImg();
                        String height = auxPokemon.getHeight();
                        String weight = auxPokemon.getWeight();
                        List<String> types = auxPokemon.getTypes();
                        List<String> moves = auxPokemon.getMoves();

                        FavoritePokemon favoritePokemon = new FavoritePokemon(Integer.parseInt(pokemonId), pokemonName, imageUrl, height, weight, types, moves);
                        pokemonViewModel.addPokemonToFavorites(favoritePokemon);
                        ivFavorite.setColorFilter(getResources().getColor(R.color.Gold));
                        Toast.makeText(getContext(), "Added to favorites", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });
    }
}
