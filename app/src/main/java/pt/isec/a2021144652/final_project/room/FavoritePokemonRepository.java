package pt.isec.a2021144652.final_project.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import pt.isec.a2021144652.final_project.models.FavoritePokemon;

public class FavoritePokemonRepository {
    private FavoritePokemonDao favoritePokemonDao;

    public FavoritePokemonRepository(Context context) {
        favoritePokemonDao = AppDatabase.getInstance(context).favoritePokemonDao();
    }

    public void removePokemonFromFavorites(int id) {
        new RemovePokemonTask().execute(id);
    }

    public void addPokemonToFavorites(FavoritePokemon pokemon) {
        new InsertPokemonTask().execute(pokemon);
    }

    public LiveData<FavoritePokemon> getPokemonById(String pokemonId) {
        return favoritePokemonDao.getPokemonById(pokemonId);
    }

    public LiveData<List<FavoritePokemon>> getAllFavoritePokemon() {
        return favoritePokemonDao.getAllFavoritePokemon();
    }

    private class InsertPokemonTask extends AsyncTask<FavoritePokemon, Void, Void> {

        @Override
        protected Void doInBackground(FavoritePokemon... favoritePokemons) {
            favoritePokemonDao.insert(favoritePokemons[0]);
            return null;
        }
    }

    private class RemovePokemonTask extends AsyncTask<Integer, Void, Void> {

        @Override
        protected Void doInBackground(Integer... ids) {
            favoritePokemonDao.delete(String.valueOf(ids[0]));
            return null;
        }
    }
}
