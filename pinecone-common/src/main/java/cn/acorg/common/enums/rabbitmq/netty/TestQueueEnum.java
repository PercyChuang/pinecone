package cn.acorg.common.enums.rabbitmq.netty;

/**
 * Test 队列名称
 * @author ChenXueSong
 * @date 2020-09-28 21:05
 */
public enum TestQueueEnum {

    TEST_1(TestQueueHandler.TEST_1, "测试1 队列"),

    TEST_2(TestQueueHandler.TEST_2, "测试2 队列"),

    TEST_3(TestQueueHandler.TEST_3, "测试3 队列");

    TestQueueEnum(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    private String name;

    private String desc;

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
