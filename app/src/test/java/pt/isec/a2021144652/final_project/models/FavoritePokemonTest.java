package pt.isec.a2021144652.final_project.models;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Arrays;

public class FavoritePokemonTest {

    FavoritePokemon pokemon = new FavoritePokemon(1, "Bulbasaur", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png", "7", "69", Arrays.asList("Grass", "Poison"), Arrays.asList("Tackle", "Vine Whip"));

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

    @Test
    public void getTypes() {
        assertEquals(Arrays.asList("Grass", "Poison"), pokemon.getTypes());
    }

    @Test
    public void getMoves() {
        assertEquals(Arrays.asList("Tackle", "Vine Whip"), pokemon.getMoves());
    }
}