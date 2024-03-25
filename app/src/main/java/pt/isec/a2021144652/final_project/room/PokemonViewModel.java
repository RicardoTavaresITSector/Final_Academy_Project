package pt.isec.a2021144652.final_project.room;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import pt.isec.a2021144652.final_project.models.FavoritePokemon;

public class PokemonViewModel extends AndroidViewModel {
    private FavoritePokemonRepository repository;

    public PokemonViewModel(@NonNull Application application) {
        super(application);
        repository = new FavoritePokemonRepository(application);
    }

    public void addPokemonToFavorites(int pokemonId, String name, String imageUrl, String height, String weight, List<String> types, List<String> moves) {
        FavoritePokemon pokemon = new FavoritePokemon(pokemonId, name, imageUrl, height, weight, types, moves);
        repository.addPokemonToFavorites(pokemon);
    }

    public void removePokemonFromFavorites(int id) {
        repository.removePokemonFromFavorites(id);
    }

    public LiveData<FavoritePokemon> getPokemonById(String pokemonId) {
        return repository.getPokemonById(pokemonId);
    }

    public LiveData<List<FavoritePokemon>> getAllFavoritePokemon() {
        return repository.getAllFavoritePokemon();
    }
}
