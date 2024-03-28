package pt.isec.a2021144652.final_project.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "favorite_pokemon")
public class FavoritePokemon {
    @PrimaryKey
    private int id;
    private String name;
    private String img;
    private String height;
    private String weight;
    private List<String> types;
    private List<String> moves;

    public FavoritePokemon(int id, String name, String img, String height, String weight, List<String> types, List<String> moves) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.height = height;
        this.weight = weight;
        this.types = types;
        this.moves = moves;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImg() {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/" + id + ".png";
    }

    public String getHeight() {
        return height;
    }

    public String getWeight() {
        return weight;
    }

    public List<String> getTypes() {
        return types;
    }

    public List<String> getMoves() {
        return moves;
    }
}
