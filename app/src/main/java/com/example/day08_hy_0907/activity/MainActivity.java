package com.example.day08_hy_0907.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.day08_hy_0907.R;
import com.example.day08_hy_0907.adapter.MyAdapter;
import com.example.day08_hy_0907.bean.Bean;
import com.example.day08_hy_0907.net.HttpUtils;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PullToRefreshListView pull_to_refresh_list;
    private String path = "https://www.zhaoapi.cn/product/getProducts?pscid=1 ";
    private List<Bean.DataBean> list;
    private boolean isPull;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        pull_to_refresh_list = findViewById(R.id.pull_to_refresh_list);

        getFromNet();
        getFromId();

    }

    private void getFromId() {
        pull_to_refresh_list.setMode(PullToRefreshBase.Mode.BOTH);
        pull_to_refresh_list.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                isPull = true;
                getFromNet();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                getFromNet();
            }
        });
        //创建适配器
        adapter = new MyAdapter(list, MainActivity.this);
        pull_to_refresh_list.setAdapter(adapter);
    }

    private void getFromNet() {
        //获取接口
        String url = path;
        HttpUtils instance = HttpUtils.getInstance();
        instance.setUrl(url);
        instance.setGetDataCallback(new HttpUtils.getDataCallback() {
            @Override
            public void CallBack(String result) {
                Gson gson = new Gson();
                Bean bean = gson.fromJson(result, Bean.class);
                List<Bean.DataBean> data = bean.getData();
                if (isPull) {
                    isPull = !isPull;
                    list.clear();
                }
                list.addAll(data);
                adapter.notifyDataSetChanged();
                pull_to_refresh_list.onRefreshComplete();
            }
        });
    }
}
