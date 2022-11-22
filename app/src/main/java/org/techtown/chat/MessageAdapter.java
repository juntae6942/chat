package org.techtown.chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<Message> list;
    /*
        위의 list 변수에는 null이 초기화되어 있으므로 list에 값을 넣어줄 수 있는 생성자나 메소드 정의해야 함
    */

    public MessageAdapter(List<Message> list) {
        this.list = list;
    }

    public void addList(Message ms){
                list.add(ms);
    }
    public void setList(List<Message> list){
        this.list=list;
    }
    public Message getMessage(int position){
        return list.get(position);
    }
    public void setMs(int position, Message item){
            list.set(position,item);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (Objects.requireNonNull(MessageType.of(viewType))) {
            case LEFT_CONTENTS:
                return new LeftViewHolder(inflater.inflate(R.layout.message_left_item, parent, false));
            case RIGHT_CONTENTS:
                return new RightViewHolder(inflater.inflate(R.layout.message_right_item, parent, false));
            default:
                return new CenterViewHolder(inflater.inflate(R.layout.message_center_item, parent, false));
        }
    }

    /*
        이 메소드를 구현하시오.
    */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof RightViewHolder){
            ((RightViewHolder) holder).messageText.setText(list.get(position).getMessage());
            ((RightViewHolder)holder).datetimeText.setText(list.get(position).getRegisterDate());
        }else if(holder instanceof LeftViewHolder){
            ((LeftViewHolder)holder).messageText.setText(list.get(position).getMessage());
            ((LeftViewHolder)holder).datetimeText.setText(list.get(position).getRegisterDate());
        }else if(holder instanceof CenterViewHolder){
            ((CenterViewHolder) holder).messageText.setText(list.get(position).getMessage());
            ((CenterViewHolder)holder).datetimeText.setText(list.get(position).getRegisterDate());
        }
    }
    /*
        이 메소드를 구현하시오.
    */
    @Override
    public int getItemCount() {
        return list.size();
    }

    // 이 메소드를 재정의 하면 onCreateViewHolder 메소드의 두번째 파라미터 viewType 변수에 이 메소드의 리턴값이 들어간다.
    @Override
    public int getItemViewType(int position) {
        // 해당 메시지의 message_type 을 리턴
        return list.get(position).getMessageType().getCode();
    }

    private class RightViewHolder extends RecyclerView.ViewHolder {
        private final TextView messageText;
        private final TextView datetimeText;

        public RightViewHolder(@NonNull View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.messageText);
            datetimeText = itemView.findViewById(R.id.datetimeText);
        }
    }
    private class LeftViewHolder extends RecyclerView.ViewHolder {
        private final TextView messageText;
        private final TextView datetimeText;

        public LeftViewHolder(@NonNull View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.messageText);
            datetimeText = itemView.findViewById(R.id.datetimeText);
        }
    }
    private class CenterViewHolder extends RecyclerView.ViewHolder {
        private final TextView messageText;
        private final TextView datetimeText;

        public CenterViewHolder(@NonNull View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.messageText);
            datetimeText = itemView.findViewById(R.id.datetimeText);
        }
    }
}

