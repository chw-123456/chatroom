package client.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import client.DataBuffer;
import client.util.ClientUtil;
import com.sun.net.httpserver.Request;
import common.model.entity.Request;
import common.model.entity.Response;
import common.model.entity.ResponseStatus;
import common.model.entity.User;

//登陆界面
public class LoginFrame extends JFrame{
    private static final long serialVersionUID = -3426717670093483287L;
    private JTextField idTxt;
    private JPasswordField pwdFld;
    public LoginFrame(){
        this.init();
        setVisible(true);
    }

    public void init(){
        this.setTitle("登录");
        this.setSize(430,330);

        //设置默认窗口在屏幕中央
        int x = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int y = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((x-this.getWidth())/2,(y-this.getHeight())/2);

        //不允许用户改变窗口大小；
        this.setResizable(false);

        //把logo放在JFrame的上面
        Icon icon = new ImageIcon("images/logo.png");
        JLabel label = new JLabel(icon);
        label.setPreferredSize(new Dimension(430,150));
        this.add(label,BorderLayout.NORTH);

        //登录信息
        JPanel mainPanel = new JPanel();
        // 具有“浮雕化”外观效果的边框(效果为凹陷)
        Border border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        mainPanel.setBorder(BorderFactory.createTitledBorder(border,"输入登录信息",TitledBorder.CENTER,TitledBorder.TOP));
        this.add(mainPanel,BorderLayout.CENTER);
        mainPanel.setLayout(null);

        JLabel nameLbl = new JLabel("账号");
        nameLbl.setBounds(110,30,70,22);
        mainPanel.add(nameLbl);
        idTxt = new JTextField();
        idTxt.setBounds(150,30,150,22);
        idTxt.requestFocusInWindow();//用户名获得焦点
        mainPanel.add(idTxt);

        JLabel pwdLbl = new JLabel("密码");
        pwdLbl.setBounds(110,60,40,22);
        mainPanel.add(pwdLbl);
        pwdFld = new JPasswordField();
        pwdFld.setBounds(150,60,150,22);
        mainPanel.add(pwdFld);

        //按钮面板放置在JFrame的下面
        JPanel btnPanel = new JPanel();
        this.add(btnPanel,BorderLayout.SOUTH);
        btnPanel.setLayout(new BorderLayout());
        btnPanel.setBorder(new EmptyBorder(2,8,4,8));

        JButton registenBtn = new JButton("注册");
        btnPanel.add(registenBtn,BorderLayout.WEST);
        JButton submitBtn = new JButton("登录");
        btnPanel.add(submitBtn,BorderLayout.EAST);

        //关闭窗口
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                Request req = new Request();
                req.setAction("exit");
                try{
                    ClientUtil.sendTextRequest(req);
                }catch(IOException ex){
                    ex.printStackTrace();
                }finally {
                    System.exit(0);
                }
            }
        });

        //注册
        registenBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterFrame();
            }
        });

        //登录
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
    }
    //登录
    private void login(){
        if(idTxt.getText().length()==0||pwdFld.getPassword().length==0){
            JOptionPane.showMessageDialog(LoginFrame.this,
                    "请输入账号密码！，" ,
                    "输入有误",JOptionPane.ERROR_MESSAGE);
            idTxt.requestFocusInWindow();
            return;
        }
        //创建请求
        Request req = new Request();
        req.setAction("userLogin");
        req.setAttribute("id", idTxt.getText());
        req.setAttribute("password", new String(pwdFld.getPassword()));
        //获取响应
        Response response = null;
        try {
            response = ClientUtil.sendTextRequest(req);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

}
