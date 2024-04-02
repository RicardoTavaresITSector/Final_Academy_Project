package pt.isec.a2021144652.final_project;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import static org.junit.Assert.*;

import pt.isec.a2021144652.final_project.adapter.FavoritePokemonsAdapterTest;
import pt.isec.a2021144652.final_project.adapter.MovesAdapterTest;
import pt.isec.a2021144652.final_project.adapter.PokemonAdapterTest;
import pt.isec.a2021144652.final_project.adapter.TypeAdapterTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        // Adapters
        TypeAdapterTest.class,
        MovesAdapterTest.class,
        PokemonAdapterTest.class,
        FavoritePokemonsAdapterTest.class
})

public class MainTests {

}