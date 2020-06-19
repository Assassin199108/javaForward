package com.wangwei.forward.es;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.elasticsearch.ingest.AbstractProcessor;
import org.elasticsearch.ingest.ConfigurationUtils;
import org.elasticsearch.ingest.IngestDocument;
import org.elasticsearch.plugins.IngestPlugin;
import org.elasticsearch.plugins.Plugin;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 我的es管道处理器技术
 *
 * @author wangwei
 */
public class MyEsPipelineProcessor {

    /**
     * 添加或更新pipeline
     */
    public static String putPipeline(String pipelineName, Pipeline pipeline) {

        return "PUT" + " " + "pipeline" + " " + pipelineName + JSON.toJSONString(pipeline);
    }

    /**
     * pipeline是一系列processors的定义，这些processors将按照声明的顺序执行。
     * pipeline包含两个主要字段：description 和processors列表
     */
    @Data
    public static class Pipeline {

        /**
         * 管道名称
         */
        private String pipelineName;

        /**
         * 管道描述
         */
        private String description;

        /**
         * 管道内预处理器技术
         */
        private List<Processor> processors;
    }

    /**
     * 要按顺序执行的预处理器列表
     * <p>
     * Processors类型详解
     * Append Processor 追加处理器
     * Convert Processor 转换处理器
     * Date Processor 日期转换器
     * Date Index Name Processor 日期索引名称处理器
     * Fail Processor 失败处理器
     * Foreach Processor 循环处理器
     * Grok Processor Grok 处理器
     * Gsub Processor
     * Join Processor
     * JSON Processor
     * KV Processor
     * Lowercase Processor
     * Remove Processor
     * Rename Processor
     * Script Processor
     * Split Processor
     * Sort Processor
     * Trim Processor
     * Uppercase Processor
     * Dot Expander Processor
     */
    @Data
    public static class Processor {

        private Set set;

        private Append append;

        private Convert convert;

        private Date date;

        private DateIndex date_index_name;

        private Fail fail;

        private Foreach foreach;

        private Grok grok;

        private Gsub gsub;

        private Join join;

        private Json json;

        private Kv kv;

        private Lowercase lowercase;

        private Uppercase uppercase;

        private Remove remove;

        private Rename rename;

        private Script script;

        private Split split;

        private Sort sort;

        private Trim trim;
    }

    /**
     * Set Processor作用于两种不同情况:
     * <p>
     * 指定字段存在时,修改指定字段的值
     * 指定字段不存在时,新增该字段并设置该字段的值
     */
    @Data
    public static class Set {

        /**
         * 字段
         */
        private String field;

        /**
         * 值
         */
        private String value;

    }

    /**
     * 追加
     */
    @Data
    public static class Append {

        /**
         * 字段
         */
        private String field;

        /**
         * 追加的值
         */
        private List<String> value;

    }

    /**
     * 类型转换
     */
    @Data
    public static class Convert {

        /**
         * 字段
         */
        private String field;

        /**
         * 转换的类型
         * "float"
         */
        private String type;

    }

    /**
     * 时间转换
     */
    @Data
    public static class Date {

        /**
         * 字段
         */
        private String field;

        /**
         * 转换类型
         */
        private String formats;

        /**
         * 时间区域
         */
        private String timezone;
    }

    /**
     * Date Index Name Processor算得上是一个比较强大的处理了,
     * 它的目的是让通过该处理器的文档能够分配到符合指定时间格式的索引中,前提是按照官方提供的说明来进行使用:
     * <p>
     * 请求将不会将此 document（文档）放入 myindex 索引，而是放入到 myindex-2016-04-01 索引中.
     *
     * https://www.elastic.co/guide/en/elasticsearch/reference/current/date-index-name-processor.html
     */
    @Data
    public static class DateIndex {

        /**
         * 字段
         */
        private String field;

        /**
         * 索引前缀
         */
        private String index_name_prefix;

        /**
         * 取值类型
         */
        private String date_rounding;
    }

    /**
     * 该处理器比较简单,就是当文档通过该pipeline的时候,一旦出现异常,该pipeline指定的错误信息就会返回给请求者.
     */
    @Data
    public static class Fail {

        private String message;

    }

    /**
     * 一个Foreach Processor是用来处理一些数组字段,数组内的每个元素都会使用到一个相同的处理器
     *
     * https://www.elastic.co/guide/en/elasticsearch/reference/current/foreach-processor.html
     */
    @Data
    public static class Foreach {

        /**
         * 字段
         */
        private String field;

        /**
         * 具体的处理
         */
        private Processor processor;

    }

    /**
     * Grok Processor可以算的上是一个比较实用的处理器了,会经常使用到日志格式切割上
     * 因此该文本格式的日志信息在经过Grok Processor之后,能够解析成一个标准文档的格式,
     * 该文档可以使用Elasticsearch提供的检索和聚合功能充分使用到,而原始的文本格式的日志信息则无法做到这一点
     *
     * https://www.elastic.co/guide/en/elasticsearch/reference/current/grok-processor.html
     */
    @Data
    public static class Grok {

        /**
         * 字段
         */
        private String field;

        /**
         * 正则
         */
        private List<String> patterns;

    }

    /**
     * Gsub Processor能够解决一些字符串中才特有的问题,比如我想把字符串格式的日期格式如
     * "yyyy-MM-dd HH:mm:ss"转换成"yyyy/MM/dd HH:mm:ss"的格式,
     * 我们可以借助于Gsub Processor来解决,而Gsub Processor也正是利用正则来完成这一任务的
     *
     * https://www.elastic.co/guide/en/elasticsearch/reference/current/gsub-processor.html
     */
    @Data
    public static class Gsub {
        /**
         * 字段
         */
        private String field;

