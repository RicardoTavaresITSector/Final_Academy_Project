package pt.isec.a2021144652.final_project.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pt.isec.a2021144652.final_project.R;

public class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.TypeViewHolder> {
    private List<String> types;

    public TypeAdapter(List<String> types) {
        this.types = types;
    }

    @NonNull
    @Override
    public TypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_type_image, parent, false);
        return new TypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TypeViewHolder holder, int position) {
        String type = types.get(position);
        int drawableResId = getResourceIdForType(type);
        if (drawableResId != 0) {
            holder.ivType.setImageResource(drawableResId);
        }
    }

    @Override
    public int getItemCount() {
        return types.size();
    }

    public class TypeViewHolder extends RecyclerView.ViewHolder {
        ImageView ivType;

        public TypeViewHolder(@NonNull View itemView) {
            super(itemView);
            ivType = itemView.findViewById(R.id.ivType);
        }
    }

    public int getResourceIdForType(String type) {
        switch (type) {
            case "normal":
                return R.drawable.type_normal;
            case "bug":
                return R.drawable.type_bug;
            case "fighting":
                return R.drawable.type_fighting;
            case "flying":
                return R.drawable.type_flying;
            case "ghost":
                return R.drawable.type_ghost;
            case "poison":
                return R.drawable.type_poison;
            case "ground":
                return R.drawable.type_ground;
            case "rock":
                return R.drawable.type_rock;
            case "steel":
                return R.drawable.type_steel;
            case "fire":
                return R.drawable.type_fire;
            case "water":
                return R.drawable.type_water;
            case "grass":
                return R.drawable.type_grass;
            case "electric":
                return R.drawable.type_electric;
            case "psychic":
                return R.drawable.type_psychic;
            case "ice":
                return R.drawable.type_ice;
            case "dragon":
                return R.drawable.type_dragon;
            case "dark":
                return R.drawable.type_dark;
            case "fairy":
                return R.drawable.type_fairy;
            case "shadow":
                return R.drawable.type_shadow;
            default:
                return R.drawable.pokeball;
        }
    }
}