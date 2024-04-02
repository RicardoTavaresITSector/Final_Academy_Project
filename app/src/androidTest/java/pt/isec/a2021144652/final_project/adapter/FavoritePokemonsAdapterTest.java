package pt.isec.a2021144652.final_project.adapter;

import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.test.core.app.ApplicationProvider;

import pt.isec.a2021144652.final_project.R;
import pt.isec.a2021144652.final_project.activities.FavoriteFragment;
import pt.isec.a2021144652.final_project.adapter.FavoritePokemonsAdapter;
import pt.isec.a2021144652.final_project.models.FavoritePokemon;
import pt.isec.a2021144652.final_project.room.PokemonViewModel;


public class FavoritePokemonsAdapterTest {
    private FavoritePokemonsAdapter adapter;
    private List<FavoritePokemon> pokemons;

    @Mock
    private PokemonViewModel viewModel;

    @Mock
    private FavoriteFragment activity;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        pokemons = new ArrayList<>();
        pokemons.add(new FavoritePokemon(1, "Bulbasaur", "image_url", "1.0m", "10kg", Arrays.asList("Grass", "Poison"), Arrays.asList("Tackle", "Vine Whip")));
        pokemons.add(new FavoritePokemon(2, "Pikachu", "image_url", "1.0m", "10kg", Arrays.asList("Electric"), Arrays.asList("Thunderbolt")));
        adapter = new FavoritePokemonsAdapter(pokemons, viewModel, activity);
    }

    @Test
    public void testGetItemCount() {
        assertEquals(2, adapter.getItemCount());
    }

    @Test
    public void testOnBindViewHolder() throws Throwable {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                FrameLayout parent = new FrameLayout(ApplicationProvider.getApplicationContext());
                FavoritePokemonsAdapter.FavoritePokemonViewHolder viewHolder = adapter.onCreateViewHolder(parent, 0);
                adapter.onBindViewHolder(viewHolder, 0);
                assertEquals("Bulbasaur", ((TextView) viewHolder.itemView.findViewById(R.id.tvFavoritePokemonName)).getText());
            }
        });
    }
}