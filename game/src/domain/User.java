package domain;

public class User {
    private String id;
    private String name;
    private String password;
    private boolean state;

    //创建id
    public String createId() {
        //生成随机id
       String id = "Uid" + Math.random()/100000;
        return this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public User( String name, String password) {
        this.id = createId();
        this.name = name;
        this.password = password;
        this.state = true;
    }

    public User() {
        id = createId();
    }
}
