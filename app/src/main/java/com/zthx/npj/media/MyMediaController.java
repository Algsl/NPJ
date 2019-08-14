package com.zthx.npj.media;

import android.content.Context;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatSeekBar;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.MediaController;
import android.widget.SeekBar;

import com.zthx.npj.R;
import com.zthx.npj.media.IMediaController;
import com.zthx.npj.media.IjkVideoView;

import java.util.Locale;

import tv.danmaku.ijk.media.player.IMediaPlayer;

public class MyMediaController implements IMediaController, View.OnClickListener, AppCompatCheckBox.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener {
    private View controllerRoot;
    private AppCompatTextView tvStart;
    private AppCompatTextView tvEnd;
    private AppCompatSeekBar seekBar;
    private AppCompatCheckBox btnFull;
    private AppCompatImageButton btnPlay;

    private boolean isShowing;
    private MediaController.MediaPlayerControl player;
    private long duration;
    private boolean isDragging;
    private AudioManager audioManager;

    public MyMediaController(Context context) {

        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        controllerRoot = LayoutInflater.from(context).inflate(R.layout.activity_video_controll, null);

        tvStart = controllerRoot.findViewById(R.id.tv_start);
        tvEnd = controllerRoot.findViewById(R.id.tv_end);
        seekBar = controllerRoot.findViewById(R.id.seek_bar);
        btnFull = controllerRoot.findViewById(R.id.btn_full);
        btnPlay = controllerRoot.findViewById(R.id.btn_play);

        btnFull.setOnCheckedChangeListener(this);
        btnPlay.setOnClickListener(this);
        seekBar.setOnSeekBarChangeListener(this);

    }

