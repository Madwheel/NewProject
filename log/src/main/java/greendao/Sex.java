package greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 描述：
 * 作者：小辉
 * 时间：2018/05/24
 */

@Entity
public class Sex {
    @Id(autoincrement = true)
    private Long id;//主键  自增长
    @NotNull   //不许为空
    private String name;
    @Generated(hash = 559114540)
    public Sex(Long id, @NotNull String name) {
        this.id = id;
        this.name = name;
    }
    @Generated(hash = 1648139996)
    public Sex() {
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
}
