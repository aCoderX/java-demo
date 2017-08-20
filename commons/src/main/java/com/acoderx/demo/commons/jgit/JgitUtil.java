package com.acoderx.demo.commons.jgit;

import com.acoderx.demo.commons.util.StringUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

import java.io.File;
import java.io.IOException;

/**
 * Created by xudi on 2017/8/19.
 */
public class JgitUtil {
    //默认workspace，所有git项目所在父目录
    public static final String LOCALPATH = "/Users/xudi/tmp/";
    //git远程地址
    public static final String GITREMOTE = "https://github.com/MacheteTeam/bogo.git";
    public static void main(String[] args) throws GitAPIException {
        Repository repository = cloneRepository(GITREMOTE, LOCALPATH);
        System.out.println(repository.getDirectory().getAbsolutePath());
    }

    public static Repository cloneRepository(String remotePath,String localPath) throws GitAPIException {
        String repoName = getRepoNameByUrl(remotePath);
        if(!localPath.endsWith("/")){
            localPath = localPath + "/";
        }
        localPath = localPath + repoName;
        File file = new File(localPath);
        Git result = Git.cloneRepository().setURI(remotePath).setDirectory(file).setNoCheckout(true).call();
        return result.getRepository();
    }
    /**
     *
     * @param repoName 项目名
     * @return
     * @throws IOException
     */
    public static Repository openRepository(String repoName) throws IOException {
        FileRepositoryBuilder builder = new FileRepositoryBuilder();
        return builder.setGitDir(new File(LOCALPATH + repoName+"/.git")).build();
    }


    private static boolean validateGitUrl(String gitUrl){
        if (StringUtils.isNotEmpty(gitUrl)) {
            return gitUrl.endsWith(".git");
        }
        return false;
    }

    private static String getRepoNameByUrl(String gitUrl){
        if(validateGitUrl(gitUrl)){
            String gitStr = gitUrl.substring(gitUrl.lastIndexOf("/")+1);
            String[] subs = gitStr.split("\\.");
            if(subs.length>1){
                return subs[0];
            }
            return "";
        }
        return "";
    }
}
