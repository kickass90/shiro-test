package com.jet.common.test;

import java.io.File;

import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.util.EnvUtil;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.repository.RepositoryDirectoryInterface;
import org.pentaho.di.repository.kdr.KettleDatabaseRepository;
import org.pentaho.di.repository.kdr.KettleDatabaseRepositoryMeta;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;


/**
 * @author:
 * @TODO:Jave调用Kettle案例
 */
public class KettleUtil {

    private KettleUtil() {
    };

    /**
     * @author:
     * @TODO:运行本地ktr文件
     */
    @SuppressWarnings("all")
    public static boolean runTransformation(String filename) {
        boolean flag = false;
        try {
            // 初始化任务
            KettleEnvironment.init();
            EnvUtil.environmentInit();
            TransMeta transMeta = new TransMeta(filename);
            transMeta.setCapturingStepPerformanceSnapShots(true);
            Trans trans = new Trans(transMeta);
            trans.setMonitored(true);
            trans.setInitializing(true);
            trans.setPreparing(true);
            trans.setRunning(true);
            trans.setSafeModeEnabled(true);
            // 设置参数 在kettle文件中sql语句的设置 SELECT * FROM xx where xx=${stnlevel}
            // sql参数必须一一对应
            trans.setVariable("stnlevel", "2");
            trans.execute(null);
            // 等待转换执行结束
            trans.waitUntilFinished();
            flag = true;
        } catch (KettleException e) {
            System.out.println(e);
        }
        return flag;
    }

    /**
     * @author:
     * @TODO:运行本地kjb文件
     */
    @SuppressWarnings("all")
    public static boolean runJob(String filename) {
        boolean flag = false;
        try {
            KettleEnvironment.init();
            // jobname 是Job脚本的路径及名称
            JobMeta jobMeta = new JobMeta(filename, null);
            Job job = new Job(null, jobMeta);
            // job.setVariable("id", params[0]);
            // job.setVariable("dt", params[1]);
            job.start();
            job.waitUntilFinished();
            flag = true;
        } catch (KettleException e) {
            System.out.println(e);
        }
        return flag;
    }

    /**
     * @author:
     * @TODO:初始化运行环境
     */
    private static void initKettleEnvironment(boolean isWebProgram)
            throws KettleException {
        String user_dir = "";
        String kettleHome = "";
        if (isWebProgram) {
            user_dir = System.getProperty("user.dir");
            kettleHome = "mms" + File.separator + "WEB-INF";
            // Kettle初始化需要修改相应的配置路径
            System.setProperty("user.dir", kettleHome);
            System.setProperty("KETTLE_HOME", kettleHome);
        }
        // 初始化环境
        // 运行环境初始化（设置主目录、注册必须的插件等）
        KettleEnvironment.init();
        if (isWebProgram) {
            System.setProperty("user.dir", user_dir);
        }
    }

    /**
     * @author:
     * @TODO:运行资源库ktr文件
     */
    @SuppressWarnings("all")
    public static boolean runRepoTransformation(String filename,
                                                boolean isWebProgram) {
        boolean flag = false;
        try {
            initKettleEnvironment(isWebProgram);
            // 创建资源库 对象
            KettleDatabaseRepository repository = new KettleDatabaseRepository();
            // 创建资源库所在的数据库
            DatabaseMeta databaseMeta = new DatabaseMeta("", "oracle", "jdbc",
                    "192.168.1.254", "ORCL", "1521", "cmbexam", "cmbexam");
            // 选择资源库 找到对应的资源库名称
            KettleDatabaseRepositoryMeta kettleDatabaseRepositoryMeta = new KettleDatabaseRepositoryMeta(
                    "xx", "xx", "Transformation description", databaseMeta);
            repository.init(kettleDatabaseRepositoryMeta);
            // 连接资源库 资源库的用户名和密码
            repository.connect("admin", "1");
            RepositoryDirectoryInterface directoryInterface = repository
                    .loadRepositoryDirectoryTree();
            // 选择转换
            TransMeta transMeta = repository.loadTransformation(filename,
                    directoryInterface, null, true, null);
            Trans trans = new Trans(transMeta);
            trans.setVariable("stnlevel", "2");
            trans.execute(null);
            // 等待直到数据结束
            trans.waitUntilFinished();
            if (trans.getErrors() > 0) {
                System.out.println("Transformation Error");
            } else {
                System.out.println("Transformation Successful");
            }
            flag = true;
        } catch (KettleException e) {
            System.out.println(e);
        }
        return flag;
    }

    public static void main(String[] args) throws KettleException {
//         runTransformation("E:\\g.ktr");
         runJob("E:\\hh.kjb");
//        runRepoTransformation("XXXX", false);
    }
}
