package com.mblund.examples.zookeeper.leaderSelector;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.CancelLeadershipException;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.state.ConnectionState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * An example leader selector client.
 */
public class LeaderSelectorClient implements Closeable, LeaderSelectorListener {

    private static final Logger log = LoggerFactory.getLogger(LeaderSelectorExample.class);

    /**
     * Note that we do not interupt the leader when new state is SUSPENDED
     * This is different from {@link LeaderSelectorListenerAdapter}
     *
     * check out
     *  https://github.com/Netflix/curator/wiki/Leader-Election
     *
     * @param client
     * @param newState
     */
    @Override
    public void stateChanged(CuratorFramework client, ConnectionState newState) {
        if (newState == ConnectionState.LOST) {
            log.error("New state is Connection.LOST");
            // If the LOST state is reported, the instance is no longer the leader and its takeLeadership method should exit.
            throw new CancelLeadershipException();
        } else if (newState == ConnectionState.SUSPENDED) {
            log.warn("I'm might not be leader");
            // If the SUSPENDED state is reported, the instance must assume that it might no longer be the leader until
            // it receives a RECONNECTED state.
        } else if (newState == ConnectionState.RECONNECTED) {
            log.info("Reconnected and still leader if I haven't got any LOST connection in between");
        } else {
            log.info("New state: " + newState);
        }
    }

    private final String name;
    private final LeaderSelector leaderSelector;

    public LeaderSelectorClient(CuratorFramework client, String path, String name) {
        this.name = name;

        // create a leader selector using the given path for management
        // all participants in a given leader selection must use the same path
        // LeaderSelectorClient here is also a LeaderSelectorListener but this isn't required
        leaderSelector = new LeaderSelector(client, path, this);

        // for most cases you will want your instance to requeue when it relinquishes leadership
        leaderSelector.autoRequeue();
    }

    public void start() throws IOException {
        // the selection for this instance doesn't start until the leader selector is started
        // leader selection is done in the background so this call to leaderSelector.start() returns immediately
        leaderSelector.start();
    }

    @Override
    public void close() throws IOException {
        leaderSelector.close();
    }

    @Override
    public void takeLeadership(CuratorFramework client) throws Exception {
        // we are now the leader. This method should not return until we want to relinquish leadership
        log.info(name + " is now the leader");

        try {
            while (true) {
                Thread.sleep(TimeUnit.SECONDS.toMillis(5));
                log.debug(name + " still leader. ");
            }
        } catch (InterruptedException e) {
            log.debug(name + " was interrupted by " + e.getMessage());
            Thread.currentThread().interrupt();
        } finally {
            log.warn(name + " leaving leadership");
        }

    }
}