package pt.isec.a2021144652.final_project.adapter;

import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.RuntimeEnvironment;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.test.core.app.ApplicationProvider;

import pt.isec.a2021144652.final_project.R;

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

    @Test
    public void testOnBindViewHolder() throws Throwable {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                FrameLayout parent = new FrameLayout(ApplicationProvider.getApplicationContext());
                MovesAdapter.MovesViewHolder viewHolder = movesAdapter.onCreateViewHolder(parent, 0);
                movesAdapter.onBindViewHolder(viewHolder, 0);
                assertEquals("Move1", ((TextView) viewHolder.itemView.findViewById(R.id.tv_pokemon_move)).getText());
            }
        });
    }
}