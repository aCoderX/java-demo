package com.acoderx.demo.commons.jgit;

import org.eclipse.jgit.api.CreateBranchCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;

import java.io.IOException;

/**
 * Created by xudi on 2017/8/20.
 * 分支操作
 */
public class Demo3 {
    public static void main(String[] args) throws IOException, GitAPIException {
        checkoutRemoteBranch("lenny-dev");
    }

    /**
     * 切换到远程分支，相当于git checkout origin/branchName
     * @param branchName 远程分支名  如：master
     * @throws IOException
     * @throws GitAPIException
     */
    public static void checkoutRemoteBranch(String branchName) throws IOException, GitAPIException {
        Repository repository = JgitUtil.openRepository("bogo");
        Git git = new Git(repository);
        git.checkout()
                .setName("origin/"+branchName)
                .setUpstreamMode(CreateBranchCommand.SetupUpstreamMode.TRACK)
                .setStartPoint("origin/" + branchName ).call();
    }
}
