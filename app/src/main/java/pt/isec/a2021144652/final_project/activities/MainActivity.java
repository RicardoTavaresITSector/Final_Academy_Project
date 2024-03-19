package pt.isec.a2021144652.final_project.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import pt.isec.a2021144652.final_project.R;
import pt.isec.a2021144652.final_project.adapter.PokemonAdapter;
import pt.isec.a2021144652.final_project.models.ApiService;
import pt.isec.a2021144652.final_project.models.Pokemon;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements PokemonAdapter.ItemClicked {
    RecyclerView rvPokemons;
    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Pokemon> pokemons;
    TextView tvTest;
    ImageView ivTest;
    private static final String LIMIT = "151";
    private static final String OFFSET = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvPokemons = findViewById(R.id.rvPokemonList);
        rvPokemons.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        rvPokemons.setLayoutManager(layoutManager);

        pokemons = new ArrayList<>();
        pokemons.add(new Pokemon("Bulbasaur", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"));
        pokemons.add(new Pokemon("Ivysaur", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/2.png"));
        pokemons.add(new Pokemon("Venusaur", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/3.png"));
        pokemons.add(new Pokemon("Charmander", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/4.png"));

        myAdapter = new PokemonAdapter(this, pokemons);
        rvPokemons.setAdapter(myAdapter);
    }

    @Override
    public void onItemClicked(int position) {

        Toast.makeText(this, pokemons.get(position).getName(), Toast.LENGTH_SHORT).show();

    }
}