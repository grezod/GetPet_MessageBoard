package iii.org.tw.getpet_messageboard;

import java.io.Serializable;

/**
 * Created by poloi on 2017/2/11.
 */

public class object_Get_messageOfMessageBoard implements Serializable {

    int boardID = 0;
    String boardTime="";
    String board_userID;
    String UserName;
    String Email;
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

    public void setBoardID(int boardID) {
        this.boardID = boardID;
    }

    public String getBoardTime() {
        return boardTime;
    }

    public void setBoardTime(String boardTime) {
        this.boardTime = boardTime;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }
}
