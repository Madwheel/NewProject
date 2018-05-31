package utils;

import java.io.File;

/**
 * 描述：
 * 作者：小辉
 * 时间：2018/05/08
 */

public class FileManager implements IFile {
    @Override
    public String getFilePath(FilePathType type) {
        return null;
    }

    @Override
    public File getFile(String filePath) {
        return null;
    }

    @Override
    public void saveFile(String filePath, File file) {

    }

    @Override
    public void saveFile(String filePath, File file, ISaveFileCallBack callBack) {

    }
}
