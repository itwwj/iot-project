package com.gitee.mqtt.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.channels.FileChannel;

/**
 * 修改文件中包含 oldStr 的内容，改成newStr
 *
 * 感谢 @☜☞E牛仔 提供本文件
 */
public class ModifyContent {
    //这个路径不能含有zuihou字符!!!!!!! 先将 zuihou-commons-plus 修改为tangyh-commons-plus
    //这个路径不能含有zuihou字符!!!!!!! 先将 zuihou-commons-plus 修改为tangyh-commons-plus
    //这个路径不能含有zuihou字符!!!!!!! 先将 zuihou-commons-plus 修改为tangyh-commons-plus
//    static String dir = "/Users/tangyh/gitlab/test/tangyh-commons-plus";// 文件所在路径，所有文件的根目录，记得修改为你电脑上的文件所在路径
    static String dir = "E:\\code\\iot\\act-web";// 文件所在路径，所有文件的根目录，记得修改为你电脑上的文件所在路径
    static String newStr = "act";
    static String oldStr = "lamp";

    /**
     * @author itmyhome
     */
    public static void main(String[] args) {
        //这个路径不能含有zuihou字符
        File f = new File(dir);
        print(f, 0, "E:\\code\\iot", "E:\\code\\iot");
      //  dir="E:\\code\\iot\\new\\boot\\act-boot-plus";
       // f = new File(dir);
      //  print(f, 0, "E:\\code\\iot\\new\\boot", "E:\\code\\iot");
    }

    /**
     * 遍历目录
     *
     * @param f
     * @param len
     */
    public static void print(File f, int len, String sourceDisk, String targetDisk) {
        File[] file = f.listFiles();

        for (int i = 0; i < file.length; i++) {
            if (file[i].isDirectory()) { //推断是否目录
                print(file[i], len + 1, sourceDisk, targetDisk);
            }

            // 为防止输出文件覆盖源文件，所以更改输出盘路径 也可自行设置其它路径
            File outPath = new File(file[i].getParent().replace(sourceDisk, targetDisk));
            File readfile = new File(file[i].getAbsolutePath());

            if (!readfile.isDirectory()) {
                String filename = readfile.getName(); // 读到的文件名称
                String absolutepath = readfile.getAbsolutePath(); // 文件的绝对路径
                if (absolutepath.endsWith(".png") ||
                        absolutepath.endsWith(".ico") ||
                        absolutepath.endsWith(".jpg") ||
                        absolutepath.endsWith(".dll") ||
                        absolutepath.endsWith(".exe") ||
                        absolutepath.endsWith(".jar") ||
                        absolutepath.endsWith(".zip") ||
                        absolutepath.endsWith(".rdb") ||
                        absolutepath.endsWith(".docx") ||
                        absolutepath.endsWith(".pdb")
                ) {
                    //.png  .ico .jpg .dll .exe .jar .zip  .rdb .docx .pdb
                    try {
                        copyFileUsingFileChannels(absolutepath, filename, i, outPath);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    readFile(absolutepath, filename, i, outPath); // 调用 readFile

                }

            }
        }
    }

    /**
     * 读取目录下的文件
     *
     * @return
     */
    public static void readFile(String absolutepath, String filename,
                                int index, File outPath) {
        try {
            BufferedReader bufReader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(absolutepath), "utf8")); // 数据流读取文件

            StringBuffer strBuffer = new StringBuffer();


            int line = 0;
            for (String temp = null; (temp = bufReader.readLine()) != null;
                 temp = null) {
                line++;
                if ((temp.indexOf(oldStr) != -1) &&
                        (temp.indexOf(oldStr) != -1)) { // 推断当前行是否存在想要替换掉的字符
                    temp = temp.replace(oldStr, newStr); // 此处进行替换

                    System.out.println(absolutepath + "\t\t\t\t\t\t\t\t\t\t\t" + "第" + line + "行" + "替换" + oldStr + "为" + newStr);
                }

                strBuffer.append(temp);
                strBuffer.append(System.getProperty("line.separator")); // 换行符
            }

            bufReader.close();

            if (outPath.exists() == false) { // 检查输出目录是否存在，若不存在先创建
                outPath.mkdirs();
                //System.out.println("已成功创建输出目录：" + outPath);
            }

            PrintWriter printWriter = new PrintWriter(outPath + File.separator + filename, "utf8"); // 替换后输出文件路径
            printWriter.write(strBuffer.toString().toCharArray()); //又一次写入
            printWriter.flush();
            printWriter.close();
            //System.out.println("第 " + (index + 1) + " 个文件   " + absolutepath +"  已成功输出到    " + outPath + "\\" + filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void copyFileUsingFileChannels(String absolutepath, String filename,
                                                 int index, File outPath) throws IOException {

        if (outPath.exists() == false) { // 检查输出目录是否存在，若不存在先创建
            outPath.mkdirs();
            //System.out.println("已成功创建输出目录：" + outPath);
        }

        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            inputChannel = new FileInputStream(absolutepath).getChannel();
            outputChannel = new FileOutputStream(outPath.getAbsolutePath() + File.separator + filename).getChannel();
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        } finally {
            inputChannel.close();
            outputChannel.close();
        }
    }

}
