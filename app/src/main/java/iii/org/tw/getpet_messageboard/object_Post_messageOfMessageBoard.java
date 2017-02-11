package iii.org.tw.getpet_messageboard;

import java.io.Serializable;

/**
 * Created by poloi on 2017/2/11.
 */

public class object_Post_messageOfMessageBoard  implements Serializable  {
    final int boardID = 0;
    final String boardTime="";
    String board_userID;
    int board_animalID;
    String boardContent;

    public int getBoard_animalID() {
        return board_animalID;
    }

    public void setBoard_animalID(int board_animalID) {
        this.board_animalID = board_animalID;
    }

    public String getBoard_userID() {
        return board_userID;
    }

    public void setBoard_userID(String board_userID) {
        this.board_userID = board_userID;
    }

    public String getBoardContent() {
        return boardContent;
    }

    public void setBoardContent(String boardContent) {
        this.boardContent = boardContent;
    }

    public int getBoardID() {
        return boardID;
    }

    public String getBoardTime() {
        return boardTime;
    }
}
