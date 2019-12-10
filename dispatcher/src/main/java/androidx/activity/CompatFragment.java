package androidx.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

@SuppressLint("Registered")
public class CompatFragment extends Fragment implements OnActivityResultDispatcherOwner {
    private final OnActivityResultDispatcher mOnActivityResultDispatcher = new OnActivityResultDispatcher(this);

    /**
     * Retrieve the {@link OnActivityResultDispatcher} that will be triggered when
     * {@link #onActivityResult(int, int, Intent)} is called.
     *
     * @return The {@link OnActivityResultDispatcher} associated with this ComponentActivity.
     */
    @NonNull
    @Override
    public OnActivityResultDispatcher getOnActivityResultDispatcher() {
        return mOnActivityResultDispatcher;
    }

    @Override
    @CallSuper
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        mOnActivityResultDispatcher.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * @param action The Intent action, such as ACTION_VIEW.
     */
    public void startActivityForResult(String action, OnActivityResultCallback callback) {
        Intent intent = new Intent(action);
        getOnActivityResultDispatcher().addCallback(callback);
        super.startActivityForResult(intent, callback.getRequestCode());
    }

    /**
     * @param action The Intent action, such as ACTION_VIEW.
     * @param uri    The Intent data URI.
     */
    public void startActivityForResult(String action, Uri uri, OnActivityResultCallback callback) {
        Intent intent = new Intent(action, uri);
        getOnActivityResultDispatcher().addCallback(callback);
        super.startActivityForResult(intent, callback.getRequestCode());
    }

    /**
     * @param packageContext A Context of the application package implementing this class.
     * @param cls            The component class that is to be used for the intent.
     */
    public void startActivityForResult(Context packageContext, Class<?> cls, OnActivityResultCallback callback) {
        Intent intent = new Intent(packageContext, cls);
        getOnActivityResultDispatcher().addCallback(callback);
        super.startActivityForResult(intent, callback.getRequestCode());
    }

    public void startActivityForResult(String action, Uri uri,
                                       Context packageContext,
                                       Class<?> cls,
                                       OnActivityResultCallback callback) {
        Intent intent = new Intent(action, uri, packageContext, cls);
        getOnActivityResultDispatcher().addCallback(callback);
        super.startActivityForResult(intent, callback.getRequestCode());
    }

    public void startActivityForResult(Intent intent, OnActivityResultCallback callback) {
        getOnActivityResultDispatcher().addCallback(callback);
        super.startActivityForResult(intent, callback.getRequestCode());
    }

    public void startActivityForResult(Intent intent, @Nullable Bundle options, OnActivityResultCallback callback) {
        getOnActivityResultDispatcher().addCallback(callback);
        super.startActivityForResult(intent, callback.getRequestCode(), options);
    }

}
