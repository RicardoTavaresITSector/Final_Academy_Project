package pt.isec.a2021144652.final_project.activities;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.SearchView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import pt.isec.a2021144652.final_project.R;
import pt.isec.a2021144652.final_project.adapter.PokemonAdapter;
import pt.isec.a2021144652.final_project.models.ApiService;
import pt.isec.a2021144652.final_project.models.Pokemon;
import pt.isec.a2021144652.final_project.models.PokemonList;
import pt.isec.a2021144652.final_project.models.PokemonResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import pt.isec.a2021144652.final_project.R;

public class MainFragment extends Fragment implements PokemonAdapter.ItemClicked {
    RecyclerView rvPokemons;
    RecyclerView.Adapter myAdapter;
    SearchView searchView;
    RecyclerView.LayoutManager layoutManager;
    List<PokemonList> pokemons;
    List<PokemonList> filteredPokemons;
    private static final String LIMIT = "151";
    private static final String OFFSET = "1";

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        rvPokemons = view.findViewById(R.id.rvPokemonList);
        searchView = view.findViewById(R.id.searchView);

        rvPokemons.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
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
                        filteredPokemons = new ArrayList<>(pokemons);
                        myAdapter = new PokemonAdapter(MainFragment.this, (ArrayList<PokemonList>) filteredPokemons);
                        rvPokemons.setAdapter(myAdapter);
                    }
                } else {
                    Toast.makeText(getContext(), "Erro ao carregar pokémons", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PokemonResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Erro ao carregar pokémons", Toast.LENGTH_SHORT).show();
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

        return view;
    }

    private void filter(String text) {
        filteredPokemons.clear();
        for (PokemonList pokemon : pokemons) {
            if (pokemon.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredPokemons.add(pokemon);
            }
        }
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClicked(int position) {
        String url = filteredPokemons.get(position).getUrl();
        String[] parts = url.split("/");
        String id = parts[parts.length - 1];

        DetailFragment detailFragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString("pokemon_id", id);
        detailFragment.setArguments(args);

        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.fragment_container, detailFragment);

        transaction.addToBackStack(null);

        transaction.commit();
    }
}