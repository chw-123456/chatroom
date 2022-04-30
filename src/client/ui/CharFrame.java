package client.ui;
import client.ClientThread;
import client.DataBuffer;
import client.model.entity.MyCellRenderer;
import client.model.entity.OnlineUserListModel;
import client.util.ClientUtil;
import common.model.entity.FileInfo;
import common.model.entity.Message;
import common.model.entity.Request;
import common.model.entity.User;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class CharFrame {
    private static final long serialVersionUID = -3426717670093483287L;
    //聊天对方的信息Label
    private JLabel otherInfoLbl;
    //当前用户信息Lbl
    private JLabel currentUserLbl;
    //聊天信息列表区域
    public static JTextArea msgListArea;
    //要发送的信息区域
    public static JTextArea sendArea;
    //在线用户列表
    public static JList onlineCountLbl;
    //准备发送文件
    public static FileInto sendFile;
    //私聊复选框
    public JCheckBox rybqBtn;

    public CharFrame(){
        this.init();
        //调用任意已注册 WindowListener 的对象后自动隐藏并释放该窗体。
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    //初始化
    public void init(){
    
    }
}
