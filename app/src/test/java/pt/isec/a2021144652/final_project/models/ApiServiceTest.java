package pt.isec.a2021144652.final_project.models;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceTest {

    @Test
    public void testGetPokemon() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);
        Call<Pokemon> call = service.getPokemon("1");
        Response<Pokemon> response = call.execute();

        assertTrue(response.isSuccessful());
        assertNotNull(response.body());
    }

    @Test
    public void testGetPokemons() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);
        Call<PokemonResponse> call = service.getPokemons(10);
        Response<PokemonResponse> response = call.execute();

        assertTrue(response.isSuccessful());
        assertNotNull(response.body());
        assertNotNull(response.body().getResults());
        assertFalse(response.body().getResults().isEmpty());
    }

    @Test
    public void testGetSomePokemons() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);
        Call<PokemonResponse> call = service.getSomePokemons(10, 0);
        Response<PokemonResponse> response = call.execute();

        assertTrue(response.isSuccessful());
        assertNotNull(response.body());
        assertNotNull(response.body().getResults());
        assertFalse(response.body().getResults().isEmpty());
    }

}