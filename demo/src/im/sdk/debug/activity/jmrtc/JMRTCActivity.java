package im.sdk.debug.activity.jmrtc;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class JMRTCActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView hint = new TextView(this);
        hint.append("此版本demo不支持音视频功能");
        setContentView(hint);
    }
}
