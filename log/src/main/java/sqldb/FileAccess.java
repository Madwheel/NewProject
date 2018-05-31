package sqldb;

/**
 * 描述：文件存取
 * 作者：小辉
 * 时间：2018/01/26
 */

public class FileAccess {
    public FileAccess() {
    }

    private static volatile FileAccess fileAccess;

    //安全的单例模式
    public static FileAccess getFileAccess() {
        if (fileAccess == null) {
            synchronized (FileAccess.class) {
                if (fileAccess == null) {
                    fileAccess = new FileAccess();
                }
            }
        }
        return fileAccess;
    }

}
