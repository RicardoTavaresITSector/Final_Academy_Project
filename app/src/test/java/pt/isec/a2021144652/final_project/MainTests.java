package pt.isec.a2021144652.final_project;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import pt.isec.a2021144652.final_project.models.ApiServiceTest;
import pt.isec.a2021144652.final_project.models.FavoritePokemon;
import pt.isec.a2021144652.final_project.models.FavoritePokemonTest;
import pt.isec.a2021144652.final_project.models.PokemonListTest;
import pt.isec.a2021144652.final_project.models.PokemonResponseTest;
import pt.isec.a2021144652.final_project.models.PokemonTest;
import pt.isec.a2021144652.final_project.room.FavoritePokemonDaoTest;
import pt.isec.a2021144652.final_project.utils.ConvertersTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        // Utils
        ConvertersTest.class,
        // Retrofit
        ApiServiceTest.class,
        // Models
        FavoritePokemonTest.class,
        PokemonTest.class,
        PokemonListTest.class,
        PokemonResponseTest.class,
        // Room
        FavoritePokemonDaoTest.class
})

public class MainTests {

}