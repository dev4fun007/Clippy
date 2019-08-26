package bytes.sync.clippy;

import android.app.Service;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.IBinder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ClippyForegroundService extends Service implements ClipboardManager.OnPrimaryClipChangedListener {

    private ClipboardManager clipboardManager;

    private List<String> textClipList;


    public ClippyForegroundService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        clipboardManager = (ClipboardManager) getApplicationContext().getSystemService(CLIPBOARD_SERVICE);
        clipboardManager.addPrimaryClipChangedListener(this);
        textClipList = new ArrayList<>(5);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        clipboardManager.removePrimaryClipChangedListener(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    private void setPrimaryClip(String text) {
        clipboardManager.setPrimaryClip(ClipData.newPlainText("CLIPPY_PRIMARY_CLIP_TEXT",text));
    }

    @Override
    public void onPrimaryClipChanged() {
        ClipData clipData = clipboardManager.getPrimaryClip();
        if(clipData.getItemCount() > 0) {
            ClipData.Item item = clipData.getItemAt(0);
            String clipText = item.getText().toString();
        }
    }
}
