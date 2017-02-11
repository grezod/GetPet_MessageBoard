package iii.org.tw.getpet_messageboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by poloi on 2017/1/30.
 */

public class AdapterOfMessageBoard extends BaseAdapter {
    private LayoutInflater iv_LayoutInflater_myInflater;
    private List<object_Get_messageOfMessageBoard> iv_List_object_Get_messageOfMessageBoard;
    private Context context;
    object_Get_messageOfMessageBoard iv_object_Get_messageOfMessageBoard;

    public AdapterOfMessageBoard(Context p_context,List<object_Get_messageOfMessageBoard> p_List_simplePetDatas) {
        iv_LayoutInflater_myInflater = LayoutInflater.from(p_context);
        this.iv_List_object_Get_messageOfMessageBoard = p_List_simplePetDatas;
        this.context = p_context;
    }

    @Override
    public int getCount() {
        return iv_List_object_Get_messageOfMessageBoard.size();
    }

    @Override
    public Object getItem(int position) {
        return iv_List_object_Get_messageOfMessageBoard.get(position);
    }

    @Override
    public long getItemId(int position) {
        return iv_List_object_Get_messageOfMessageBoard.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View p_convertView, ViewGroup parent) {
        //************
        ViewHolder l_holder = null;
        if(p_convertView==null){
            p_convertView = iv_LayoutInflater_myInflater.inflate(R.layout.adapter_message_board, null);
            l_holder = new ViewHolder(
                    (TextView) p_convertView.findViewById(R.id.txt_UserName),
                    (TextView) p_convertView.findViewById(R.id.txt_boardTime),
                    (TextView) p_convertView.findViewById(R.id.txt_Email),
                    (TextView) p_convertView.findViewById(R.id.txt_boardContent)
            );
            p_convertView.setTag(l_holder);
        }else{
            l_holder = (ViewHolder) p_convertView.getTag();
        }
        //***********
        iv_object_Get_messageOfMessageBoard = (object_Get_messageOfMessageBoard)getItem(position);
        //***********

        l_holder.txt_UserName.setText(iv_object_Get_messageOfMessageBoard.getUserName());
        l_holder.txt_boardTime.setText(iv_object_Get_messageOfMessageBoard.getBoardTime());
        l_holder.txt_Email.setText(iv_object_Get_messageOfMessageBoard.getEmail());
        l_holder.txt_boardContent.setText(iv_object_Get_messageOfMessageBoard.getBoardContent());
        //***


        return p_convertView;
    }

    //************


    //**

    //****
    private class ViewHolder {
        TextView txt_UserName;
        TextView txt_boardTime;
        TextView txt_Email;
        TextView txt_boardContent;


        public ViewHolder(TextView p_txt_UserName, TextView p_txt_boardTime, TextView p_txt_Email, TextView p_txt_boardContent){
            this.txt_UserName = p_txt_UserName;
            this.txt_boardTime = p_txt_boardTime;
            this.txt_Email = p_txt_Email;
            this.txt_boardContent = p_txt_boardContent;

        }
    }
    //*********
}
