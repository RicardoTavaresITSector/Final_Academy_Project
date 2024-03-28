package pt.isec.a2021144652.final_project.models;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class PokemonTest {
    JsonObject sprites = new JsonObject();
    JsonArray types = new JsonArray();
    JsonArray moves = new JsonArray();
    Pokemon pokemon = new Pokemon(1, sprites, "Bulbasaur", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png", "7", "69", types, moves);

    @Test
    public void getId() {
        assertEquals(1, pokemon.getId());
    }

    @Test
    public void getName() {
        assertEquals("Bulbasaur", pokemon.getName());
    }

    @Test
    public void getImg() {
        assertEquals("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png", pokemon.getImg());
    }

    @Test
    public void getHeight() {
        assertEquals("7", pokemon.getHeight());
    }

    @Test
    public void getWeight() {
        assertEquals("69", pokemon.getWeight());
    }
}