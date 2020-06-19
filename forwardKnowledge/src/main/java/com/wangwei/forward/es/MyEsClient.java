package com.wangwei.forward.es;

import com.wangwei.forward.es.constant.MyIndex;
import com.wangwei.forward.es.constant.MyPipeline;
import org.apache.http.HttpHost;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.ActiveShardCount;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.TermVectorsRequest;
import org.elasticsearch.client.core.TermVectorsResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.VersionType;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;

import java.io.IOException;
import java.util.Date;

public class MyEsClient {


    public static RestHighLevelClient client = InnerClient.Instance.getClient();

    public static final String[] FIELDS = {"user", "age", "hobbit", "gmtModify"};



    public static IndexResponse createIndex(String id) throws IOException {
        // 组建内容
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.field("user", "wang");
            builder.field("age", "23");
            builder.field("hobbit", "football");
            builder.field("gmtModify", new Date());
        }
        builder.endObject();

        IndexRequest indexRequest = new IndexRequest(MyIndex.TEST_INDEX)
                // id值
                .id(id)
                // 操作类型
                .opType(DocWriteRequest.OpType.CREATE)
                // 管道设置
                .setPipeline(MyPipeline.SET_PIPELINE)
                // 超时时间
                .timeout(TimeValue.timeValueSeconds(5))
                // Hash路由到指定分片
                .routing("routing")
                // 设置刷新策略
                .setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE)
                // 版本控制
                .versionType(VersionType.INTERNAL)
                // 设置内容
                .source(builder);

        return client.index(indexRequest, RequestOptions.DEFAULT);
    }

    public static GetResponse getInfo(String id) throws IOException {
        String[] includes = {"user", "age"};
        String[] excludes = {"hobbit"};

        GetRequest getRequest = new GetRequest(MyIndex.TEST_INDEX, id)
                // 版本
                .versionType(VersionType.EXTERNAL)
                // 查询资源信息
                .fetchSourceContext(new FetchSourceContext(true, includes, excludes))
                // 搜索执行
                .preference("preference")
                // 不清楚 todo
                .realtime(true)
                // 获取前线刷新资源 大流量不要设置true 默认false
                .refresh(true)
                // 路由规则
                .routing("routing")
                // 指定能返回的字段
                .storedFields("user");

        return client.get(getRequest, RequestOptions.DEFAULT);
    }

    /**
     * 值是否存在
     *
     * @return {@link Boolean}
     * @throws IOException
     */
    public static Boolean exists(String id) throws IOException {

        GetRequest getRequest = new GetRequest(MyIndex.TEST_INDEX, id)
                .fetchSourceContext(FetchSourceContext.DO_NOT_FETCH_SOURCE)
                .storedFields("_none_");

        return client.exists(getRequest, RequestOptions.DEFAULT);
    }

    /**
     * 删除指定值
     *
     * @param id id
     * @return
     * @throws IOException
     */
    public static DeleteResponse delete(String id) throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest(MyIndex.TEST_INDEX, id)
                // 路由
                .routing("routing")
                // 超时
                .timeout(TimeValue.timeValueSeconds(2))
                // 刷新策略
                .setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE)
                .versionType(VersionType.EXTERNAL);

        return client.delete(deleteRequest, RequestOptions.DEFAULT);
    }

    /**
     * 更新指定值
     *
     * @param id id
     * @return
     * @throws IOException
     */
    public static UpdateResponse update(String id) throws IOException {
        String[] includes = {"user", "age"};
        String[] excludes = {"hobbit"};

        XContentBuilder xContentBuilder = XContentFactory.jsonBuilder();
        xContentBuilder.startObject();
        {
            xContentBuilder.field("gmtModify", new Date());
            xContentBuilder.field("age", "25");
        }
        xContentBuilder.endObject();

        UpdateRequest updateRequest = new UpdateRequest(MyIndex.TEST_INDEX, id)
                // 更新后拉取资源
                .fetchSource(new FetchSourceContext(true, includes, excludes))
                // 更新文档
                .doc(xContentBuilder)
                // 检测是不是Noop 默认是true
                .detectNoop(true)
                // 默认为false，更新请求检查发现文档不存在时(对应的ID文档)，会抛出ElasticsearchException
                // true: 更新请求检查发现文档不存在时(对应的ID文档),会新增 request.doc 中设置的文档;
                .docAsUpsert(false)
                // 解析 太难了
                //.fromXContent()
                // 冲突时候更新失败 重试次数 默认为0
                .retryOnConflict(0)
                // 分片的路由策略
                .routing("routing")
                // 更新脚本
                .script(Script.parse(Settings.builder()
                        .put("job", "coder")
                        .build()))
                // 不存在 就插入 我猜的
                .scriptedUpsert(true)
                // 文档所在位置（相当于班级） 与下方一起使用
                .setIfPrimaryTerm(1)
                // 文档版本号，作用同_version 与上方一起使用
                .setIfSeqNo(1)
                // 刷新机制
                .setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE)
                // 版本号
                .versionType(VersionType.EXTERNAL)
                // 超时时间
                .timeout(TimeValue.timeValueSeconds(3))
                // 必须全部分片数量处于激活当中才可以写入
                .waitForActiveShards(ActiveShardCount.ALL);

        return client.update(updateRequest, RequestOptions.DEFAULT);
    }

    public static TermVectorsResponse queryByTerms() throws IOException {
        TermVectorsRequest termVectorsRequest = new TermVectorsRequest(MyIndex.TEST_INDEX, "1");
        termVectorsRequest.setFields(FIELDS);
        termVectorsRequest.setFieldStatistics(true);
        // todo do not konw
        //termVectorsRequest.setFilterSettings();
        termVectorsRequest.setOffsets(true);
        termVectorsRequest.setPayloads(true);
        // todo do not know
        //termVectorsRequest.setPerFieldAnalyzer();
        termVectorsRequest.setPositions(true);
        // todo do not know
        //termVectorsRequest.setPreference();
        // 感觉想实时
        termVectorsRequest.setRealtime(true);
        // 分片路由策略
        termVectorsRequest.setRouting("routing");
        termVectorsRequest.setTermStatistics(true);

        return client.termvectors(termVectorsRequest, RequestOptions.DEFAULT);
    }

    private enum InnerClient {
        /**
         * 初始对象
         */
        Instance()
        ;

        InnerClient() {
        }

        private RestHighLevelClient getClient() {
            return new RestHighLevelClient(
                    RestClient.builder(new HttpHost("localhost", 9200))
            );
        }
    }

}
