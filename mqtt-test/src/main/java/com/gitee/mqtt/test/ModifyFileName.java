package com.gitee.mqtt.test;

import java.io.File;
import java.io.IOException;

/**
 * 修改文件名
 * 感谢 @☜☞E牛仔 提供本文件
 *
 * @Auther: Code
 * @Date: 2018/9/9 18:02
 * @Description: 批量重命名文件
 */
public class ModifyFileName {
    static String OLD_PROJECT_NAME = "lamp";// 要被替换的字符串
    static String NEW_PROJECT_NAME = "act";// 新字符串,如果是去掉前缀后缀就留空，否则写上需要替换的字符串

    //这个路径不能含有zuihou字符!!!!!!! 先将 zuihou-commons-plus 修改为tangyh-commons-plus
    //这个路径不能含有zuihou字符!!!!!!! 先将 zuihou-commons-plus 修改为tangyh-commons-plus
    //这个路径不能含有zuihou字符!!!!!!! 先将 zuihou-commons-plus 修改为tangyh-commons-plus
    static String utilDir = "E:\\code\\iot\\act-boot-plus";// 文件所在路径，所有文件的根目录，记得修改为你电脑上的文件所在路径
    static String bootDir = "E:\\code\\iot\\act-util-plus";
    static String web = "E:\\code\\iot\\act-web";


    public static void main(String[] args) throws IOException {



        // 递归遍历此路径下所有文件夹
        recursiveTraversalFolder(web);
//        recursiveTraversalFolder(bootDir);
        OLD_PROJECT_NAME="tangyh";
//        recursiveTraversalFolder(utilDir);
//        recursiveTraversalFolder(bootDir);
        recursiveTraversalFolder(web);
    }

    /**
     * 递归遍历文件夹获取文件
     */
    public static void recursiveTraversalFolder(String path) {
        File folder = new File(path);
        if (folder.exists()) {
            File[] fileArr = folder.listFiles();
            if (fileArr == null || fileArr.length == 0) {
                System.out.println("文件夹是空的!");
                return;
            } else {
                File newDir = null;// 文件所在文件夹路径+新文件名
                String newName = "";// 新文件名
                String fileName = null;// 旧文件名
                File parentPath = new File("");// 文件所在父级路径
                for (File file : fileArr) {
                    if (file.isDirectory()) {// 是文件夹，继续递归，如果需要重命名文件夹，这里可以做处理
                        fileName = file.getName();
                        if (fileName.contains(OLD_PROJECT_NAME)) {// 文件夹名包含需要被替换的字符串
                            newName = file.getAbsolutePath().replaceAll(OLD_PROJECT_NAME, NEW_PROJECT_NAME);// 新名字
                            File newFile = new File(newName);
                            file.renameTo(newFile);// 重命名

                            System.out.println("文件夹修改前:" + file.getAbsolutePath() + "，修改后" + newName);
                            recursiveTraversalFolder(newFile.getAbsolutePath());
                        }
                        recursiveTraversalFolder(file.getAbsolutePath());
                    } else {// 是文件，判断是否需要重命名
                        fileName = file.getName();
                        parentPath = file.getParentFile();
                        if (fileName.contains(OLD_PROJECT_NAME)) {// 文件名包含需要被替换的字符串
                            newName = fileName.replaceAll(OLD_PROJECT_NAME, NEW_PROJECT_NAME);// 新名字
                            newDir = new File(parentPath + "/" + newName);// 文件所在文件夹路径+新文件名
                            file.renameTo(newDir);// 重命名
                            System.out.println("文件修改前：" + fileName + "修改后：" + newDir);
                        }
                    }
                }
            }
        } else {
            System.out.println("目录不存在=" + path);
        }
    }
}
