package pt.isec.a2021144652.final_project.models;

import static org.junit.Assert.*;

import org.junit.Test;

public class PokemonListTest {

    PokemonList pokemonList = new PokemonList("bulbasaur", "https://pokeapi.co/api/v2/pokemon/1/");

    @Test
    public void getId() {
        assertEquals(1, pokemonList.getId());
    }

    @Test
    public void getName() {
        assertEquals("bulbasaur", pokemonList.getName());
    }

    @Test
    public void getUrl() {
        assertEquals("https://pokeapi.co/api/v2/pokemon/1/", pokemonList.getUrl());
    }
}