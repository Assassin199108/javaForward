package com.wangwei.forward.es;

/**
 * 我的es管道模拟测试
 *
 * @author wangwei
 */
public class MyEsPipelineSimulate {

    public static final String _simulate = "_simulate";

    /**
     * 请求头带pipeline的模拟测试
     *
     * @param pipelineName
     * @param doc
     * @return
     */
    public static String simulateThePipeline(String pipelineName, Doc doc) {
        String header = EsRequest.RequestHeader.buildHeader(
                EnumRestApi.POST.getType(),
                "_ingest",
                "pipeline",
                pipelineName,
                _simulate);

        String body = EsRequest.RequestBody.buildBody(doc);

        return header + "\n" + body;
    }

}
