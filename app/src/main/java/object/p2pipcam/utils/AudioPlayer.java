package object.p2pipcam.utils;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.util.Log;

public class AudioPlayer {

	// private final static String LOG_TAG = "AudioPlayer" ;

	CustomBuffer audioBuffer = null;
	private boolean bAudioPlaying = false;
	private Thread audioThread = null;
	private AudioTrack m_AudioTrack = null;
	private int mode = 0;

	public AudioPlayer(CustomBuffer buffer, int mode1) {
		// TODO Auto-generated constructor stub
		audioBuffer = buffer;
		mode = mode1;
	}

	public boolean isAudioPlaying() {
		return bAudioPlaying;
	}

	public boolean AudioPlayStart() {
		synchronized (this) {
			if (bAudioPlaying) {
				return true;
			}
			bAudioPlaying = true;
			audioThread = new Thread(new AudioPlayThread());
			audioThread.start();
		}
		return true;
	}

	public void AudioPlayStop() {
		synchronized (this) {
			if (!bAudioPlaying || audioThread == null) {
				return;
			}

			bAudioPlaying = false;
			try {
				audioThread.join();
			} catch (Exception e) {
				// TODO: handle exception
			}
			audioThread = null;
		}
	}

	public boolean initAudioDev() {
		Log.d("tag", "AudioTrack---mode="+mode);
		int channelConfig;
		int audioFormat = 2;
		int mMinBufSize = 0;

		channelConfig = AudioFormat.CHANNEL_CONFIGURATION_MONO;
		audioFormat = AudioFormat.ENCODING_PCM_16BIT;
		if (mode == 2) {
			mMinBufSize = AudioTrack.getMinBufferSize(16000, channelConfig,
					audioFormat);
		} else {
			mMinBufSize = AudioTrack.getMinBufferSize(8000, channelConfig,
					audioFormat);
		}

		System.out.println("--audio, mMinBufSize=" + mMinBufSize);

		if (mMinBufSize == AudioTrack.ERROR_BAD_VALUE
				|| mMinBufSize == AudioTrack.ERROR)
			return false;

		try {
			if (mode == 2) {
				m_AudioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, 16000,
						channelConfig, audioFormat, mMinBufSize * 2,
						AudioTrack.MODE_STREAM);
			} else {
				m_AudioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, 8000,
						channelConfig, audioFormat, mMinBufSize * 2,
						AudioTrack.MODE_STREAM);
			}
		} catch (IllegalArgumentException iae) {
			iae.printStackTrace();
			return false;
		}

		m_AudioTrack.play();
		return true;
	}

	class AudioPlayThread implements Runnable {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			if (!initAudioDev()) {
				Log.d("tag", "��ʼ��audioTrackʧ��");
				return;
			}

			while (bAudioPlaying) {
				CustomBufferData data = audioBuffer.RemoveData();
				if (data == null) {
					try {
						Thread.sleep(50);
						continue;
					} catch (Exception e) {
						// TODO: handle exception
						m_AudioTrack.stop();
						return;
					}
				}
				// Log.d(LOG_TAG, "length:" + data.head.length);
				m_AudioTrack.write(data.data, 0, data.head.length);
				// Log.d(LOG_TAG, "nRet:" + nRet);
				//Log.d("tag", "AudioTrack---mode="+mode);
			}
			Log.d("tag", "stop/release Audio");
			m_AudioTrack.stop();
			m_AudioTrack.release();
			m_AudioTrack = null;
		}

	}
}