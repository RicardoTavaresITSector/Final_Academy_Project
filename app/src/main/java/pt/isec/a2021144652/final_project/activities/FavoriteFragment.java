package pt.isec.a2021144652.final_project.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pt.isec.a2021144652.final_project.R;
import pt.isec.a2021144652.final_project.adapter.FavoritePokemonsAdapter;
import pt.isec.a2021144652.final_project.models.FavoritePokemon;
import pt.isec.a2021144652.final_project.room.AppDatabase;
import pt.isec.a2021144652.final_project.room.FavoritePokemonDao;
import pt.isec.a2021144652.final_project.room.PokemonViewModel;


public class FavoriteFragment extends Fragment {
    Toolbar favotitePokemonsToolbar;
    private PokemonViewModel viewModel;
    private RecyclerView recyclerView;
    private FavoritePokemonsAdapter adapter;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication())).get(PokemonViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        favotitePokemonsToolbar = view.findViewById(R.id.favotitePokemonsToolbar);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(favotitePokemonsToolbar);
        if (((AppCompatActivity) requireActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) requireActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        favotitePokemonsToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().onBackPressed();
            }
        });
        recyclerView = view.findViewById(R.id.recyclerViewFavoritePokemon);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new FavoritePokemonsAdapter(new ArrayList<>(), viewModel);
        recyclerView.setAdapter(adapter);

        viewModel.getAllFavoritePokemon().observe(getViewLifecycleOwner(), new Observer<List<FavoritePokemon>>() {
            @Override
            public void onChanged(List<FavoritePokemon> favoritePokemonList) {
                adapter.setPokemons(favoritePokemonList);
            }
        });

        return view;
    }
}