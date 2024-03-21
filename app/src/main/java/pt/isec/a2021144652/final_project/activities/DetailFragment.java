package pt.isec.a2021144652.final_project.activities;

import android.os.Bundle;
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

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import pt.isec.a2021144652.final_project.R;
import pt.isec.a2021144652.final_project.adapter.TypeAdapter;
import pt.isec.a2021144652.final_project.models.ApiService;
import pt.isec.a2021144652.final_project.models.Pokemon;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class DetailFragment extends Fragment {
    RecyclerView rvTypes;
    TypeAdapter typeAdapter;
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
        rvTypes = view.findViewById(R.id.rvTypes);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvTypes.setLayoutManager(layoutManager);

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
