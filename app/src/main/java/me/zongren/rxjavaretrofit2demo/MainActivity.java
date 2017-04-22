package me.zongren.rxjavaretrofit2demo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import retrofit2.http.GET;

public class MainActivity extends AppCompatActivity {

    private ListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.show();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://zongren.me/").addConverterFactory(SimpleXmlConverterFactory.createNonStrict()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        Service service = retrofit.create(Service.class);

        service.atom().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Atom>() {
                    @Override
                    public void accept(@NonNull Atom atom) throws Exception {
                        dialog.dismiss();
                        onLoadAtom(atom);
                    }
                });

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        mAdapter = new ListAdapter();
        mAdapter.onClickListener = new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                MainActivity.this.onItemClicked(view);
            }
        };
        recyclerView.setAdapter(mAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void onItemClicked(View view) {
        Object idObj = view.getTag(R.id.tag_position);
        if(idObj!=null){
            int position = (int)idObj;
            ArticleModel model = mAdapter.articleList.get(position);
            Toast.makeText(this, "Click "+model.title, Toast.LENGTH_SHORT).show();
        }

    }

    private void onLoadAtom(Atom atom) {
        if(atom == null || atom.list == null || atom.list.size() == 0){
            return;
        }
        mAdapter.articleList.clear();
        for(AtomEntry entry:atom.list){
            ArticleModel model = new ArticleModel();
            model.title = entry.title.replace("\r","").replace("\n","").trim();
            model.subtitle = entry.summary.replace("\r","").replace("\n","").trim();
            mAdapter.articleList.add(model);
        }
        mAdapter.notifyDataSetChanged();
    }

    private interface Service {
        @GET("atom.xml")
        Observable<Atom> atom();
    }
}
