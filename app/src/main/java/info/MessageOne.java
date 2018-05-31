package info;

/**
 * 描述：
 * 作者：小辉
 * 时间：2018/05/02
 */

public class MessageOne {
    public String name;
    public int sex;

    public MessageOne() {
    }

    public MessageOne(String name, int sex) {
        this.name = name;
        this.sex = sex;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
