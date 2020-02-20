package cn.caoleix.all.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.xutils.x;

/**
  * @author charley
  * @date 2020/2/11 21:52
  * @desc
  */
public abstract class BaseFragment extends Fragment {

    protected Context mContext;
    protected View root;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getActivity();
        root = x.view().inject(this, inflater, container);
        init();
        return root;
    }

    protected abstract void init();

}
