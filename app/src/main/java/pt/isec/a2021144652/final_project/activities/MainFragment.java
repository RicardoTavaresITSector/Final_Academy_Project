package pt.isec.a2021144652.final_project.activities;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.SearchView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

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
    Toolbar toolbar;
    RecyclerView rvPokemons;
    RecyclerView.Adapter myAdapter;
    SearchView searchView;
    RecyclerView.LayoutManager layoutManager;
    List<PokemonList> pokemons;
    List<PokemonList> filteredPokemons;
    private static final String LIMIT = "151";
    private static final String OFFSET = "1";
    private boolean dataLoaded = false;

    public MainFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void openFavoritesPage() {
        FavoriteFragment favoriteFragment = new FavoriteFragment();

        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.fragment_container, favoriteFragment);

        transaction.addToBackStack(null);

        transaction.commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        toolbar = view.findViewById(R.id.main_toolbar);
        setHasOptionsMenu(true);
        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_favorite) {
                openFavoritesPage();
                return true;
            }
            return false;
        });
        toolbar.inflateMenu(R.menu.menu_items);

        rvPokemons = view.findViewById(R.id.rvPokemonList);
        searchView = view.findViewById(R.id.searchView);

        rvPokemons.setHasFixedSize(true);

        int columns = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ? 4 : 2;
        layoutManager = new GridLayoutManager(getContext(), columns, GridLayoutManager.VERTICAL, false);
        rvPokemons.setLayoutManager(layoutManager);

        if (!dataLoaded) {
            loadData();
        } else {
            setupRecyclerView();
        }

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

    private void loadData() {
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
                        dataLoaded = true;
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
    }

    private void setupRecyclerView() {
        myAdapter = new PokemonAdapter(MainFragment.this, (ArrayList<PokemonList>) filteredPokemons);
        rvPokemons.setAdapter(myAdapter);
    }

    private void filter(String text) {
        if (filteredPokemons == null) {
            filteredPokemons = new ArrayList<>();
        } else {
            filteredPokemons.clear();
            for (PokemonList pokemon : pokemons) {
                if (pokemon.getName().toLowerCase().contains(text.toLowerCase())) {
                    filteredPokemons.add(pokemon);
                }
            }
            myAdapter.notifyDataSetChanged();
        }
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


    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            rvPokemons.setLayoutManager(new GridLayoutManager(getContext(), 4, GridLayoutManager.VERTICAL, false));
        } else {
            rvPokemons.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
        }
    }
}