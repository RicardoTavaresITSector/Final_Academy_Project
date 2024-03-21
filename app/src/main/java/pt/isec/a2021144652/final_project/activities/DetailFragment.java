package pt.isec.a2021144652.final_project.activities;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
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
import pt.isec.a2021144652.final_project.models.ApiService;
import pt.isec.a2021144652.final_project.models.Pokemon;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class DetailFragment extends Fragment {
    LinearLayout llTypes;
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
        llTypes = view.findViewById(R.id.llTypes);
        llTypes.removeAllViews();

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
                            for (String type : pokemon.getTypes()) {
                                ImageView imageView = new ImageView(getContext());
                                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                                        ViewGroup.LayoutParams.WRAP_CONTENT,
                                        ViewGroup.LayoutParams.WRAP_CONTENT
                                );
                                layoutParams.setMargins(0, 0, 2, 0);
                                imageView.setLayoutParams(layoutParams);

                                int drawableResId = getResourceIdForType(type);
                                if (drawableResId != 0) {
                                    imageView.setImageResource(drawableResId);
                                    llTypes.addView(imageView);
                                }
                            }

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

    private int getResourceIdForType(String type) {
        switch (type) {
            case "normal":
                return R.drawable.type_normal;
            case "bug":
                return R.drawable.type_bug;
            case "fighting":
                return R.drawable.type_fighting;
            case "flying":
                return R.drawable.type_flying;
            case "ghost":
                return R.drawable.type_ghost;
            case "poison":
                return R.drawable.type_poison;
            case "ground":
                return R.drawable.type_ground;
            case "rock":
                return R.drawable.type_rock;
            case "steel":
                return R.drawable.type_steel;
            case "fire":
                return R.drawable.type_fire;
            case "water":
                return R.drawable.type_water;
            case "grass":
                return R.drawable.type_grass;
            case "electric":
                return R.drawable.type_electric;
            case "psychic":
                return R.drawable.type_psychic;
            case "ice":
                return R.drawable.type_ice;
            case "dragon":
                return R.drawable.type_dragon;
            case "dark":
                return R.drawable.type_dark;
            case "fairy":
                return R.drawable.type_fairy;
            case "shadow":
                return R.drawable.type_shadow;
            default:
                return R.drawable.pokeball;
        }
    }
}
