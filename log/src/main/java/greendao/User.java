package greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 描述：
 * 作者：小辉
 * 时间：2018/05/21
 */

@Entity
public class User {
    @Id(autoincrement = true)
    private Long id;//主键  自增长
    @NotNull   //不许为空
    private String name;
    private String age;
    private String content;
    private String content1;
    @Generated(hash = 925353593)
    public User(Long id, @NotNull String name, String age, String content,
            String content1) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.content = content;
        this.content1 = content1;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAge() {
        return this.age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getContent1() {
        return this.content1;
    }
    public void setContent1(String content1) {
        this.content1 = content1;
    }
}
