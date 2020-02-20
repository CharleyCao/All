package cn.caoleix.all.ui.adapter;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cn.caoleix.all.R;
import cn.caoleix.all.entity.ItemBean;
import cn.caoleix.all.ui.activity.ArticleActivity;
import cn.caoleix.all.utils.CImageLoader;

public class TabAdapter extends RecyclerView.Adapter<TabAdapter.Holder> {

    private List<ItemBean.Child> data;

    private OnItemClickListener onItemClickListener;

    public TabAdapter(List<ItemBean.Child> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_tab, null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int position) {
        ItemBean.Child item = data.get(position);
        CImageLoader.newInstance().url(item.getLogo(), holder.ivLogo);
        holder.tvTitle.setText(item.getTitle());
        holder.tvSubTitle.setText(item.getSubTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
//                    Intent intent = new Intent(holder.itemView.getContext(), ArticleActivity.class);
//                    holder.itemView.getContext().startActivity(intent);
                    onItemClickListener.onItemClick(holder.itemView, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    static class Holder extends RecyclerView.ViewHolder {

        private ImageView ivLogo;
        private TextView tvTitle;
        private TextView tvSubTitle;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ivLogo = itemView.findViewById(R.id.iv_logo);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvSubTitle = itemView.findViewById(R.id.tv_sub_title);
        }

    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}