    public void attach(IjkVideoView videoView) {
        videoView.addView(controllerRoot);
        videoView.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(IMediaPlayer iMediaPlayer) {
                isDragging = true;//修改
                isShowing = true;
                handler.sendEmptyMessage(MESSAGE_SHOW_PROGRESS);
                handler.sendEmptyMessageDelayed(SET_VIEW_HIDE, TIME_OUT);
            }
        });

        videoView.setOnCompletionListener(new IMediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(IMediaPlayer iMediaPlayer) {
                btnPlay.setImageResource(R.drawable.item_pause);
            }
        });
    }

    public void disAttach(IjkVideoView videoView) {
        videoView.removeView(controllerRoot);
        handler.removeMessages(SET_VIEW_HIDE);
        handler.removeMessages(SET_VIEW_SHOW);
        handler.removeMessages(MESSAGE_SHOW_PROGRESS);
        handler.removeMessages(PAUSE_IMAGE_HIDE);
        handler.removeMessages(MESSAGE_SEEK_NEW_POSITION);
        handler.removeMessages(MESSAGE_HIDE_CONTROL);
        handler = null;
    }

    @Override
    public void hide() {
        handler.removeMessages(SET_VIEW_HIDE);
        handler.removeMessages(SET_VIEW_SHOW);
        handler.sendEmptyMessage(SET_VIEW_HIDE);
    }

    @Override
    public boolean isShowing() {
        return isShowing;
    }

    @Override
    public void setAnchorView(View view) {

    }

    @Override
    public void setEnabled(boolean enabled) {

    }

    @Override
    public void setMediaPlayer(MediaController.MediaPlayerControl player) {
        this.player = player;
    }

    @Override
    public void show(int timeout) {
        handler.removeMessages(SET_VIEW_HIDE);
        handler.removeMessages(SET_VIEW_SHOW);
        handler.sendEmptyMessage(SET_VIEW_SHOW);
        handler.sendEmptyMessageDelayed(SET_VIEW_HIDE, timeout);
    }

    @Override
    public void show() {
        handler.removeMessages(SET_VIEW_HIDE);
        handler.removeMessages(SET_VIEW_SHOW);
        handler.sendEmptyMessage(SET_VIEW_SHOW);
        handler.sendEmptyMessageDelayed(SET_VIEW_HIDE, TIME_OUT);
    }

    @Override
    public void showOnce(View view) {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        //切换播放状态
        if (id == R.id.btn_play) {
            if (player.isPlaying()) {
                pause();
            } else {
                reStart();
            }

        }

    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (mListener != null) {
            mListener.onFullScreen(isChecked);
        }
    }
    //全屏与非全屏转换
    public void switchFull(boolean full) {
        btnFull.setChecked(full);
    }

    //是否全屏
    public boolean isFull() {
        return btnFull.isChecked();
    }


    public void pause() {
        btnPlay.setImageResource(R.drawable.item_play);
        player.pause();
        if (mListener != null) {
            mListener.onPause(true);
        }
    }

    public void reStart() {
        btnPlay.setImageResource(R.drawable.item_pause);
        player.start();
        if (mListener != null) {
            mListener.onPause(false);
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        String string = generateTime(progress);
        tvStart.setText(string);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        setProgress();
        isDragging = true;
        audioManager.setStreamMute(AudioManager.STREAM_MUSIC, true);
        handler.removeMessages(MESSAGE_SHOW_PROGRESS);
        show();
        handler.removeMessages(SET_VIEW_HIDE);
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        isDragging = false;
        player.seekTo(seekBar.getProgress());
        handler.removeMessages(MESSAGE_SHOW_PROGRESS);
        audioManager.setStreamMute(AudioManager.STREAM_MUSIC, false);
        isDragging = false;
        handler.sendEmptyMessageDelayed(MESSAGE_SHOW_PROGRESS, 1000);
        show();
    }

    private String generateTime(long time) {
        int totalSeconds = (int) (time / 1000);
        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;
        return hours > 0 ? String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds)
                : String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
    }

    private void setProgress() {
        if (isDragging) {
            return;
        }

        long position = player.getCurrentPosition();
        long duration = player.getDuration();
        this.duration = duration;
        if (!generateTime(duration).equals(tvEnd.getText().toString()))
            tvEnd.setText(generateTime(duration));
        if (seekBar != null) {
            if (duration > 0) {
                int pos = (int) position;
                seekBar.setMax((int) duration);
                seekBar.setProgress(pos);
            }
            int percent = player.getBufferPercentage();
            seekBar.setSecondaryProgress(percent);
        }
        String string = generateTime(position);
        tvStart.setText(string);
    }

    private static final int SET_VIEW_HIDE = 1;
    private static final int SET_VIEW_SHOW = 6;
    private static final int TIME_OUT = 3000;
    private static final int MESSAGE_SHOW_PROGRESS = 2;
    private static final int PAUSE_IMAGE_HIDE = 3;
    private static final int MESSAGE_SEEK_NEW_POSITION = 4;
    private static final int MESSAGE_HIDE_CONTROL = 5;
    private long newPosition = 1;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SET_VIEW_HIDE:
                    controllerRoot.setVisibility(View.GONE);
                    isShowing = false;
                    break;
                case SET_VIEW_SHOW:
                    controllerRoot.setVisibility(View.VISIBLE);
                    isShowing = true;
                    break;
                case MESSAGE_SHOW_PROGRESS:
                    setProgress();
                    if (!isDragging) {
                        msg = obtainMessage(MESSAGE_SHOW_PROGRESS);
                        sendMessageDelayed(msg, 1000);
                    }
                    break;
                case PAUSE_IMAGE_HIDE:
                    break;
                case MESSAGE_SEEK_NEW_POSITION:
                    if (newPosition >= 0) {
                        player.seekTo((int) newPosition);
                        newPosition = -1;
                    }
                    break;
                case MESSAGE_HIDE_CONTROL:
//                    seekTxt.setVisibility(View.GONE);
                    break;
            }
        }
    };
    //自定义的接口，可以根据自己的需求修改
    private OnVideoListener mListener;

    public void setListener(OnVideoListener listener) {
        mListener = listener;

    }

    public interface OnVideoListener {
        //暂停
        void onPause(boolean pause);

        //全屏
        void onFullScreen(boolean full);
    }
}
