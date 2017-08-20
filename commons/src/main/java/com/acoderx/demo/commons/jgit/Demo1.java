package com.acoderx.demo.commons.jgit;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ListBranchCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;

import java.io.IOException;
import java.util.List;

/**
 * Created by xudi on 2017/8/19.
 * 获取分支信息
 */
public class Demo1 {
    public static void main(String[] args) throws IOException, GitAPIException {
        Repository repository = JgitUtil.openRepository("bogo");
        Git git = new Git(repository);
        /*List<Ref> l = git.branchList().call();
        for (Ref r : l) {
            System.out.println(r.getName());
        }*/
        //只显示远程分支
        List<Ref> la = git.branchList().setListMode(ListBranchCommand.ListMode.REMOTE).call();
        for (Ref r : la) {
            System.out.println(r.getName());
        }
    }
}
