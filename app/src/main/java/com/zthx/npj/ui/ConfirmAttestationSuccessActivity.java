package com.zthx.npj.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zthx.npj.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConfirmAttestationSuccessActivity extends ActivityBase {

    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.title_theme_tv_right)
    TextView titleThemeTvRight;
    @BindView(R.id.title_theme_img_right)
    ImageView titleThemeImgRight;
    @BindView(R.id.ac_confirmAttestation_btn_back)
    Button acConfirmAttestationBtnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_attestation_success);
        ButterKnife.bind(this);

        back(titleThemeBack);
        changeTitle(titleThemeTitle, "提交成功");
    }

    @OnClick(R.id.ac_confirmAttestation_btn_back)
    public void onViewClicked() {
        openActivity(MyAttestationActivity.class);
    }
}
