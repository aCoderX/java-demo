package com.acoderx.demo.commons.jgit;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.transport.FetchResult;

import java.io.IOException;

/**
 * Created by xudi on 2017/8/20.
 * fetch
 */
public class Demo2 {
    public static void main(String[] args) throws IOException, GitAPIException {
        try (Repository repository = JgitUtil.openRepository("bogo")) {
            try (Git git = new Git(repository)) {
                FetchResult result = git.fetch().setCheckFetchedObjects(true).call();
                System.out.println(result.getMessages());
            }
        }
    }
}
