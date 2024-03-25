package pt.isec.a2021144652.final_project.activities;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pt.isec.a2021144652.final_project.R;
import pt.isec.a2021144652.final_project.models.FavoritePokemon;
import pt.isec.a2021144652.final_project.room.AppDatabase;
import pt.isec.a2021144652.final_project.room.FavoritePokemonDao;
import pt.isec.a2021144652.final_project.room.PokemonViewModel;


public class FavoriteFragment extends Fragment {

    private PokemonViewModel viewModel;
    private TextView textViewFavoritePokemonNames;

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

        textViewFavoritePokemonNames = view.findViewById(R.id.textViewFavoritePokemonNames);

        viewModel.getAllFavoritePokemon().observe(getViewLifecycleOwner(), new Observer<List<FavoritePokemon>>() {
            @Override
            public void onChanged(List<FavoritePokemon> favoritePokemonList) {
                StringBuilder stringBuilder = new StringBuilder();
                for (FavoritePokemon pokemon : favoritePokemonList) {
                    stringBuilder.append(pokemon.getName()).append("\n");
                }

                textViewFavoritePokemonNames.setText(stringBuilder.toString());
            }
        });

        return view;
    }
}