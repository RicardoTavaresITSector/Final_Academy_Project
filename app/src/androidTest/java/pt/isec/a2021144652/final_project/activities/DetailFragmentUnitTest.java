package pt.isec.a2021144652.final_project.activities;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import android.view.View;
import android.widget.TextView;

import pt.isec.a2021144652.final_project.R;
import pt.isec.a2021144652.final_project.models.Pokemon;

public class DetailFragmentUnitTest {

    private DetailFragment detailFragment;

    @Before
    public void setUp() {
        detailFragment = new DetailFragment();
    }

    @Test
    public void testLoadPokemonDetails_Success() {
        Pokemon pokemon = new Pokemon(1, null, "Pikachu", "https", "0.4", "6.0", null, null);

        detailFragment.loadPokemonDetails(String.valueOf(pokemon.getId()));

        assertEquals("Pikachu", detailFragment.tvDetailPokemonName.getText().toString());
        assertEquals("6.0kg", detailFragment.tvWeight.getText().toString());
        assertEquals("0.4m", detailFragment.tvHeight.getText().toString());
    }
}