package ui;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Begin {
    String user;
    String password;
    Scanner sc = new Scanner(System.in);
    //先来打印启动ui
    public void begin(){
        System.out.println("-------------------------");
        System.out.println("----------1 注册----------");
        System.out.println("----------2 登录----------");
        System.out.println("----------3 退出----------");
        System.out.println("-------------------------");
        switch (sc.nextInt()) {
            case 1:
                Register();
                break;
    }
    public void Register(){
        //1.录入用户名
        //2.录入密码，密码输入两遍，且有数字和字母组成，3~8位数
        System.out.println("-------------------------");
        while(user.length() < 3 || user.length() > 8 || !user.matches(".*[a-zA-Z].*")) {
            System.out.print("用户名:");
            user = sc.nextLine();
            if(!user.matches(".*[a-zA-Z].*")|| user.length() < 3 || user.length() > 8) {
                System.out.println("用户名必须包含字母,且长度在3~8之间！");
            }
        }
        System.out.print("密码:");
        password = sc.nextLine();
        //密码检测
        if(password.length() < 3 || password.length() > 8 || !password.matches(".*[a-zA-Z].*") || !password.matches(".*[0-9].*")) {
            System.out.println("密码不符合要求，请重新输入！");

        }
        System.out.print("再次输入密码:");
        //验证密码是否一致
        if(!password.equals(sc.nextLine())) {
            System.out.println("两次密码不一致，请重新输入！");
            Register();
        }

    }
}
