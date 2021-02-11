package com.example.loopshort;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.MappingTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.TrackSelectionDialogBuilder;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class VideoWatchActivity extends AppCompatActivity {

int MAX_HEIGHT = 539;
 int MAX_WIDTH = 959;

    private static final String TAG = "mytag";
    private boolean playWhenReady = true;
        private int currentWindow = 0;
        private long playbackPosition = 0;
        private PlayerView playerView;
        private SimpleExoPlayer player;
    DefaultTrackSelector trackSelector;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_video_watch);
            playerView = findViewById(R.id.videoView);
          findViewById(R.id.user_view).setOnClickListener(this::UserClick);
            Object uri = getIntent().getExtras().get("url");
            String s = uri.toString();
     //       String s = "https://s3.amazonaws.com/_bc_dml/example-content/sintel_dash/sintel_vod.mpd";
      //      String s = "https://testingvideo-dev-output-w0cqb334.s3.ap-south-1.amazonaws.com/public/992f3081-d927-40f2-af9e-e9540a29d96c/992f3081-d927-40f2-af9e-e9540a29d96c.mpd";
            Log.d(TAG, "onCreate: "+s);
                  initializePlayer(s);
        }

        private void UserClick(View view) {
            showMenuQuality();

            Log.d(TAG, "UserClick: ");
            //    Toast.makeText(getApplicationContext(), " User click", Toast.LENGTH_SHORT).show();
        }

        private void initializePlayer(String urii) {
             trackSelector = new DefaultTrackSelector(this);
             player =  new SimpleExoPlayer.Builder(getApplicationContext())
                            .setTrackSelector(trackSelector)
                            .build();
            MappingTrackSelector.MappedTrackInfo mappedTrackInfo = trackSelector.getCurrentMappedTrackInfo();
            Log.d(TAG, "initializePlayer: "+mappedTrackInfo);
            Log.d(TAG, "initializePlayer: "+trackSelector.getCurrentMappedTrackInfo());
            playerView.setPlayer(player);

            Uri uri = Uri.parse(urii);
            MediaSource mediaSource = buildMediaSource(uri);
            player.setPlayWhenReady(playWhenReady);
            player.seekTo(currentWindow, playbackPosition);
            player.prepare(mediaSource);
            getPlayer();
            }

    private void showMenuQuality() {
//        MappingTrackSelector.MappedTrackInfo currentMappedTrackInfo = trackSelector.getCurrentMappedTrackInfo();
//        Log.d(TAG, "showMenuQuality: "+currentMappedTrackInfo);
//          TrackSelectionDialogBuilder build = new TrackSelectionDialogBuilder(this, "", trackSelector, 2);
//          build.setAllowAdaptiveSelections(true);
//       build.build().show();

//

        MappingTrackSelector.MappedTrackInfo mappedTrackInfo = trackSelector.getCurrentMappedTrackInfo();
        Log.d(TAG, "showMenuQuality: "+mappedTrackInfo);
        if (mappedTrackInfo != null) {
            CharSequence title = "Video";
            int rendererIndex = 0;
            int rendererType = mappedTrackInfo.getRendererType(rendererIndex);
            boolean allowAdaptiveSelections =
                    rendererType == C.TRACK_TYPE_VIDEO
                            || (rendererType == C.TRACK_TYPE_AUDIO
                            && mappedTrackInfo.getTypeSupport(C.TRACK_TYPE_VIDEO)
                            == MappingTrackSelector.MappedTrackInfo.RENDERER_SUPPORT_NO_TRACKS);

            TrackSelectionDialogBuilder build = new TrackSelectionDialogBuilder(this, title, trackSelector, rendererIndex);

            build.setAllowAdaptiveSelections(true);
            build.build().show();


        }else{
            Log.d(TAG, "showMenuQuality: else ");
        }

    }

    private void getPlayer() {
        Player.EventListener eventListener = new Player.EventListener() {
            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                Log.d(TAG, "onPlayerStateChanged: ");
//                showMenuQuality();
                MappingTrackSelector.MappedTrackInfo mappedTrackInfo = trackSelector.getCurrentMappedTrackInfo();
                Log.d(TAG, "showMenuQuality: "+mappedTrackInfo);

            }
        };
        player.addListener(eventListener);
    }


    private MediaSource buildMediaSource(Uri uri) {
            // Create a data source factory.
            DefaultHttpDataSourceFactory exoplayer = new DefaultHttpDataSourceFactory("exoplayer");
// Create a DASH media source pointing to a DASH manifest uri.
            MediaSource mediaSource =
                    new DashMediaSource.Factory(exoplayer)
                            .createMediaSource(uri);
// Create a player instance.
            return mediaSource;
        }



        @Override
        public void onStop() {
            super.onStop();
            if (Util.SDK_INT >= 24) {
                releasePlayer();
            }
        }

        private void releasePlayer() {
            if (player != null) {
                playWhenReady = player.getPlayWhenReady();
                playbackPosition = player.getCurrentPosition();
                currentWindow = player.getCurrentWindowIndex();
                player.release();
                player = null;
            }
        }
    }
