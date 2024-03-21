package pt.isec.a2021144652.final_project.models;

import java.util.List;

public class PokemonResponse {
    private List<PokemonList> results;

    public List<PokemonList> getResults() {
        return results;
    }

    public void setResults(List<PokemonList> results) {
        this.results = results;
    }
}
