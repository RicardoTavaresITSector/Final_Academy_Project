package pt.isec.a2021144652.final_project.adapter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.test.core.app.ApplicationProvider;

import pt.isec.a2021144652.final_project.R;
import pt.isec.a2021144652.final_project.activities.DetailFragment;
import pt.isec.a2021144652.final_project.activities.MainFragment;
import pt.isec.a2021144652.final_project.models.PokemonList;

public class PokemonAdapterTest {

    private PokemonAdapter adapter;
    private ArrayList<PokemonList> pokemons;

    @Mock
    private MainFragment context;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        pokemons = new ArrayList<>();
        pokemons.add(new PokemonList("Bulbasaur", "https://pokeapi.co/api/v2/pokemon/1/"));
        pokemons.add(new PokemonList("Charmander", "https://pokeapi.co/api/v2/pokemon/4/"));
        adapter = new PokemonAdapter(context, pokemons);
    }

    @Test
    public void testGetItemCount() {
        assertEquals(2, adapter.getItemCount());
    }

    @Test
    public void testOnBindViewHolder() {
        //PokemonAdapter.ViewHolder viewHolder = mock(PokemonAdapter.ViewHolder.class);
        //PokemonAdapter.ViewHolder spyViewHolder = spy(viewHolder);
        //TextView textView = mock(TextView.class);
        //spyViewHolder.tvPokemonName = textView;

        //int position = 1;
        //adapter.onBindViewHolder(spyViewHolder, position);

        //verify(spyViewHolder).tvPokemonName.setText("Bulbasaur");

        //pokemons = new ArrayList<>();
        //pokemons.add(new PokemonList("Bulbasaur", "https://pokeapi.co/api/v2/pokemon/1/"));
        //pokemons.add(new PokemonList("Charmander", "https://pokeapi.co/api/v2/pokemon/4/"));
        //adapter = new PokemonAdapter(context, pokemons);
        FrameLayout parent = new FrameLayout(ApplicationProvider.getApplicationContext());
        PokemonAdapter.ViewHolder viewHolder = adapter.onCreateViewHolder(parent, 0);
        TextView textView = mock(TextView.class);
        when(viewHolder.tvPokemonName).thenReturn(textView);

        adapter.onBindViewHolder(viewHolder, 0);

        verify(textView).setText("Bulbasaur");
    }

    @Test
    public void testItemClick() {
        adapter.onItemClick(0);

        verify(context).onItemClicked(0);
    }
}