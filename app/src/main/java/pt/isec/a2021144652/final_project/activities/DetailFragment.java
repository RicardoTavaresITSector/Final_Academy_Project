package pt.isec.a2021144652.final_project.activities;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import pt.isec.a2021144652.final_project.R;
import pt.isec.a2021144652.final_project.models.ApiService;
import pt.isec.a2021144652.final_project.models.Pokemon;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class DetailFragment extends Fragment {

    ImageView ivPokemonDetailImage;
    TextView tvDetailPokemonName;
    TextView tvWeight;
    TextView tvHeight;
    TextView tvTypes;

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        ivPokemonDetailImage = view.findViewById(R.id.ivPokemonDetailImage);
        tvDetailPokemonName = view.findViewById(R.id.tvDetailPokemonName);
        tvWeight = view.findViewById(R.id.tvWeight);
        tvHeight = view.findViewById(R.id.tvHeight);
        tvTypes = view.findViewById(R.id.tvTypes);

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
                            tvTypes.setText(pokemon.getTypes().toString());

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