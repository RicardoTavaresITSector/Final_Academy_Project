package pt.isec.a2021144652.final_project.room;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import pt.isec.a2021144652.final_project.models.FavoritePokemon;
import pt.isec.a2021144652.final_project.room.AppDatabase;
import pt.isec.a2021144652.final_project.room.FavoritePokemonDao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class FavoritePokemonDaoTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private AppDatabase mDatabase;
    private FavoritePokemonDao mFavoritePokemonDao;

    @Before
    public void setup() {
        mDatabase = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),
                        AppDatabase.class)
                .allowMainThreadQueries()
                .build();
        mFavoritePokemonDao = mDatabase.favoritePokemonDao();
    }

    @After
    public void tearDown() {
        mDatabase.close();
    }

    @Test
    public void testInsertAndGetAllFavoritePokemon() throws InterruptedException {
        FavoritePokemon pokemon = new FavoritePokemon(1, "Bulbasaur", "image_url", "1.0m", "10kg", Arrays.asList("Grass", "Poison"), Arrays.asList("Tackle", "Vine Whip"));
        mFavoritePokemonDao.insert(pokemon);

        LiveData<List<FavoritePokemon>> favoritePokemonLiveData = mFavoritePokemonDao.getAllFavoritePokemon();

        List<FavoritePokemon> favoritePokemonList = getValue(favoritePokemonLiveData);

        assertNotNull(favoritePokemonList);
        assertEquals(1, favoritePokemonList.size());
        assertEquals("Bulbasaur", favoritePokemonList.get(0).getName());
    }

    @Test
    public void testDelete() throws InterruptedException {
        FavoritePokemon pokemon = new FavoritePokemon(1, "Bulbasaur", "image_url", "1.0m", "10kg", Arrays.asList("Grass", "Poison"), Arrays.asList("Tackle", "Vine Whip"));
        mFavoritePokemonDao.insert(pokemon);

        mFavoritePokemonDao.delete("1");

        LiveData<List<FavoritePokemon>> favoritePokemonLiveData = mFavoritePokemonDao.getAllFavoritePokemon();

        List<FavoritePokemon> favoritePokemonList = getValue(favoritePokemonLiveData);

        assertEquals(0, favoritePokemonList.size());
    }

    @Test
    public void testGetPokemonById() throws InterruptedException {
        FavoritePokemon pokemonToAdd = new FavoritePokemon(1, "Bulbasaur", "image_url", "1.0m", "10kg", Arrays.asList("Grass", "Poison"), Arrays.asList("Tackle", "Vine Whip"));
        mFavoritePokemonDao.insert(pokemonToAdd);

        LiveData<FavoritePokemon> pokemonLiveData = mFavoritePokemonDao.getPokemonById("1");

        FavoritePokemon retrievedPokemon = getValue(pokemonLiveData);

        assertNotNull(retrievedPokemon);
        assertEquals(pokemonToAdd.getId(), retrievedPokemon.getId());
        assertEquals(pokemonToAdd.getName(), retrievedPokemon.getName());;
    }

    // Helper method to get the value from a LiveData object synchronously
    private <T> T getValue(final LiveData<T> liveData) throws InterruptedException {
        final Object[] data = new Object[1];
        CountDownLatch latch = new CountDownLatch(1);
        Observer<T> observer = new Observer<T>() {
            @Override
            public void onChanged(T t) {
                data[0] = t;
                latch.countDown();
                liveData.removeObserver(this);
            }
        };
        liveData.observeForever(observer);
        latch.await(2, TimeUnit.SECONDS);
        return (T) data[0];
    }
}