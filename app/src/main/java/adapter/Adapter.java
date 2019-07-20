package adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.realmdatabase.R;
import activity.UpdateDelete;

import java.util.List;

import model.Student;

public class Adapter  extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    Context context;
    List<Student> studentList;

    public Adapter(List<Student> studentList,Context context){
        this.context = context;
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_show_recycler_item, viewGroup ,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Adapter.MyViewHolder myViewHolder, int i) {

        // Add  details

        Student student = studentList.get(i);
        myViewHolder.name.setText(student.getName());
        myViewHolder.mail.setText(student.getEmail());
        myViewHolder.mob.setText(student.getMobileno());
        myViewHolder.add.setText(student.getAddress());

        // cardview ---- Update---Delete

        myViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent  = new Intent(context, UpdateDelete.class);

                intent.putExtra("Name",studentList.get(myViewHolder.getAdapterPosition()).getName());
                intent.putExtra("email",studentList.get(myViewHolder.getAdapterPosition()).getEmail());
                intent.putExtra("mobile",studentList.get(myViewHolder.getAdapterPosition()).getMobileno());
                intent.putExtra("Address",studentList.get(myViewHolder.getAdapterPosition()).getAddress());

                context.startActivity(intent);

            }
        });
    }


    @Override
    public int getItemCount() {
        if (studentList != null && studentList.size() > 0) {
            return studentList.size();
        } else {
            return 0;
        }
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name,mail,mob,add;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.txtname);
            mail = itemView.findViewById(R.id.txtemail);
            mob = itemView.findViewById(R.id.txtmobileno);
            add = itemView.findViewById(R.id.txtaddress);

            // cardview
            cardView = itemView.findViewById(R.id.cardview);
        }
    }
}
