package com.mblund.examples.zookeeper.leaderSelector;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryUntilElapsed;
import org.apache.curator.utils.CloseableUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class LeaderSelectorExample {


    public static void main(String[] args) throws IOException, InterruptedException {
        LeaderSelectorExample example = new LeaderSelectorExample();
        example.connect("localhost:2181,localhost:2182,localhost:2183");
    }

    public void connect(String hosts) throws IOException, InterruptedException {
        RetryPolicy retryPolicy = new RetryUntilElapsed(10000, 1000);
        //RetryPolicy retryPolicy = new ExponentialBackoffRetry(5000, 10);

        CuratorFramework client = CuratorFrameworkFactory.newClient(
                hosts,
                10000,
                10000,
                retryPolicy);
        //SessionTimeoutMs above must be larger that gc pauces, simulate with debugger an pause the process

        LeaderSelectorClient leaderSelectorClient = new LeaderSelectorClient(
                client,
                "/leaderElection",
                "Client #" + new Random().nextInt(100));
        try {
            client.start();
            leaderSelectorClient.start();
            System.out.println("Press enter/return to quit\n");
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        } finally {
            CloseableUtils.closeQuietly(leaderSelectorClient);
            CloseableUtils.closeQuietly(client);
        }
    }

}
