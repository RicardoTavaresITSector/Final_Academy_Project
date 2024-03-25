package pt.isec.a2021144652.final_project;

import android.app.Application;

import androidx.room.Room;

import pt.isec.a2021144652.final_project.room.AppDatabase;

public class MyApplication extends Application {
    public static AppDatabase appDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        appDatabase = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "pokemon_database")
                .build();
    }

    public static AppDatabase getPokemonDatabase() {
        return appDatabase;
    }
}