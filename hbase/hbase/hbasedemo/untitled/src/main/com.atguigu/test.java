public static Configuration conf;
    static{
        //使用 HBaseConfiguration 的单例方法实例化
        conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "node21,node22,node23");
        conf.set("hbase.zookeeper.property.clientPort", "2181");
    }