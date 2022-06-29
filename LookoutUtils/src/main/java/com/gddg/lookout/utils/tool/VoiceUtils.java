package com.gddg.lookout.utils.tool;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.util.Log;

public class VoiceUtils {

    public void playSound(byte[] b){
//        try {
//            // create temp file that will hold byte array
//            File tempMp3 = File.createTempFile("TTS", ".mp3");
//            tempMp3.deleteOnExit();
//            FileOutputStream fos = new FileOutputStream(tempMp3);
//            fos.write(b);
//            fos.close();
//
//            // Tried reusing instance of media player
//            // but that resulted in system crashes...
//            MediaPlayer mediaPlayer = new MediaPlayer();
//
//            // Tried passing path directly, but kept getting
//            // "Prepare failed.: status=0x1"
//            // so using file descriptor instead
//            FileInputStream fis = new FileInputStream(tempMp3);
//            mediaPlayer.setDataSource(fis.getFD());
//
//            mediaPlayer.prepare();
//            mediaPlayer.start();
//        } catch (IOException ex) {
//            String s = ex.toString();
//            ex.printStackTrace();
//        }



        try {
            /**
             android.media.AudioTrack public static int getMinBufferSize(int
             sampleRateInHz, int channelConfig, int audioFormat)
             返回在MODE_STREAM模式下创建的 AudioTrack 对象所需的估计最小缓冲区大小。 大小是一个
             估计值，因为它既不考虑路由也不考虑汇，因为两者都不知道。 请注意，此大小并不能保证在
             负载下流畅播放，应根据缓冲区重新填充要播放的其他数据的预期频率选择更高的值。 例如，
             如果您打算将 AudioTrack 的源采样率动态设置为高于初始源采样率的值，请务必根据计划的
             最高采样率配置缓冲区大小。
             参数：
             sampleRateInHz – 以 Hz 表示的源采样率。 不允许
             AudioFormat.SAMPLE_RATE_UNSPECIFIED 。
             channelConfig – 描述音频通道的配置。 请参阅AudioFormat.CHANNEL_OUT_MONO和
             AudioFormat.CHANNEL_OUT_STEREO
             audioFormat – 表示音频数据的格式。 请参阅AudioFormat.ENCODING_PCM_16BIT和
             AudioFormat.ENCODING_PCM_8BIT和AudioFormat.ENCODING_PCM_FLOAT 。
             返回：
             如果传递了无效参数，则为ERROR_BAD_VALUE如果无法查询输出属性，则为ERROR ，或者以字
             节为单位表示的        最小缓冲区大小
             *
             */
            AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
                    16000, AudioFormat.CHANNEL_OUT_MONO, AudioFormat.ENCODING_PCM_16BIT, b.length, AudioTrack.MODE_STREAM);
            audioTrack.play();
            audioTrack.write(b, 0, b.length);
            audioTrack.stop();
        } catch (Throwable t) {
            Log.e("Audio", "播放失败"+t.getMessage());
        }


    }

}
