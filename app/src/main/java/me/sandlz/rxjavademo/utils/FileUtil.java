package me.sandlz.rxjavademo.utils;

import android.content.Context;
import android.content.res.Resources;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by liuzhu on 16/8/12.
 * Description : 文件工具类
 * Usage :
 */
public class FileUtil {

    /**
     * 获取SDCARD 根目录
     * @return
     */
    public static String getRootPath() {
        return Environment.getExternalStorageDirectory() + "/";
    }

    public static void initFileDir(String path) {
        File file = new File(path);
        if (!isFileExists(path)) {
            file.mkdir();
        }
    }

    /**
     * 文件是否存在
     * @param path
     * @return
     */
    public static boolean isFileExists(String path) {
        if (null != path) {
            File file = new File(path);
            if (file.exists()) {
                return true;
            }else {
                return false;
            }
        }
        return false;
    }

    /**
     * 拷贝Assets文件至SD卡
     */
    public static void copyAssetsToFile(String assetDirectory, String destination, Resources res) {
        if(res != null) {
            String[] files;
            try {
                files = res.getAssets().list(assetDirectory);
            } catch (IOException var12) {
                return;
            }

            File mWorkingPath = new File(destination);
            if(!mWorkingPath.exists() && !mWorkingPath.mkdirs()) {
                ;
            }

            for(int i = 0; i < files.length; ++i) {
                try {
                    String e = files[i];
                    if(!e.contains(".")) {
                        if(0 == assetDirectory.length()) {
                            copyAssetsToFile(e, destination + e + "/", res);
                        } else {
                            copyAssetsToFile(assetDirectory + "/" + e, destination + e + "/", res);
                        }
                    } else {
                        File outFile = new File(mWorkingPath, e);
                        if(outFile.exists()) {
                            outFile.delete();
                        }

                        InputStream in = null;
                        if(0 != assetDirectory.length()) {
                            in = res.getAssets().open(assetDirectory + "/" + e);
                        } else {
                            in = res.getAssets().open(e);
                        }

                        FileOutputStream out = new FileOutputStream(outFile);
                        byte[] buf = new byte[1024];

                        int len;
                        while((len = in.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        }

                        in.close();
                        out.close();
                    }
                } catch (FileNotFoundException var13) {
                    var13.printStackTrace();
                } catch (IOException var14) {
                    var14.printStackTrace();
                }
            }

        }
    }

    /**
     * 依据字符串资源ID获取资源字符串
     *
     * @param StrId
     * @param pContext
     * @return
     */
    public static String getResString(int StrId, Context pContext) {
        String pStr = "";
        try {
            pStr = pContext.getResources().getString(StrId);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return pStr;
    }




    /**
     * 在指定的位置创建指定的文件
     *
     * @param filePath 完整的文件路径
     * @param mkdir    是否创建相关的文件夹
     * @throws Exception
     */
    public static void mkFile(String filePath, boolean mkdir) throws Exception {
        File file = new File(filePath);
        file.getParentFile().mkdirs();
        file.createNewFile();
        file = null;
    }

    /**
     * 在指定的位置创建文件夹
     *
     * @param dirPath 文件夹路径
     * @return 若创建成功，则返回True；反之，则返回False
     */
    public static boolean mkDir(String dirPath) {
        return new File(dirPath).mkdirs();
    }

    /**
     * 删除指定的文件
     *
     * @param filePath 文件路径
     * @return 若删除成功，则返回True；反之，则返回False
     */
    public static boolean delFile(String filePath) {
        return new File(filePath).delete();
    }

    /**
     * 删除指定的文件夹
     *
     * @param dirPath 文件夹路径
     * @param delFile 文件夹中是否包含文件
     * @return 若删除成功，则返回True；反之，则返回False
     */
    public static boolean delDir(String dirPath, boolean delFile) {
        if (delFile) {
            File file = new File(dirPath);
            if (file.isFile()) {
                return file.delete();
            } else if (file.isDirectory()) {
                if (file.listFiles().length == 0) {
                    return file.delete();
                } else {
                    int zfiles = file.listFiles().length;
                    File[] delfile = file.listFiles();
                    for (int i = 0; i < zfiles; i++) {
                        if (delfile[i].isDirectory()) {
                            delDir(delfile[i].getAbsolutePath(), true);
                        }
                        delfile[i].delete();
                    }
                    return file.delete();
                }
            } else {
                return false;
            }
        } else {
            return new File(dirPath).delete();
        }
    }

    /**
     * 复制文件/文件夹 若要进行文件夹复制，请勿将目标文件夹置于源文件夹中
     *
     * @param source   源文件（夹）
     * @param target   目标文件（夹）
     * @param isFolder 若进行文件夹复制，则为True；反之为False
     * @throws Exception
     */
    public static void copy(String source, String target, boolean isFolder)
            throws Exception {
        if (isFolder) {
            (new File(target)).mkdirs();
            File a = new File(source);
            String[] file = a.list();
            File temp = null;
            for (int i = 0; i < file.length; i++) {
                if (source.endsWith(File.separator)) {
                    temp = new File(source + file[i]);
                } else {
                    temp = new File(source + File.separator + file[i]);
                }
                if (temp.isFile()) {
                    FileInputStream input = new FileInputStream(temp);
                    FileOutputStream output = new FileOutputStream(target + "/" + (temp.getName()).toString());
                    byte[] b = new byte[1024];
                    int len;
                    while ((len = input.read(b)) != -1) {
                        output.write(b, 0, len);
                    }
                    output.flush();
                    output.close();
                    input.close();
                }
                if (temp.isDirectory()) {
                    copy(source + "/" + file[i], target + "/" + file[i], true);
                }
            }
        } else {
            int byteread = 0;
            File oldfile = new File(source);
            if (oldfile.exists()) {
                InputStream inStream = new FileInputStream(source);
                File file = new File(target);
                file.getParentFile().mkdirs();
                file.createNewFile();
                FileOutputStream fs = new FileOutputStream(file);
                byte[] buffer = new byte[1024];
                while ((byteread = inStream.read(buffer)) != -1) {
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
                fs.close();
            }
        }
    }

    /**
     * 移动指定的文件（夹）到目标文件（夹）
     *
     * @param source   源文件（夹）
     * @param target   目标文件（夹）
     * @param isFolder 若为文件夹，则为True；反之为False
     * @return
     * @throws Exception
     */
    public static boolean move(String source, String target, boolean isFolder)
            throws Exception {
        copy(source, target, isFolder);
        if (isFolder) {
            return delDir(source, true);
        } else {
            return delFile(source);
        }
    }

    /**
     * 获取缓存文件夹
     *
     * @param context
     * @return
     */
    public static String getDiskCacheDir(Context context) {
        String cachePath;
        //isExternalStorageEmulated()设备的外存是否是用内存模拟的，是则返回true
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageEmulated()) {
            cachePath = context.getExternalCacheDir().getAbsolutePath();
        } else {
            cachePath = context.getCacheDir().getAbsolutePath();
        }
        return cachePath;
    }
}
