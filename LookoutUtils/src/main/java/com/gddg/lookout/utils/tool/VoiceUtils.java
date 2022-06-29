package com.gddg.lookout.utils.tool;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder;
import android.util.Log;

import androidx.core.app.ActivityCompat;

public class VoiceUtils {

    private static int streamType = AudioManager.STREAM_MUSIC;
    private static int sampleRateInHz_out = 16000;
    private static int sampleRateInHz_in = 8000;
    private static int channelConfig_out = AudioFormat.CHANNEL_OUT_MONO;
    private static int channelConfig_in = AudioFormat.CHANNEL_IN_STEREO;
    private static int audioFormat = AudioFormat.ENCODING_PCM_16BIT;
    private static int mode = AudioTrack.MODE_STREAM;


    public static void playSound(byte[] b) {

        try {
            AudioTrack audioTrack = new AudioTrack(streamType,
                    sampleRateInHz_out, channelConfig_out, audioFormat, b.length, mode);
            audioTrack.play();
            audioTrack.write(b, 0, b.length);
            audioTrack.stop();
        } catch (Throwable t) {
            Log.e("Audio", "播放失败" + t.getMessage());
        }
    }

    public static void playSound4Rec(byte[] b) {

        try {
            AudioTrack audioTrack = new AudioTrack(streamType,
                    sampleRateInHz_in, channelConfig_in, audioFormat, b.length, mode);
            audioTrack.play();
            audioTrack.write(b, 0, b.length);
            audioTrack.stop();
        } catch (Throwable t) {
            Log.e("Audio", "播放失败" + t.getMessage());
        }
    }

    private static int bufferSize;
    private static AudioRecord audioRecord;

    @SuppressLint("MissingPermission")
    public static void recordInit() {
        try {
            bufferSize = AudioRecord.getMinBufferSize(sampleRateInHz_in, channelConfig_in, audioFormat);
            audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, sampleRateInHz_in, channelConfig_in, audioFormat, bufferSize);
        } catch (Throwable t) {
            Log.e("Audio", "录音初始化失败");
            t.printStackTrace();
        }
    }

    private static boolean isRecording = false;
    private static byte[] record = new byte[0];

    public static void recordStart() {
        try {
            record = new byte[10 * bufferSize];
            audioRecord.startRecording();
            isRecording = true;

            new Thread(()->{
                int cnt = 0;
                while (isRecording) {
                    int bufferReadResult = audioRecord.read(record, cnt * bufferSize, bufferSize);
                    cnt++;
                    if (cnt % 10 == 0) {
                        byte[] temp = new byte[bufferSize * (cnt + 10)];
                        System.arraycopy(record, 0, temp, 0, bufferSize * cnt);
                        record = temp;
                    }
                    Log.e("Audio", "Recording...");
                }
            }).start();

        } catch (Throwable t) {
            Log.e("Audio", "录音失败");
        }
    }

    public static byte[] recordStop() {
        isRecording = false;
        try {
            audioRecord.stop();
        } catch (Throwable t) {
            Log.e("Audio", "录音失败");
        }
        Log.e("Audio", "录音成功: "+ record.length);
        return record;
    }

}
