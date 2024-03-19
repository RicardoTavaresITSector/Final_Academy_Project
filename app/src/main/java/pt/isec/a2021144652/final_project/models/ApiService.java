package pt.isec.a2021144652.final_project.models;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("pokemon/{id}")
    Call<Pokemon> getPokemon(@Path("id") String id);
    @GET("pokemon")
    Call<PokemonResponse> getPokemons(@Query("limit") int limit);

}
