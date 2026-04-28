package ui;


import domain.User;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Begin {
    Scanner sc = new Scanner(System.in);
    ArrayList<User> List = new ArrayList<>();

    //先来打印启动ui
    public  void begin() {

        while (true) {
            System.out.println("-------------------------");
            System.out.println("----------1 注册----------");
            System.out.println("----------2 登录----------");
            System.out.println("----------3 退出----------");
            System.out.println("-------------------------");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    register(List);
                    break;
                case 2:
                    login(List);
                    break;
                case 3:
                    System.out.println("欢迎下次光临！");
                    System.exit(0);
                default:
                    System.out.println("输入错误，请重新输入！");
                    break;

            }
        }
    }

    public void register(ArrayList<User> list) {
        System.out.println("注册页面");
        User U = new User();
        //注册用户名
        while (true) {
            System.out.print("请输入用户名：");
            U.setName(sc.next());
            //判断用户名是否合规
            if (check(3, 16, U.getName())) {
                System.out.println("用户名长度不合规，请重新输入！");
                continue;
            }
            //检查用户名是否只有字母和数字构成
            if (!checkUser(U.getName())) {
                System.out.println("用户名只能包含字母和数字，请重新输入！");
                continue;
            }
            //判断用户名是否已经存在
            if (contName(list,U.getName())){
                System.out.println("有重复名字");
                continue;
            }
            break;
        }
        //注册密码
        while (true) {
            System.out.print("请输入密码：");
            U.setPassword(sc.next());
            //检测密码长度时候正确
            if (check(3, 8, U.getPassword())) {
                System.out.println("密码长度不对，请在8~20位");
                continue;
            }
            //检测密码格式是否正确
            if (!checkPassword(U.getPassword())){
                System.out.println("密码格式错误");
                continue;
            }
            //重新输入密码
            System.out.print("请再次输入密码:");
            String password = sc.next();
            if(!password.equals(U.getPassword())){
                System.out.println("两遍密码输入不相同");
                continue;
            }
            break;
        }//密码循环
        //将用户名和密码录入到系统中
        List.add(U);
        System.out.println("用户注册成功");
    }//注册完成

    public void login(ArrayList<User> list) {
        System.out.println("登录页面");
        int cnt;
        //输入用户名
        System.out.println("请输入用户名：");
        String name = sc.next();
        //检测库里是否有该名字
        if(!contName(list,name)){
            System.out.println("该账户不存在！");
            return;
        }
        //找出名字所对应的索引
        cnt=cntList(List,name);

        //确定账号是否被锁定
        User U = list.get(cnt);
        if(U.isState()){
            System.out.println("该账号以被锁定，请重新注册");
            return;
        }

        //登录密码
        for (int i=3;i>0;i--) {
            System.out.print("请输入密码：");
            String password1 = sc.next();
            System.out.print("请在此输入密码：");
            String password2 = sc.next();
            //验证码
            //生成验证码
            User p ;
            p = list.get(cnt);
            while (true) {
                String code= getTest();
                System.out.print("请输入验证码"+code);
                if (!code.equals(sc.next())){
                    System.out.println("验证码输入错误");
                }else {
                    System.out.println("验证码输入正确");
                    break;
                }
            }
            //检测密码
            if(!p.getPassword().equals(password2)){
                System.out.println("两次密码输入不同");
            }
            else if(!p.getPassword().equals(password1)){
                System.out.println("密码输入错误");
            }
            else {
                System.out.println("登录完成");
                //进入游戏界面
                System.out.println("进入游戏");
                return;
            }
            System.out.println("还剩下"+i+"次重试,账号将被锁定");
        }
        U.setState(false);
        list.set(cnt, U);
        System.out.println("该账户被锁定");
    }//登录页面结束

    public Boolean check(int min, int max, String len) {
        return len.length() < min || len.length() > max;
    }

    public boolean contName( ArrayList<User> list,String name) {
        for (User U : List) {
            //现在要将name与库里面的name比较寻找是否有重复
            if (U.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public int[] cnt(String s) {
        int numcnt=0;
        int charcnt=0;
        int othercnt=0;
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i)>='0' && s.charAt(i)<='9'){
                numcnt++;
            }
            else if (s.charAt(i)>='a' && s.charAt(i)<='z'||s.charAt(i)>='A' && s.charAt(i)<='Z'){
                charcnt++;
            }
            else othercnt++;
        }
        return new int[]{numcnt, charcnt, othercnt};
    }

    public boolean checkUser(String name) {
        int[] arr = cnt(name);
        return arr[0]>=0&&arr[1]>0&&arr[2]==0;
    }

    public boolean checkPassword(String password) {
        int[] arr = cnt(password);
        return arr[0]>0&&arr[1]>0&&arr[2]==0;
    }

    //找出名字的索引
    public int cntList(ArrayList<User> list,String name){
        for (int i = 0; i < List.size(); i++) {
            User U = List.get(i);
            if (U.getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public String getTest() {
        //五位字符，四个字母（不限制大小写，重复）加一个数字
        ArrayList<Character> list =new ArrayList<>();
        for(int i=0;i<26;i++){
            list.add((char)('a'+i));
            list.add((char)('A'+i));
        }
        char[] Add=new char[5];
        Random r = new Random();
        for(int i=0;i<5;i++){
            Add[i]=list.get(r.nextInt(list.size()));
        }
        //将一个替换成数字
        int i=r.nextInt(0,5);
        Add[i]= (char) r.nextInt('0',10+'0');
        return new String(Add);
    }
}//类
