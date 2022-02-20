package com.gaurav.beatbox;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;

public class BeatBox {
    private static final String TAG = "BeatBox";
    private static final String SOUNDS_FOLDER = "sample_sounds";
    private AssetManager mAssets;

    public BeatBox(Context context) {
        mAssets = context.getAssets();
        loadSounds();
    }

    private void loadSounds() {
        String[] soundsNames;
        try {
            soundsNames = mAssets.list(SOUNDS_FOLDER);
            Log.i(TAG, "Found" + soundsNames.length + " sounds");
        } catch (IOException e) {
            Log.e(TAG, "Could not list assets", e);
        }

    }
}
