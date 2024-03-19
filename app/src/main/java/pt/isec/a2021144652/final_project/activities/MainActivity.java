package pt.isec.a2021144652.final_project.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import pt.isec.a2021144652.final_project.R;
import pt.isec.a2021144652.final_project.adapter.PokemonAdapter;
import pt.isec.a2021144652.final_project.models.ApiService;
import pt.isec.a2021144652.final_project.models.Pokemon;
import pt.isec.a2021144652.final_project.models.PokemonResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements PokemonAdapter.ItemClicked {
    RecyclerView rvPokemons;
    RecyclerView.Adapter myAdapter;
    SearchView searchView;
    RecyclerView.LayoutManager layoutManager;
    List<Pokemon> pokemons;
    List<Pokemon> filteredPokemons; // Lista de pokemons filtrados
    TextView tvTest;
    ImageView ivTest;
    private static final String LIMIT = "151";
    private static final String OFFSET = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvPokemons = findViewById(R.id.rvPokemonList);
        searchView = findViewById(R.id.searchView);

        rvPokemons.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        rvPokemons.setLayoutManager(layoutManager);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<PokemonResponse> call = apiService.getPokemons(151);
        call.enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {
                if (response.isSuccessful()) {
                    PokemonResponse pokemonResponse = response.body();
                    if (pokemonResponse != null) {
                        pokemons = pokemonResponse.getResults();
                        filteredPokemons = new ArrayList<>(pokemons); // Inicializa a lista de pokemons filtrados
                        myAdapter = new PokemonAdapter(MainActivity.this, (ArrayList<Pokemon>) filteredPokemons);
                        rvPokemons.setAdapter(myAdapter);
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Erro ao carregar pokémons", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PokemonResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Erro ao carregar pokémons", Toast.LENGTH_SHORT).show();
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });
    }

    private void filter(String text) {
        filteredPokemons.clear();
        for (Pokemon pokemon : pokemons) {
            if (pokemon.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredPokemons.add(pokemon);
            }
        }
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClicked(int position) {
        Toast.makeText(this, filteredPokemons.get(position).getName(), Toast.LENGTH_SHORT).show();
    }
}