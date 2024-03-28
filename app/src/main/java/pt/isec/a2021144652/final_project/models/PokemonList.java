package pt.isec.a2021144652.final_project.models;

public class PokemonList {
    private int id;
    private String name;
    private String url;
    public PokemonList(String name, String url) {
        this.name = name;
        this.url = url;
        String[] parts = url.split("/");
        int id = Integer.parseInt(parts[parts.length - 1]);
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getUrl() {
        return url;
    }
}