        /**
         * 正则
         */
        private String pattern;

        /**
         * 替代品
         */
        private String replacement;
    }

    /**
     * Join Processor能够将原本一个数组类型的字段值,分解成以指定分隔符分割的一个字符串
     *
     * https://www.elastic.co/guide/en/elasticsearch/reference/current/join-processor.html
     */
    @Data
    public static class Join {
        /**
         * 字段
         */
        private String field;

        /**
         * 分隔符
         */
        private String separator;
    }

    /**
     * JSON Processor也是用来处理字符串类型的字段,可以将那些符合JSON格式(或被JSON串化)的文本,
     * 在经过JSON Processor加工之后,解析成对应的JSON格式
     * <p>
     * 原本是字符串格式的字段message,在处理之后变成了一个标准的JSON格式.
     * "message":"{\"foo\": 2000}"
     * <p>
     * "message": {
     * "foo": 2000
     * }
     *
     * https://www.elastic.co/guide/en/elasticsearch/reference/current/json-processor.html
     */
    @Data
    public static class Json {
        /**
         * 字段
         */
        private String field;
    }

    /**
     * KV Processor用来K,V字符串格式的处理器,比如K=V, K:V,K->V等格式的解析.
     * <p>
     * 其中字段message的原始值为""ip:127.0.0.1"",我们想将该格式的内容新增一个独立的字段如ip:127.0.0.1这种格式.
     *
     * https://www.elastic.co/guide/en/elasticsearch/reference/current/kv-processor.html
     */
    @Data
    public static class Kv {
        /**
         * 字段
         */
        private String field;

        /**
         * 字段分隔符
         */
        private String field_split;

        /**
         * 值的分隔符
         */
        private String value_split;
    }

    /**
     * Lowercase Processor也是一个专用于字符串类型的字段处理器,顾名思义,是将字符串都转换成小写格式
     */
    @Data
    public static class Lowercase {
        /**
         * 字段
         */
        private String field;
    }

    /**
     * 该处理器类似于Lowercase Processor,将字符串文本统一转换成大写.
     */
    @Data
    public static class Uppercase {
        /**
         * 字段
         */
        private String field;
    }

    /**
     * Remove Processor是用来处理在写入文档之前,删除原文档中的某些字段值的.
     */
    @Data
    public static class Remove {
        /**
         * 字段
         */
        private String field;
    }

    /**
     * Rename Processor是用来处理在文档写入Elasticsearch之前修改某个文档的字段的名称
     */
    @Data
    public static class Rename {
        /**
         * 字段
         */
        private String field;

        /**
         * 目标更新的字段新名
         */
        private String target_field;
    }

    /**
     * Script Processor算的上是最强大的处理了,因为能充分利用Elasticsearch提供的脚本能力.
     * <p>
     * https://www.elastic.co/guide/en/elasticsearch/reference/current/modules-scripting.html
     */
    @Data
    public static class Script {
        /**
         * 语言
         * painless
         * expression
         * mustache
         * java
         */
        private String lang;

        /**
         *
         */
        private String inline;

    }

    /**
     * Split Processor用于将一个以指定分隔分开的字符串转换成一个数组类型的字段
     */
    @Data
    public static class Split {

        /**
         * 字段
         */
        private String field;

        /**
         * 分隔符
         */
        private String separator;

    }

    /**
     * Sort Processor用于处理数组类型的字段,可以将存储在原文档中某个数组类型的字段中的元素按照升序或降序来对原元素进行排序
     */
    @Data
    public static class Sort {
        /**
         * 字段
         */
        private String field;

        /**
         * 排序
         */
        private String order;
    }

    /**
     * 哎,Elasticsearch开发者真的没谁了,基本上能把String类中的方法都拿过来搬一套过来使用.
     * <p>
     * Trim Processor是专门用于处理字符串两端的空格问题,如下
     */
    @Data
    public static class Trim {
        /**
         * 字段
         */
        private String field;
    }

    public static class MyPersonalProcessor extends Plugin implements IngestPlugin {
        @Override
        public Map<String, org.elasticsearch.ingest.Processor.Factory> getProcessors(org.elasticsearch.ingest.Processor.Parameters parameters) {
            return Collections.singletonMap(FirstUpperProcessor.TYPE, new FirstUpperProcessor.Factory());
        }
    }

    public static class FirstUpperProcessor extends AbstractProcessor {

        public static final String TYPE = "firstUpper";

        private final String field;

        public FirstUpperProcessor(String tag, String field) {

            super(tag);
            this.field = field;
        }

        @Override
        public IngestDocument execute(IngestDocument ingestDocument) {

            ingestDocument.setFieldValue(field, field.toUpperCase());
            return ingestDocument;
        }

        @Override
        public String getType() {

            return TYPE;
        }

        public static final class Factory implements org.elasticsearch.ingest.Processor.Factory {

            @Override
            public FirstUpperProcessor create
                    (Map<String, org.elasticsearch.ingest.Processor.Factory> registry,
                     String processorTag,
                     Map<String, Object> config) {

                String field = ConfigurationUtils.readStringProperty(TYPE, processorTag, config, "field");
                return new FirstUpperProcessor(processorTag, field);
            }

        }
    }

}
