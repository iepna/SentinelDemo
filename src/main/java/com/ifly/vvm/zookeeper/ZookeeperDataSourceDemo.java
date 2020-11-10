package com.ifly.vvm.zookeeper;

import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.zookeeper.ZookeeperDataSource;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.ifly.vvm.nacos.FlowQpsRunner;

import java.util.List;

public class ZookeeperDataSourceDemo {
    private static final String KEY = "TestResource";

    public static void main(String[] args) {
        // 使用zookeeper的场景
        //loadRules();

        // 方便扩展的场景
        //loadRules2();

        FlowQpsRunner runner = new FlowQpsRunner(KEY, 1, 100);
        runner.simulateTraffic();
        runner.tick();
    }

    private static void loadRules() {

        final String remoteAddress = "10.2.38.4:2181";
        final String path = "/Sentinel-Demo/SYSTEM-CODE-DEMO-FLOW";

        ReadableDataSource<String, List<FlowRule>> flowRuleDataSource = new ZookeeperDataSource<>(remoteAddress, path,
                source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {}));
        FlowRuleManager.register2Property(flowRuleDataSource.getProperty());


    }

    private static void loadRules2() {

        final String remoteAddress = "10.2.38.4:2181";
        // 引入groupId和dataId的概念，是为了方便和Nacos进行切换
        final String groupId = "Sentinel-Demo";
        final String flowDataId = "SYSTEM-CODE-DEMO-FLOW";
        // final String degradeDataId = "SYSTEM-CODE-DEMO-DEGRADE";
        // final String systemDataId = "SYSTEM-CODE-DEMO-SYSTEM";


        // 规则会持久化到zk的/groupId/flowDataId节点
        // groupId和和flowDataId可以用/开头也可以不用
        // 建议不用以/开头，目的是为了如果从Zookeeper切换到Nacos的话，只需要改数据源类名就可以
        ReadableDataSource<String, List<FlowRule>> flowRuleDataSource = new ZookeeperDataSource<>(remoteAddress, groupId, flowDataId,
                source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {}));
        FlowRuleManager.register2Property(flowRuleDataSource.getProperty());

        // ReadableDataSource<String, List<DegradeRule>> degradeRuleDataSource = new ZookeeperDataSource<>(remoteAddress, groupId, degradeDataId,
        //         source -> JSON.parseObject(source, new TypeReference<List<DegradeRule>>() {}));
        // DegradeRuleManager.register2Property(degradeRuleDataSource.getProperty());
        //
        // ReadableDataSource<String, List<SystemRule>> systemRuleDataSource = new ZookeeperDataSource<>(remoteAddress, groupId, systemDataId,
        //         source -> JSON.parseObject(source, new TypeReference<List<SystemRule>>() {}));
        // SystemRuleManager.register2Property(systemRuleDataSource.getProperty());

    }
}
