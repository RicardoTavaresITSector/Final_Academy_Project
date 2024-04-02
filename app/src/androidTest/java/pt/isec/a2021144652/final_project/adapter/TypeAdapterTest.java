package pt.isec.a2021144652.final_project.adapter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import android.view.ViewGroup;
import android.widget.ImageView;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import pt.isec.a2021144652.final_project.R;

public class TypeAdapterTest {
    private TypeAdapter typeAdapter;
    private List<String> types;

    @Before
    public void setUp() {
        types = new ArrayList<>();
        types.add("normal");
        types.add("fire");
        typeAdapter = new TypeAdapter(types);
    }

    @Test
    public void testGetItemCount() {
        assertEquals(2, typeAdapter.getItemCount());
    }

    @Test
    public void testGetResourceIdForType() {
        assertEquals(R.drawable.type_normal, typeAdapter.getResourceIdForType("normal"));
        assertEquals(R.drawable.type_fire, typeAdapter.getResourceIdForType("fire"));
        assertEquals(R.drawable.pokeball, typeAdapter.getResourceIdForType("unknown"));
    }

    @Test
    public void testOnBindViewHolder() {
        TypeAdapter.TypeViewHolder viewHolder = mock(TypeAdapter.TypeViewHolder.class);
        TypeAdapter.TypeViewHolder spyViewHolder = spy(viewHolder);
        ImageView imageView = mock(ImageView.class);
        spyViewHolder.ivType = imageView;

        int position = 0;
        typeAdapter.onBindViewHolder(spyViewHolder, position);

        verify(spyViewHolder).ivType.setImageResource(R.drawable.type_normal);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testOnBindViewHolderInvalidPosition() {
        TypeAdapter.TypeViewHolder viewHolder = mock(TypeAdapter.TypeViewHolder.class);
        typeAdapter.onBindViewHolder(viewHolder, typeAdapter.getItemCount() + 1);
    }
}