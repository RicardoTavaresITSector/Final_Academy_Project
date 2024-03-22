package pt.isec.a2021144652.final_project.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pt.isec.a2021144652.final_project.R;

public class MovesAdapter extends RecyclerView.Adapter<MovesAdapter.MovesViewHolder>{
    private List<String> moves;

    public MovesAdapter(List<String> moves) {
        this.moves = moves;
    }

    @NonNull
    @Override
    public MovesAdapter.MovesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_pokemon_moves, parent, false);
        return new MovesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovesViewHolder holder, int position) {
        String move = moves.get(position);
        String capitalizedMove = capitalizeFirstLetter(move);
        holder.tvMove.setText(capitalizedMove);
    }

    @Override
    public int getItemCount() {
        return moves.size();
    }

    public class MovesViewHolder extends RecyclerView.ViewHolder {
        TextView tvMove;

        public MovesViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMove = itemView.findViewById(R.id.tv_pokemon_move);
        }
    }

    private String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
