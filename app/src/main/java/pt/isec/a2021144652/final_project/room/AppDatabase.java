package pt.isec.a2021144652.final_project.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;

import pt.isec.a2021144652.final_project.models.FavoritePokemon;
import pt.isec.a2021144652.final_project.utils.Converters;

@Database(entities = {FavoritePokemon.class}, version = 4, exportSchema = false)
@TypeConverters(Converters.class)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public abstract FavoritePokemonDao favoritePokemonDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "pokemon_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
