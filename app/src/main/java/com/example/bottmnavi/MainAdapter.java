package com.example.bottmnavi;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.CustomViewHolder> {

    private ArrayList<MainMemo> arrayList;
    private Context context;

    public MainAdapter(Context context,ArrayList<MainMemo> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MainAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MainAdapter.CustomViewHolder holder, final int position) {
        //holder.iv_profile.setImageResource(arrayList.get(position).getIv_profile());
        holder.tv_title.setText(arrayList.get(position).getTv_title());
        holder.tv_content.setText(arrayList.get(position).getTv_content());
        holder.tv_content.setVisibility(View.GONE);

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Context context = view.getContext();

                final AlertDialog.Builder memo = new AlertDialog.Builder(context);
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
                final View v = inflater.inflate(R.layout.note, null);

                EditText etitleText = (EditText) v.findViewById(R.id.et_title);
                EditText econtentText = (EditText) v.findViewById(R.id.et_contents);
                etitleText.setText(arrayList.get(position).getTv_title());
                econtentText.setText(arrayList.get(position).getTv_content());
                memo.setView(v);

                memo.setPositiveButton("저장", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        EditText edittitleText = (EditText) v.findViewById(R.id.et_title);
                        EditText editcontentText = (EditText) v.findViewById(R.id.et_contents);
                        String result_title = edittitleText.getText().toString();
                        String result_content = editcontentText.getText().toString();
                        arrayList.get(position).setTv_title(result_title);
                        arrayList.get(position).setTv_content(result_content);
                        notifyDataSetChanged();
                        dialogInterface.dismiss();
                    }
                });
                memo.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                memo.show();
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Context context = view.getContext();

                AlertDialog.Builder delete = new AlertDialog.Builder(context);
                delete.setMessage("삭제하시겠습니까?");

                delete.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        remove(holder.getAdapterPosition());
                        dialogInterface.dismiss();
                    }
                });
                delete.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                delete.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public void remove(int position){
        try{
            arrayList.remove(position);
            notifyItemRemoved(position);
        } catch(IndexOutOfBoundsException ex){
            ex.printStackTrace();
        }
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected ImageView iv_profile;
        protected TextView tv_title;
        protected TextView tv_content;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_profile = (ImageView) itemView.findViewById(R.id.iv_profile);
            this.tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            this.tv_content = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }

}
