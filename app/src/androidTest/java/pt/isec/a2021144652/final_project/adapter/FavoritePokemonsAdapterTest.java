package pt.isec.a2021144652.final_project.adapter;

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
import android.widget.ImageView;

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
        adapter = new FavoritePokemonsAdapter(pokemons, viewModel, activity);
    }

    @Test
    public void testGetItemCount() {
        pokemons.add(new FavoritePokemon(1, "Bulbasaur", "image_url", "1.0m", "10kg", Arrays.asList("Grass", "Poison"), Arrays.asList("Tackle", "Vine Whip")));
        pokemons.add(new FavoritePokemon(2, "Pikachu", "image_url", "1.0m", "10kg", Arrays.asList("Electric"), Arrays.asList("Thunderbolt")));

        assertEquals(2, adapter.getItemCount());
    }
}