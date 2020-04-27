package cn.edu.sdwu.android02.home.sn170507180211;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MediaService extends Service {
    private MediaPlayer mediaPlayer;
    private MyBinder myBinder;
    public MediaService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer=MediaPlayer.create(this,R.raw.wav);
        mediaPlayer.setLooping(false);
        myBinder=new MyBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //从Intent中获取用户需要的操作，然后进一步处理
        String state=intent.getStringExtra("PlayerState");
        if (state!=null){
            if (state.equals("START")){//播放
                start();
            }
            if (state.equals("PAUSE")){//暂停
                pause();
            }
            if (state.equals("STOP")){//停止播放
                stop();
            }
            if (state.equals("STOPSERVICE")){//关闭服务
                stopSelf();
            }
        }
        Log.i(MediaService.class.toString(),toString());
        return super.onStartCommand(intent, flags, startId);
    }

    public void start(){
        mediaPlayer.start();
    }
    public void pause(){
        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
    }
    public void stop() {
        mediaPlayer.stop();
        //为了下一次的播放，需要调用prepare方法
        try {
            mediaPlayer.prepare();
        } catch (Exception e) {
            Log.e(MediaService.class.toString(),e.toString());
        }
    }

    @Override
    public void onDestroy() {
        Log.i(MediaService.class.toString(),"onDestroy");
        mediaPlayer.stop();
        mediaPlayer.release();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.i(MediaService.class.toString(),"onBind");
        return myBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(MediaService.class.toString(),"onUnbind");
        return super.onUnbind(intent);
    }

    public  class MyBinder extends Binder {
        public MediaService getMediaService(){
            return  MediaService.this;
        }
    }
}
