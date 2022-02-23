package com.gaurav.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BeatBox {
    private static final String TAG = "BeatBox";
    private static final String SOUNDS_FOLDER = "sample_sounds";
    private static  final int MAX_SOUNDS = 5;

    private AssetManager mAssets;
    private List<Sound> mSounds = new ArrayList<>();
    private SoundPool mSoundPool;

    public BeatBox(Context context) {
        mAssets = context.getAssets();
        mSoundPool = new SoundPool.Builder().setMaxStreams(MAX_SOUNDS).build();
        loadSounds();
    }

    private void loadSounds() {
        String[] soundNames = null;
        try {
            soundNames = mAssets.list(SOUNDS_FOLDER);
            Log.i(TAG, "Found" + soundNames.length + " sounds");
        } catch (IOException e) {
            Log.e(TAG, "Could not list assets", e);
        }

        for (String filename: soundNames) {
            try {
                String assetPath = SOUNDS_FOLDER + "/" + filename;
                Sound sound = new Sound(assetPath);
                load(sound);
                mSounds.add(sound);
            } catch (IOException ioException){
                Log.e(TAG, "could not load sound " + filename, ioException);
            }
        }
    }

    private void load(Sound sound) throws IOException{
        AssetFileDescriptor assetFileDescriptor = mAssets.openFd(sound.getAssetPath());
        int soundId = mSoundPool.load(assetFileDescriptor,1);
        sound.setSoundId(soundId);
    }

    public List<Sound> getSounds() {
        return mSounds;
    }
}
