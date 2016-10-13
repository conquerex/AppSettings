package com.jongkook.android.sqlitebasic_bbs;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.media.ImageWriter;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


public class EditFragment extends Fragment {
    private static final int BBS_INSERT = -1;
    // private static final int BBS_UPDATE = -1;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    Button btnCancel;
    Button btnSave;
    Button btnImage;

    EditText editTitle;
    EditText editName;
    EditText editContent;
    BbsData data;
    ImageView imageView;

    int bbsNo = -1;

    // 캔슬하거나 저장후에 호출하여 텍스트필드값을 초기화해준다
    public void resetData(){
        editTitle.setText("");
        editName.setText("");
        editContent.setText("");
        this.bbsNo = -1;
    }
    private OnFragmentInteractionListener mListener;

    public EditFragment() {
        // Required empty public constructor
    }

    public static EditFragment newInstance(String param1, String param2) {
        EditFragment fragment = new EditFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit, container, false);
        btnCancel = (Button)view.findViewById(R.id.cancel);
        btnSave = (Button)view.findViewById(R.id.save);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.action(MainActivity.ACTION_GOLIST);
                resetData();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BbsData data = new BbsData();
                data.no = bbsNo;
                data.title = editTitle.getText().toString();
                data.name = editName.getText().toString();
                data.contents = editContent.getText().toString();

                if(bbsNo == BBS_INSERT){
                    DataUtil.insert(getContext(),data);
                }else{
                    DataUtil.update(getContext(),data);
                }
                resetData();
                mListener.action(MainActivity.ACTION_GOLIST_WITH_REFRESH);
            }
        });

        editTitle   = (EditText)view.findViewById(R.id.editTitle);
        editName    = (EditText)view.findViewById(R.id.editName);
        editContent = (EditText)view.findViewById(R.id.editContent);


        btnImage = (Button) view.findViewById(R.id.image);
        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // image를 불러오는 action intent
                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                // 결과값을 넘겨받기 위해 호출
                startActivityForResult(intent, REQ_CODE_IMAGE);
            }
        });
        imageView = (ImageView)view.findViewById(R.id.imageView);

        return view;
    }

    private static final int REQ_CODE_IMAGE = 99;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ_CODE_IMAGE && data != null){
            Uri mediaImage = data.getData();                      // 이미지 URI
            String selections[] = {MediaStore.Images.Media.DATA}; // 실제 이미지 패스 데이터
            Cursor cursor = getContext().getContentResolver().query(
                    mediaImage,selections,null,null,null);
            if(cursor.moveToNext()){
                String imagePath = cursor.getString(0);
                // Log.i("Gallery","image path >>>>>>>> "+imagePath);
                editName.setText(imagePath);
                imageView.setImageBitmap(BitmapFactory.decodeFile(imagePath));
            }
        }
    }

    //    public void onButtonPressed(Fragment fragment) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(fragment);
//        }
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // MainActivity가 OnFragmentInteractionListener를 구현했는지
        if (context instanceof OnFragmentInteractionListener){
            mListener = (OnFragmentInteractionListener) context;
        }else{
            // 미구현시 MA와 통신할 방법이 없으므로 앱을 죽인다.
            throw new RuntimeException();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    // Database에서 bbsno에 해당하는 레코드를 가져와서 화면에 뿌려줌
    public void setData(int bbsno){
        // 화면에 값을 뿌려주는 함수
        BbsData data = DataUtil.select(getContext(), bbsno);

        editTitle.setText(data.title);
        editName.setText(data.name);
        editContent.setText(data.contents);
        this.bbsNo = bbsno;
    }
}
