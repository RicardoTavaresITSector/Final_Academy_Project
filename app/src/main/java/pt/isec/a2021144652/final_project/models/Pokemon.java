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

        public Pokemon(){
        }

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

        public Pokemon(String bulbasaur, String s) {
            this.name = bulbasaur;
            this.img = s;
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

        public String getImg() {
            return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/" + id + ".png";
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
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

        public void setTypes(JsonArray types) {
            this.types = types;
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

        public void setMoves(JsonArray moves) {
            this.moves = moves;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
