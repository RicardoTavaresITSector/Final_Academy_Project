package pt.isec.a2021144652.final_project.models;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PokemonResponseTest {

    @Test
    public void getResults() {
        List<PokemonList> pokemonList = new ArrayList<>();
        pokemonList.add(new PokemonList("Bulbasaur", "https://pokeapi.co/api/v2/pokemon/1/"));
        pokemonList.add(new PokemonList("Charmander", "https://pokeapi.co/api/v2/pokemon/4/"));
        pokemonList.add(new PokemonList("Squirtle", "https://pokeapi.co/api/v2/pokemon/7/"));

        PokemonResponse response = new PokemonResponse();
        response.setResults(pokemonList);

        List<PokemonList> results = response.getResults();

        assertNotNull(results);
        assertEquals(3, results.size());
        assertEquals("Bulbasaur", results.get(0).getName());
        assertEquals("https://pokeapi.co/api/v2/pokemon/4/", results.get(1).getUrl());
    }

    @Test
    public void setResults() {
        List<PokemonList> pokemonList = new ArrayList<>();
        pokemonList.add(new PokemonList("Bulbasaur", "https://pokeapi.co/api/v2/pokemon/1/"));
        pokemonList.add(new PokemonList("Charmander", "https://pokeapi.co/api/v2/pokemon/4/"));
        pokemonList.add(new PokemonList("Squirtle", "https://pokeapi.co/api/v2/pokemon/7/"));

        PokemonResponse response = new PokemonResponse();
        response.setResults(pokemonList);

        List<PokemonList> results = response.getResults();

        assertNotNull(results);
        assertEquals(3, results.size());
        assertEquals("Bulbasaur", results.get(0).getName());
        assertEquals("https://pokeapi.co/api/v2/pokemon/4/", results.get(1).getUrl());
    }
}