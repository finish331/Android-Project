package com.e.user.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;


public class first extends Fragment {
    private static final int MENU_MONTH1 = Menu.FIRST,
            MENU_MONTH2 = Menu.FIRST + 1,
            MENU_MONTH3 = Menu.FIRST + 2,
            MENU_PLAYGAME = Menu.FIRST + 3;
    private RelativeLayout mRelativeLayout;

    private EditText firstNum,secondNum,thirdNum;
    private TextView invoiceTxt,messenge,invoiceNum;
    private ImageView img;
    private Button btnOK,btnClear;
    private boolean clear = false;
    private int[] special1 = {1,2,3,4,2,1,2,6},special2 = {8,0,7,4,0,9,7,7};
    private int[] special3 = {2,1,7,3,5,2,6,6},special4 = {9,1,8,7,4,2,5,4};
    private int[] head1 = {3,6,8,2,2,6,3,9},head2 = {3,8,7,8,6,2,3,8},head3 = {8,7,2,0,4,8,3,7};
    private int[] head4 = {5,6,0,6,5,2,0,9},head5 = {0,5,7,3,9,3,4,0},head6 = {6,9,0,0,1,6,1,2};
    private int[] extra1 = {9,9,1},extra2 = {7,1,5};
    private int[] extra3 = {5,9,1},extra4 = {3,4,2};
    private int[] input = {};
    private int type,invoiceNumber = 0; //1為當前月份 0為上一期 2為一起兌

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);

        input = new int[3];

        mRelativeLayout = (RelativeLayout)getView().findViewById(R.id.relative);
        firstNum = (EditText)getView().findViewById(R.id.firstInput);
        secondNum = (EditText)getView().findViewById(R.id.secondInput);
        thirdNum = (EditText)getView().findViewById(R.id.thirdInput);
        invoiceTxt = (TextView)getView().findViewById(R.id.invoiceText);
        messenge = (TextView)getView().findViewById(R.id.messenge);
        invoiceNum = (TextView)getView().findViewById(R.id.invoiceNum);
        img = (ImageView)getView().findViewById(R.id.imageView);

        btnClear = (Button) getView().findViewById(R.id.btnClear);
        btnClear.setOnClickListener(btnClearOnClick);

        firstNum.requestFocus();

        IBinder mIBinder = getActivity().getCurrentFocus().getWindowToken();
        InputMethodManager mInputMethodManager = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        mInputMethodManager.hideSoftInputFromWindow(mIBinder, InputMethodManager.HIDE_NOT_ALWAYS);

        //下方為顯示虛擬鍵盤
        mInputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

        //建立文字監聽
        TextWatcher mTextWatcher = new TextWatcher()
        {
            @Override
            public void afterTextChanged(Editable s)
            {
                if(!(firstNum.getText().toString().matches("")) && !(secondNum.getText().toString().matches("")) && !(thirdNum.getText().toString().matches(""))){
                    MainActivity activity = (MainActivity) getActivity();
                    Bundle results = activity.getMyData();
                    type = results.getInt("type");
                    input[0] = Integer.parseInt(firstNum.getText().toString());
                    input[1] = Integer.parseInt(secondNum.getText().toString());
                    input[2] = Integer.parseInt(thirdNum.getText().toString());
                    if (type == 1) {
                        CompareInvoice();
                        invoiceNumber++;
                        invoiceNum.setText("共 " + String.valueOf(invoiceNumber) + " 張");
                    }
                    if (type == 0) {
                        CompareInvoice2();
                        invoiceNumber++;
                        invoiceNum.setText("共 " + String.valueOf(invoiceNumber) + " 張");
                    }
                    if (type == 2) {
                        CompareInvoice3();
                        invoiceNumber++;
                        invoiceNum.setText("共 " + String.valueOf(invoiceNumber) + " 張");
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,int after)
            {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before,int count)
            {
                //如果字數達到1，取消自己焦點，下一個EditText取得焦點
                if(firstNum.getText().toString().length()==1)
                {
                    firstNum.clearFocus();
                    secondNum.requestFocus();
                }

                if(secondNum.getText().toString().length()==1)
                {
                    secondNum.clearFocus();
                    thirdNum.requestFocus();
                }

                if(thirdNum.getText().toString().length()==1)
                {
                    thirdNum.clearFocus();
                    firstNum.requestFocus();
                }
            }
        };

        //加入文字監聽
        firstNum.addTextChangedListener(mTextWatcher);
        secondNum.addTextChangedListener(mTextWatcher);
        thirdNum.addTextChangedListener(mTextWatcher);

        firstNum.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        secondNum.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        thirdNum.setInputType(EditorInfo.TYPE_CLASS_PHONE);
    }

    private View.OnClickListener btnClearOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            firstNum.setText("");
            secondNum.setText("");
            thirdNum.setText("");
            messenge.setText("輸入發票末三碼：");
            invoiceTxt.setText("");
            img.setImageResource(R.drawable.transparent);
        }
    };
    private void CompareInvoice(){
        String output="",temp;

        if(special1[7] == input[2] && special1[6] == input[1] && special1[5] == input[0]){
            messenge.setText("注意特別獎！6/6~9/5領獎");
            for(int i=0;i<special1.length;i++){
                temp = String.valueOf(special1[i]);
                output = output + temp;
            }
            output = "中獎號碼：" + output + '\n' + "全部數字相同特獎1000萬";
            invoiceTxt.setText(output);
        }
        else if(special2[7] == input[2] && special2[6] == input[1] && special2[5] == input[0]){
            messenge.setText("注意特獎！6/6~9/5領獎");
            for(int i=0;i<special2.length;i++){
                temp = String.valueOf(special2[i]);
                output = output + temp;
            }
            output = "中獎號碼：" + output + '\n' + "全部數字相同特獎200萬";
            invoiceTxt.setText(output);
        }
        else if(head1[7] == input[2] && head1[6] == input[1] && head1[5] == input[0]){
            messenge.setText("恭喜中獎！6/6~9/5領獎");
            img.setImageResource(R.drawable.photo);
            for(int i=0;i<head1.length;i++){
                temp = String.valueOf(head1[i]);
                output = output + temp;
            }
            output = "中獎號碼：" + output;
            invoiceTxt.setText(output);
        }
        else if(head2[7] == input[2] && head2[6] == input[1] && head2[5] == input[0]){
            messenge.setText("恭喜中獎！6/6~9/5領獎");
            img.setImageResource(R.drawable.photo);
            for(int i=0;i<head2.length;i++){
                temp = String.valueOf(head2[i]);
                output = output + temp;
            }
            output = "中獎號碼：" + output;
            invoiceTxt.setText(output);
        }
        else if(head3[7] == input[2] && head3[6] == input[1] && head3[5] == input[0]){
            messenge.setText("恭喜中獎！6/6~9/5領獎");
            img.setImageResource(R.drawable.photo);
            for(int i=0;i<head3.length;i++){
                temp = String.valueOf(head3[i]);
                output = output + temp;
            }
            output = "中獎號碼：" + output;
            invoiceTxt.setText(output);
        }
        else if(extra1[2] == input[2] && extra1[1] == input[1] && extra1[0] == input[0]){
            messenge.setText("恭喜中獎！6/6~9/5領獎");
            for(int i=0;i<extra1.length;i++){
                temp = String.valueOf(extra1[i]);
                output = output + temp;
            }
            output = "中獎號碼：" + output;
            invoiceTxt.setText(output);
        }
        else if(extra2[2] == input[2] && extra2[1] == input[1] && extra2[0] == input[0]){
            messenge.setText("恭喜中獎！6/6~9/5領獎");
            for(int i=0;i<extra2.length;i++){
                temp = String.valueOf(extra2[i]);
                output = output + temp;
            }
            output = "中獎號碼：" + output;
            invoiceTxt.setText(output);
        }
        else{
            messenge.setText("很可惜！繼續加油");
        }
    }
    private void CompareInvoice2(){
        String output="",temp;

        if(special3[7] == input[2] && special3[6] == input[1] && special3[5] == input[0]){
            messenge.setText("注意特別獎！4/6~7/5領獎");
            for(int i=0;i<special3.length;i++){
                temp = String.valueOf(special3[i]);
                output = output + temp;
            }
            output = "中獎號碼：" + output + '\n' + "全部數字相同特獎1000萬";
            invoiceTxt.setText(output);
        }
        else if(special4[7] == input[2] && special4[6] == input[1] && special4[5] == input[0]){
            messenge.setText("注意特獎！4/6~7/5領獎");
            for(int i=0;i<special4.length;i++){
                temp = String.valueOf(special4[i]);
                output = output + temp;
            }
            output = "中獎號碼：" + output + '\n' + "全部數字相同特獎200萬";
            invoiceTxt.setText(output);
        }
        else if(head4[7] == input[2] && head4[6] == input[1] && head4[5] == input[0]){
            messenge.setText("恭喜中獎！4/6~7/5領獎");
            img.setImageResource(R.drawable.photo);
            for(int i=0;i<head4.length;i++){
                temp = String.valueOf(head4[i]);
                output = output + temp;
            }
            output = "中獎號碼：" + output;
            invoiceTxt.setText(output);
        }
        else if(head5[7] == input[2] && head5[6] == input[1] && head5[5] == input[0]){
            messenge.setText("恭喜中獎！4/6~7/5領獎");
            img.setImageResource(R.drawable.photo);
            for(int i=0;i<head5.length;i++){
                temp = String.valueOf(head5[i]);
                output = output + temp;
            }
            output = "中獎號碼：" + output;
            invoiceTxt.setText(output);
        }
        else if(head6[7] == input[2] && head6[6] == input[1] && head6[5] == input[0]){
            messenge.setText("恭喜中獎！4/6~7/5領獎");
            img.setImageResource(R.drawable.photo);
            for(int i=0;i<head6.length;i++){
                temp = String.valueOf(head6[i]);
                output = output + temp;
            }
            output = "中獎號碼：" + output;
            invoiceTxt.setText(output);
        }
        else if(extra3[2] == input[2] && extra3[1] == input[1] && extra3[0] == input[0]){
            messenge.setText("恭喜中獎！4/6~7/5領獎");
            for(int i=0;i<extra3.length;i++){
                temp = String.valueOf(extra3[i]);
                output = output + temp;
            }
            output = "中獎號碼：" + output;
            invoiceTxt.setText(output);
        }
        else if(extra4[2] == input[2] && extra4[1] == input[1] && extra4[0] == input[0]){
            messenge.setText("恭喜中獎！4/6~7/5領獎");
            for(int i=0;i<extra2.length;i++){
                temp = String.valueOf(extra2[i]);
                output = output + temp;
            }
            output = "中獎號碼：" + output;
            invoiceTxt.setText(output);
        }
        else{
            messenge.setText("很可惜！繼續加油");
        }
    }
    private void CompareInvoice3(){
        String output="",temp;

        if(special1[7] == input[2] && special1[6] == input[1] && special1[5] == input[0]){
            messenge.setText("注意特別獎！6/6~9/5領獎");
            for(int i=0;i<special1.length;i++){
                temp = String.valueOf(special1[i]);
                output = output + temp;
            }
            output = "中獎號碼：" + output + '\n' + "全部數字相同特獎1000萬";
            invoiceTxt.setText(output);
        }
        else if(special2[7] == input[2] && special2[6] == input[1] && special2[5] == input[0]){
            messenge.setText("注意特獎！6/6~9/5領獎");
            for(int i=0;i<special2.length;i++){
                temp = String.valueOf(special2[i]);
                output = output + temp;
            }
            output = "中獎號碼：" + output + '\n' + "全部數字相同特獎200萬";
            invoiceTxt.setText(output);
        }
        else if(head1[7] == input[2] && head1[6] == input[1] && head1[5] == input[0]){
            messenge.setText("恭喜中獎！6/6~9/5領獎");
            img.setImageResource(R.drawable.photo);
            for(int i=0;i<head1.length;i++){
                temp = String.valueOf(head1[i]);
                output = output + temp;
            }
            output = "中獎號碼：" + output;
            invoiceTxt.setText(output);
        }
        else if(head2[7] == input[2] && head2[6] == input[1] && head2[5] == input[0]){
            messenge.setText("恭喜中獎！6/6~9/5領獎");
            img.setImageResource(R.drawable.photo);
            for(int i=0;i<head2.length;i++){
                temp = String.valueOf(head2[i]);
                output = output + temp;
            }
            output = "中獎號碼：" + output;
            invoiceTxt.setText(output);
        }
        else if(head3[7] == input[2] && head3[6] == input[1] && head3[5] == input[0]){
            messenge.setText("恭喜中獎！6/6~9/5領獎");
            img.setImageResource(R.drawable.photo);
            for(int i=0;i<head3.length;i++){
                temp = String.valueOf(head3[i]);
                output = output + temp;
            }
            output = "中獎號碼：" + output;
            invoiceTxt.setText(output);
        }
        else if(extra1[2] == input[2] && extra1[1] == input[1] && extra1[0] == input[0]){
            messenge.setText("恭喜中獎！6/6~9/5領獎");
            for(int i=0;i<extra1.length;i++){
                temp = String.valueOf(extra1[i]);
                output = output + temp;
            }
            output = "中獎號碼：" + output;
            invoiceTxt.setText(output);
        }
        else if(extra2[2] == input[2] && extra2[1] == input[1] && extra2[0] == input[0]){
            messenge.setText("恭喜中獎！6/6~9/5領獎");
            for(int i=0;i<extra2.length;i++){
                temp = String.valueOf(extra2[i]);
                output = output + temp;
            }
            output = "中獎號碼：" + output;
            invoiceTxt.setText(output);
        }
        else if(special3[7] == input[2] && special3[6] == input[1] && special3[5] == input[0]){
            messenge.setText("注意特別獎！4/6~7/5領獎");
            for(int i=0;i<special3.length;i++){
                temp = String.valueOf(special3[i]);
                output = output + temp;
            }
            output = "中獎號碼：" + output + '\n' + "全部數字相同特獎1000萬";
            invoiceTxt.setText(output);
        }
        else if(special4[7] == input[2] && special4[6] == input[1] && special4[5] == input[0]){
            messenge.setText("注意特獎！4/6~7/5領獎");
            for(int i=0;i<special4.length;i++){
                temp = String.valueOf(special4[i]);
                output = output + temp;
            }
            output = "中獎號碼：" + output + '\n' + "全部數字相同特獎200萬";
            invoiceTxt.setText(output);
        }
        else if(head4[7] == input[2] && head4[6] == input[1] && head4[5] == input[0]){
            messenge.setText("恭喜中獎！4/6~7/5領獎");
            img.setImageResource(R.drawable.photo);
            for(int i=0;i<head4.length;i++){
                temp = String.valueOf(head4[i]);
                output = output + temp;
            }
            output = "中獎號碼：" + output;
            invoiceTxt.setText(output);
        }
        else if(head5[7] == input[2] && head5[6] == input[1] && head5[5] == input[0]){
            messenge.setText("恭喜中獎！4/6~7/5領獎");
            img.setImageResource(R.drawable.photo);
            for(int i=0;i<head5.length;i++){
                temp = String.valueOf(head5[i]);
                output = output + temp;
            }
            output = "中獎號碼：" + output;
            invoiceTxt.setText(output);
        }
        else if(head6[7] == input[2] && head6[6] == input[1] && head6[5] == input[0]){
            messenge.setText("恭喜中獎！4/6~7/5領獎");
            img.setImageResource(R.drawable.photo);
            for(int i=0;i<head6.length;i++){
                temp = String.valueOf(head6[i]);
                output = output + temp;
            }
            output = "中獎號碼：" + output;
            invoiceTxt.setText(output);
        }
        else if(extra3[2] == input[2] && extra3[1] == input[1] && extra3[0] == input[0]){
            messenge.setText("恭喜中獎！4/6~7/5領獎");
            for(int i=0;i<extra3.length;i++){
                temp = String.valueOf(extra3[i]);
                output = output + temp;
            }
            output = "中獎號碼：" + output;
            invoiceTxt.setText(output);
        }
        else if(extra4[2] == input[2] && extra4[1] == input[1] && extra4[0] == input[0]){
            messenge.setText("恭喜中獎！4/6~7/5領獎");
            for(int i=0;i<extra2.length;i++){
                temp = String.valueOf(extra2[i]);
                output = output + temp;
            }
            output = "中獎號碼：" + output;
            invoiceTxt.setText(output);
        }
        else{
            messenge.setText("很可惜！繼續加油");
        }
    }

    String intArrayToArrayString(int[] array) {
        // 利用 Arrays.toString 可以超簡單輸出 array
        // 輸出結果：[4, 2, 5, 1, 5, 2, 4, 3]
        String arrayString = Arrays.toString(array);
        return arrayString;
    }
}
