package com.example.loopshort;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.options.AuthSignOutOptions;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Post;
import com.example.loopshort.databinding.ActivitySecondBinding;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity implements MyListAdapter.setVideoClick{
    MyListAdapter adapter;
    private static final String TAG = "mytag";
    ActivitySecondBinding activitySecondBinding;
    ArrayList<MyListData>myListData = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    activitySecondBinding = DataBindingUtil.setContentView(this,R.layout.activity_second);
//       setContentView(R.layout.activity_second);
        activitySecondBinding.userLogoutButton.setOnClickListener(this::Userlogout);
        activitySecondBinding.userUploadPost.setOnClickListener(this::UserUpload);


//  myListData.add( new MyListData("Email", android.R.drawable.ic_dialog_email));
//myListData.add(              new MyListData("Info", android.R.drawable.ic_dialog_info));
//myListData.add(              new MyListData("Info", android.R.drawable.ic_dialog_info));
//myListData.add(              new MyListData("Info", android.R.drawable.ic_dialog_info));
//               myListData.add( new MyListData("Delete", android.R.drawable.ic_delete));

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycleview);
     adapter = new MyListAdapter(this,myListData);
  activitySecondBinding.recycleview.setHasFixedSize(true);
        activitySecondBinding.recycleview.setLayoutManager(new LinearLayoutManager(this));
  activitySecondBinding.recycleview.setAdapter(adapter);
        Amplify.API.query(
                ModelQuery.list(Post.class),
                response -> {

                    for (Post todo : response.getData()) {
                        Log.i("MyAmplifyApp", todo.toString());
                        myListData.add(new MyListData(todo.getId(),todo.getComments(),todo.getDescription(),todo.getImageUrl(),
                                todo.getOwner(),todo.getTitle(),todo.getVideoUrl(),todo.getView()));
                    }

                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );


    }

    private void UserUpload(View view) {
      //  myListData.add( new MyListData("Dialer", android.R.drawable.ic_dialog_dialer));
        adapter.notifyDataSetChanged();

//        Post todo = Post.builder()
//                .title("My todo")
//                .description("userdata")
//                .build();
//
//        Amplify.API.mutate(
//                ModelMutation.create(todo),
//                response ->{}, //Toast.makeText(getApplicationContext(),"sucessful upload",Toast.LENGTH_SHORT).show(),
//                error -> {}//Toast.makeText(getApplicationContext(),"fail",Toast.LENGTH_SHORT).show()
//        );
    }

    private void Userlogout(View view) {

        Amplify.Auth.signOut(
                AuthSignOutOptions.builder().globalSignOut(true).build(),
                () ->          startActivity(new Intent(this,MainActivity.class)),
                error -> Log.e("AuthQuickstart", error.toString())
        );

    }

    @Override
    public void positioncallBack(String id) {
        Log.d(TAG, "positioncallBack: "+id);
        Intent intent = new Intent(this,VideoWatchActivity.class);
            Amplify.API.query(
                    ModelQuery.get(Post.class, id),
                    response ->{   intent.putExtra("url",response.getData().getVideoUrl());
        startActivity(intent);},
                    error -> Log.e("MyAmplifyApp", error.toString(), error)
            );


        }

}