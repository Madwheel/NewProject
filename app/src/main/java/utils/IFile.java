package utils;

import java.io.File;

/**
 * 描述：
 * 作者：小辉
 * 时间：2018/05/08
 */

public interface IFile {
    String getFilePath(FilePathType type);

    File getFile(String filePath);

    void saveFile(String filePath, File file);

    void saveFile(String filePath, File file, ISaveFileCallBack callBack);

    class FilePathType {
    }
}
