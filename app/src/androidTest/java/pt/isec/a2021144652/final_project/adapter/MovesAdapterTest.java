package pt.isec.a2021144652.final_project.adapter;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.RuntimeEnvironment;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

import android.widget.TextView;

public class MovesAdapterTest {
    private MovesAdapter movesAdapter;
    private List<String> moves;

    @Before
    public void setUp() {
        moves = new ArrayList<>();
        moves.add("move1");
        moves.add("move2");
        movesAdapter = new MovesAdapter(moves);
    }

    @Test
    public void testGetItemCount() {
        assertEquals(2, movesAdapter.getItemCount());
    }

    @Test
    public void testCapitalizeFirstLetter() {
        String capitalized = movesAdapter.capitalizeFirstLetter("move");
        assertEquals("Move", capitalized);

        capitalized = movesAdapter.capitalizeFirstLetter("MOVE");
        assertEquals("MOVE", capitalized);

        capitalized = movesAdapter.capitalizeFirstLetter("");
        assertEquals("", capitalized);

        capitalized = movesAdapter.capitalizeFirstLetter(null);
        assertEquals(null, capitalized);
    }
}