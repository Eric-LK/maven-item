package test.file;

import lombok.extern.slf4j.Slf4j;

import java.io.*;


@Slf4j
public class FileUtil {


    public static void randomAccessFile(File in, File out, Long seek) throws IOException {
        try (
                RandomAccessFile raFile = new RandomAccessFile(out, "rw");
                BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(in))
        ) {
            // 以读写的方式打开目标文件
            raFile.seek(seek);
            byte[] buf = new byte[1024];
            int length;
            while ((length = inputStream.read(buf)) != -1) {
                raFile.write(buf, 0, length);
            }
        }
    }


    /*
     * 删除单个文件
     *
     * @param sPath 被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            return file.delete();
        }
        return flag;
    }


    /**
     * 删除目录（文件夹）以及目录下的文件
     *
     * @param sPath 被删除目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String sPath) {
        // 如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;
        // 删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (File file : files) {
            // 删除子文件
            if (file.isFile()) {
                flag = deleteFile(file.getAbsolutePath());
            } // 删除子目录
            else {
                flag = deleteDirectory(file.getAbsolutePath());
            }
            if (!flag)
                break;
        }
        if (!flag)
            return false;
        // 删除当前目录
        return dirFile.delete();
    }
}
