package com.hemooffice.suopu.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * IO 工具类, 读写文件
 *
 * @author xietansheng
 */
public class IOUtils {
    private static final Logger logger = LoggerFactory.getLogger(IOUtils.class);

    public static void writeFile(String data, File file) throws IOException {
        OutputStream out = null;
        try {
            out = new FileOutputStream(file);
            out.write(data.getBytes());
            out.flush();
        } finally {
            close(out);
        }
    }

    public static String readFile(File file) throws IOException {
        InputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            in = new FileInputStream(file);
            out = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int len = -1;
            while ((len = in.read(buf)) != -1) {
                out.write(buf, 0, len);
            }
            out.flush();
            byte[] data = out.toByteArray();
            return new String(data);
        } finally {
            close(in);
            close(out);
        }
    }

    public static void close(Closeable c) {
        if (c != null) {
            try {
                c.close();
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
    }

    /**
     * MultipartFile转base64
     * @param file
     * @return
     * @throws Exception
     */
    public static String  fileToBase64(MultipartFile file) throws Exception{
        BASE64Encoder base64Encoder =new BASE64Encoder();
        String base64EncoderImg = "data:image/png;base64,"+ base64Encoder.encode(file.getBytes());
        return base64EncoderImg;
    }
    /**
     * 将Object对象里面的属性和值转化成Map对象
     *
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    public static Map<String, Object> objectToMap(Object obj) {
        Map<String, Object> map = new HashMap<>();
        if (obj == null) {
            return map;
        }
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(obj));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 数组转为s字符串
     * @return
     */
    public static String arrayToString(int[] arr){

        StringBuffer str = new StringBuffer();

        for(int i = 0;i< arr.length;i++){
            if(i == arr.length - 1){
                str.append(arr[i]);
            }else{
                str.append(arr[i]+",");
            }
        }
        return str.toString();
    }
}

