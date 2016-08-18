package me.sandlz.rxjavademo.core.db;

import android.content.Context;

import org.xutils.DbManager;
import org.xutils.x;

import java.io.File;

import me.sandlz.rxjavademo.R;
import me.sandlz.rxjavademo.utils.FileUtil;

/**
 * Created by liuzhu on 16/8/12.
 * Description : 数据库操作类
 * Usage : DBHelper.getCacheInstance()...
 */
public class DBHelper {
    private static final String TAG = DBHelper.class.getSimpleName();

    private static DBHelper instance;
    private static DBHelper cacheInstance;

    private static Context appContext;
    private static boolean DebugModel = true;

    private DbManager db;

    private DBHelper() {
    }

    /**
     * 初始化数据库 appContetx
     * @param context
     */
    public  static  void init(Context context){
        appContext=context;
    }

    /**
     * 获取CacheDb的实例
     * @return
     */
    public static DBHelper getCacheInstance() {
        if(appContext==null){
            throw new RuntimeException("请先在程序入口处初始化，调用init(ApplicationContext)");
        }
        if (cacheInstance == null) {

            cacheInstance = new DBHelper();
            checkDBFile(FileUtil.getRootPath() + FileUtil.getResString(R.string.db_path, appContext)+FileUtil.getResString(R.string.cache_db_name, appContext));
            DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
                    .setDbName("rxjava.cache.db")
                    // 不设置dbDir时, 默认存储在app的私有目录.
                    .setDbDir(new File(FileUtil.getRootPath() + FileUtil.getResString(R.string.db_path, appContext)))
                    .setDbVersion(1)
                    .setDbOpenListener(new DbManager.DbOpenListener() {
                        @Override
                        public void onDbOpened(DbManager db) {
                            // 开启WAL, 对写入加速提升巨大
                            db.getDatabase().enableWriteAheadLogging();
                        }
                    })
                    .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                        @Override
                        public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                            // TODO: ...
                            // db.addColumn(...);
                            // db.dropTable(...);
                            // ...
                            // or
                            // db.dropDb();
                        }
                    });
            try {
                cacheInstance.db = x.getDb(daoConfig);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return cacheInstance;
    }

    /**
     * 获取db的实例
     * @return
     */
    public static DBHelper getInstance() {
        if(appContext==null){
            throw new RuntimeException("请先在程序入口处初始化，调用init(ApplicationContext)");
        }
        if (instance == null) {
            instance = new DBHelper();
            checkDBFile(FileUtil.getRootPath() + FileUtil.getResString(R.string.db_path, appContext)+FileUtil.getResString(R.string.db_name, appContext));
            DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
                    .setDbName("rxjava.db")
                    // 不设置dbDir时, 默认存储在app的私有目录.
                    .setDbDir(new File(FileUtil.getRootPath() + FileUtil.getResString(R.string.db_path, appContext)))
                    .setDbVersion(1)
                    .setDbOpenListener(new DbManager.DbOpenListener() {
                        @Override
                        public void onDbOpened(DbManager db) {
                            // 开启WAL, 对写入加速提升巨大
                            db.getDatabase().enableWriteAheadLogging();
                        }
                    })
                    .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                        @Override
                        public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                            // TODO: ...
                            // db.addColumn(...);
                            // db.dropTable(...);
                            // ...
                            // or
                            // db.dropDb();
                        }
                    });
            try {
                instance.db = x.getDb(daoConfig);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return instance;
    }

    public DbManager getDbManager() {
        return db;
    }

    private void setDbManager(DbManager db) {
        this.db = db;
    }

    /**
     * 检查数据库文件,不存在则复制
     */
    private static void checkDBFile(String dbName) {
        if (!FileUtil.isFileExists(dbName)) {
            String outPutPath = FileUtil.getRootPath() + FileUtil.getResString(R.string.db_path, appContext);
            FileUtil.copyAssetsToFile("db", outPutPath, appContext.getResources());
        }
    }
}
