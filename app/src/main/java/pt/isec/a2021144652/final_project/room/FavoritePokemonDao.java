package pt.isec.a2021144652.final_project.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

import pt.isec.a2021144652.final_project.models.FavoritePokemon;
import pt.isec.a2021144652.final_project.models.Pokemon;

@Dao
public interface FavoritePokemonDao {
    @Query("SELECT * FROM favorite_pokemon")
    LiveData<List<FavoritePokemon>> getAllFavoritePokemon();

    @Insert
    void insert(FavoritePokemon favoritePokemon);

    @Query("DELETE FROM favorite_pokemon WHERE id = :pokemonId")
    void delete(String pokemonId);
    @Query("SELECT * FROM favorite_pokemon WHERE id = :pokemonId")
    LiveData<FavoritePokemon> getPokemonById(String pokemonId);
}