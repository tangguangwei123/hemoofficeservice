package com.hemooffice.suopu.utils;

import org.springframework.util.ResourceUtils;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件工具类
 */
public class FileUtil {
    /**
     * 获取项目根路径
     *
     * @return
     */
    public static String getResourceBasePath() {
        // 获取跟目录
        File path = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            // nothing to do
        }
        if (path == null || !path.exists()) {
            path = new File("");
        }
        String pathStr = path.getAbsolutePath();
        // 如果是在eclipse中运行，则和target同级目录,如果是jar部署到服务器，则默认和jar包同级
        pathStr = pathStr.replace("\\target\\classes", "");
        return pathStr;
    }
    /**
     * 获取指定目录下的文件名称列表
     * @param strPath
     * @return
     */
    public static List<String> getFileList(String strPath){
        //文件名称列表
        List fileList = new ArrayList();

        File fileDir = new File(strPath);
        if (null != fileDir && fileDir.isDirectory()) {
            File[] files = fileDir.listFiles();

            if (null != files) {
                for (int i = 0; i < files.length; i++) {
                    // 如果是文件夹 那就不管你了
                    if (files[i].isDirectory()) {
                        //getFileList(files[i].getAbsolutePath());
                    } else {
                        fileList.add(files[i].getName());
                    }
                }
            }
        }
        return fileList;
    }

    /**
     * 文件下载
     * @param filename
     * @param res
     * @throws IOException
     */
    public static void download(String filename, HttpServletResponse res) throws IOException {
        // 发送给客户端的数据
        OutputStream outputStream = res.getOutputStream();
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        // 读取filename
        bis = new BufferedInputStream(new FileInputStream(new File("./filedownload/" + filename)));

        int i = bis.read(buff);
        while (i != -1) {
            outputStream.write(buff, 0, buff.length);
            outputStream.flush();
            i = bis.read(buff);
        }
    }

    /**
     *  根据路径删除指定的目录或文件，无论存在与否
     *@param sPath  要删除的目录或文件
     *@return 删除成功返回 true，否则返回 false。
     */
    public static boolean deleteFolder(String sPath) {
        File file = new File(sPath);
        // 判断目录或文件是否存在
        if (!file.exists()) {  // 不存在返回 false
            return false;
        } else {
            // 判断是否为文件
            if (file.isFile()) {  // 为文件时调用删除文件方法
                return deleteFile(sPath);
            }else {
                return false;
            }
        }
    }

    /**
     * 删除单个文件
     * @param   sPath    被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String sPath) {
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            return true;
        }else {
            return false;
        }
    }
}


