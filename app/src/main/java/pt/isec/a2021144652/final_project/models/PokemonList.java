package pt.isec.a2021144652.final_project.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

public class PokemonList {
    private int id;
    private String name;
    private String url;
    @SerializedName("front_default")
    private String img;
    public PokemonList(String name, String url) {
        this.name = name;
        this.url = url;
        String[] parts = url.split("/");
        int id = Integer.parseInt(parts[parts.length - 1]);
        this.id = id;
        this.img = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/" + id + ".png";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
