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

    public void addPokemonToFavorites(FavoritePokemon pokemon) {
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
