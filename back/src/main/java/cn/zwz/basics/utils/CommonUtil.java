package cn.zwz.basics.utils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;

import java.security.SecureRandom;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;


//@Operation(description = "公共工具类")
public class CommonUtil {

    private static SecureRandom random = new SecureRandom();

    @Operation(description = "生成随机文件名称")
    public static String renamePic(String fileName) {
        return UUID.randomUUID().toString().replace("-", "") + fileName.substring(fileName.lastIndexOf("."));
    }

    @Operation(description = "生成随机企微验证码")
    public static String getRandomTwoNum() {
        int num = random.nextInt(99);
        // 不足六位前面补0
        String str = String.format("%02d", num);
        return str;
    }

    @Operation(description = "避免删除 DFS 死循环")
    public static Boolean judgeIds(String tempString, String[] list){
        boolean flag = true;
        for(String id : list){
            if(Objects.equals(tempString,id)){
                flag = false;
                break;
            }
        }
        return !flag;
    }

    @Operation(description = "生成随机6位验证码")
    public static String getRandomNum() {
        Random random = new Random();
        int num = random.nextInt(999999);
        return String.format("%06d", num);
    }
}
