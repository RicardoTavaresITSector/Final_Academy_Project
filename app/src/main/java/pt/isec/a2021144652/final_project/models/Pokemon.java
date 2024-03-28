    package pt.isec.a2021144652.final_project.models;

    import com.google.gson.JsonArray;
    import com.google.gson.JsonElement;
    import com.google.gson.JsonObject;
    import com.google.gson.annotations.SerializedName;

    import java.util.ArrayList;
    import java.util.List;

    public class Pokemon {
        private int id;
        private String url;
        private String name;
        @SerializedName("sprites")
        private JsonObject sprites;
        @SerializedName("front_default")
        private String img;
        private String height;
        private String weight;
        private JsonArray types;
        private JsonArray moves;

        public Pokemon(int id, JsonObject sprites, String name, String img, String height, String weight, JsonArray types, JsonArray moves) {
            this.id = id;
            this.name = name;
            this.img = img;
            this.height = height;
            this.weight = weight;
            this.sprites = sprites;
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
            List<String> typeNames = new ArrayList<>();
            if (types != null) {
                for (JsonElement typeElement : types) {
                    JsonObject typeObject = typeElement.getAsJsonObject();
                    JsonObject type = typeObject.get("type").getAsJsonObject();
                    String typeName = type.get("name").getAsString();
                    typeNames.add(typeName);
                }
            }
            return typeNames;
        }

        public List<String> getMoves() {
            List<String> allMoves = new ArrayList<>();
            if (moves != null) {
                for (JsonElement typeElement : moves) {
                    JsonObject typeObject = typeElement.getAsJsonObject();
                    JsonObject type = typeObject.get("move").getAsJsonObject();
                    String typeName = type.get("name").getAsString();
                    allMoves.add(typeName);
                }
            }
            return allMoves;
        }

        public String getUrl() {
            return url;
        }

    }
