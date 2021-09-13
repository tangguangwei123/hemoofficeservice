package com.hemooffice.suopu.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import static org.apache.commons.lang3.StringUtils.deleteWhitespace;

public class CommonUtils {

    /**
     * 用户密码MD5运算存储
     * 用户密码保存规则：用 Hemosoft77加用户密码然后md5 32位大写保存，这样可以吧？验证时也这样处理，这样可以防止简单密码攻击
     * @param password
     * @return
     */
    public static String userPassMd5(String password) throws UnsupportedEncodingException {
        return DigestUtils.md5DigestAsHex(("suopusoft_"+password).getBytes("utf-8")).toUpperCase();
    }
    /**
     * 生成uuid
     * @return
     */
    public static String GetGUID()
    {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 获取汉字全拼
     * @param hanzi
     * @return
     */
    public static String getAllPinyin(String hanzi) {
        //输出格式设置
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        /**
         * 输出大小写设置
         *
         * LOWERCASE:输出小写
         * UPPERCASE:输出大写
         */
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);

        /**
         * 输出音标设置
         *
         * WITH_TONE_MARK:直接用音标符（必须设置WITH_U_UNICODE，否则会抛出异常）
         * WITH_TONE_NUMBER：1-4数字表示音标
         * WITHOUT_TONE：没有音标
         */
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

        /**
         * 特殊音标ü设置
         *
         * WITH_V：用v表示ü
         * WITH_U_AND_COLON：用"u:"表示ü
         * WITH_U_UNICODE：直接用ü
         */
        format.setVCharType(HanyuPinyinVCharType.WITH_V);

        char[] hanYuArr = hanzi.trim().toCharArray();
        StringBuilder pinYin = new StringBuilder();

        try {
            for (int i = 0, len = hanYuArr.length; i < len; i++) {
                //匹配是否是汉字
                if (Character.toString(hanYuArr[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    //如果是多音字，返回多个拼音，这里只取第一个
                    String[] pys = PinyinHelper.toHanyuPinyinStringArray(hanYuArr[i], format);
                    pinYin.append(pys[0]).append(" ");
                } else {
                    pinYin.append(hanYuArr[i]).append(" ");
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
            badHanyuPinyinOutputFormatCombination.printStackTrace();
        }
        return deleteWhitespace(pinYin.toString());
    }
}
