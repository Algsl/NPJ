package com.zthx.npj.ui;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zthx.npj.R;
import com.zthx.npj.adapter.ChatListAdapter;
import com.zthx.npj.net.api.URLConstant;
import com.zthx.npj.net.been.UploadImgResponseBean;
import com.zthx.npj.net.netutils.HttpUtils;
import com.zthx.npj.utils.GsonUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.options.MessageSendingOptions;
import cn.jpush.im.api.BasicCallback;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ServicesChatActivity extends ActivityBase {
    @BindView(R.id.ac_serviceChat_et_content)
    EditText acServiceChatEtContent;
    @BindView(R.id.ac_serviceChat_iv_expression)
    ImageView acServiceChatIvExpression;
    @BindView(R.id.ac_serviceChat_iv_add)
    ImageView acServiceChatIvAdd;
    @BindView(R.id.ac_serviceChat_tv_send)
    TextView acServiceChatTvSend;
    @BindView(R.id.ac_serviceChat_lv)
    ListView acServiceChatLv;
    @BindView(R.id.ac_serviceChat_ll_add)
    LinearLayout acServiceChatLlAdd;
    @BindView(R.id.ac_serviceChat_ll)
    RelativeLayout acServiceChatLl;
    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.ac_serviceChat_ll1)
    LinearLayout acServiceChatLl1;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.ac_serviceChat_ll_photoGraphic)
    LinearLayout acServiceChatLlPhotoGraphic;
    @BindView(R.id.ac_serviceChat_ll_takePhoto)
    LinearLayout acServiceChatLlTakePhoto;

    private String chat_name = "";
    private String receiveTitle = "";
    private Conversation mConversation = null;
    private boolean isOpen = false;
    private static final int TAKE_PHOTO = 1;
    private static final int CHOOSE_PHOTO = 2;
    private Uri imageUri;
    private MessageSendingOptions options;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_chat);
        ButterKnife.bind(this);
        chat_name = getIntent().getStringExtra("key0");
        receiveTitle = getIntent().getStringExtra("key1");
        back(titleThemeBack);
        changeTitle(titleThemeTitle, receiveTitle);
        getChatMsg();


        acServiceChatEtContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (acServiceChatEtContent.getText().toString().trim().equals("")) {
                    acServiceChatIvAdd.setVisibility(View.VISIBLE);
                    acServiceChatTvSend.setVisibility(View.GONE);
                } else {
                    acServiceChatIvAdd.setVisibility(View.GONE);
                    acServiceChatTvSend.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void getChatMsg() {
        mConversation = JMessageClient.getSingleConversation(chat_name,"8893ea927c390d073532296c");
        if (mConversation == null) {
            Log.e("测试", "getChatMsg: "+chat_name+""+(mConversation==null) );
        }else{
            acServiceChatLv.setDivider(null);
            List<Message> lists = mConversation.getMessagesFromNewest(0, 18);
            ChatListAdapter adapter = new ChatListAdapter(this, lists);
            acServiceChatLv.setAdapter(adapter);
            acServiceChatLv.setSelection(lists.size() - 1);
            adapter.notifyDataSetInvalidated();
        }
    }

    //用户端发送消息设置
    @OnClick(R.id.ac_serviceChat_tv_send)
    public void onViewClicked() {
        String text = acServiceChatEtContent.getText().toString().trim();
        if (!TextUtils.isEmpty(chat_name) && !TextUtils.isEmpty(text)) {
            //通过username和appkey拿到会话对象，通过指定appkey可以创建一个和跨应用用户的会话对象，从而实现跨应用的消息发送
            if (mConversation == null) {
                mConversation = Conversation.createSingleConversation(chat_name, "");
            }
            //构造message content对象
            TextContent textContent = new TextContent(text);
            //设置自定义的extra参数
            textContent.setStringExtra("", "");
            //创建message实体，设置消息发送回调。创建消息内容，包含内容信息，发送方信息。
            final Message message = mConversation.createSendMessage(textContent, "用户18435224024");
            message.setOnSendCompleteCallback(new BasicCallback() {
                @Override
                public void gotResult(int i, String s) {
                    if (i == 0) {
                        acServiceChatEtContent.setText("");
                        getChatMsg();
                    }
                }
            });
            //设置消息发送时的一些控制参数
            options = new MessageSendingOptions();
            options.setNeedReadReceipt(true);//是否需要对方用户发送消息已读回执
            options.setRetainOffline(true);//是否当对方用户不在线时让后台服务区保存这条消息的离线消息
            options.setShowNotification(true);//是否让对方展示sdk默认的通知栏通知
            options.setCustomNotificationEnabled(true);//是否需要自定义对方收到这条消息时sdk默认展示的通知栏中的文字
            if (true) {
                options.setNotificationTitle(message.getFromName());//自定义对方收到消息时通知栏展示的title
                //options.setNotificationAtPrefix("时光之刃");//自定义对方收到消息时通知栏展示的@信息的前缀
                options.setNotificationText(text);//自定义对方收到消息时通知栏展示的text
            }
            /*if (!TextUtils.isEmpty(mEt_customMsgCount.getText())) {
                try {
                    options.setMsgCount(Integer.valueOf(mEt_customMsgCount.getText().toString()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            mProgressDialog = MsgProgressDialog.show(CreateSigTextMessageActivity.this, message);*/
            //发送消息
            JMessageClient.sendMessage(message, options);
        } else {
            Toast.makeText(getApplicationContext(), "必填字段不能为空", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick({R.id.ac_serviceChat_iv_expression, R.id.ac_serviceChat_iv_add,R.id.ac_serviceChat_ll_photoGraphic, R.id.ac_serviceChat_ll_takePhoto})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_serviceChat_iv_expression:
                break;
            case R.id.ac_serviceChat_iv_add:
                toggle();
                break;
            case R.id.ac_serviceChat_ll_photoGraphic:
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, CHOOSE_PHOTO);
                break;
            case R.id.ac_serviceChat_ll_takePhoto:
                File outputImage = new File(getExternalCacheDir(), "output_image.jpg");
                try {
                    if (outputImage.exists()) {
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT >= 24) {
                    imageUri = FileProvider.getUriForFile(ServicesChatActivity.this, "com.zthx.npj.file_provider", outputImage);
                } else {
                    imageUri = Uri.fromFile(outputImage);
                }

                Intent intent2 = new Intent("android.media.action.IMAGE_CAPTURE");
                intent2.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent2, TAKE_PHOTO);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        String filePath = getExternalCacheDir() + "/output_image.jpg";
                        Message message=JMessageClient.createSingleImageMessage(chat_name,new File(filePath));
                        JMessageClient.sendMessage(message);
                        getChatMsg();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        Uri selectedImage = data.getData(); //获取系统返回的照片的Uri
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);//从系统表中查询指定Uri对应的照片
                        cursor.moveToFirst();
                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        final String path = cursor.getString(columnIndex);  //获取照片路径
                        cursor.close();
                        Bitmap bitmap = BitmapFactory.decodeFile(path);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        }
    }
    public void toggle() {
        isOpen = !isOpen;
        if (isOpen) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 500);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            acServiceChatLl.setLayoutParams(layoutParams);
        } else {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 200);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            acServiceChatLl.setLayoutParams(layoutParams);
        }
    }
}