package com.wangwei.forward.es;

import com.alibaba.fastjson.JSON;

/**
 * es的请求
 *
 * @author wangwei
 */
public class EsRequest {

    public static class RequestHeader {

        public static String buildHeader(String restType, String... attributes) {
            StringBuilder sb = new StringBuilder();
            sb.append(restType);
            sb.append(" ");

            for (int i = 0; i < attributes.length; i++) {
                sb.append(attributes[i]);
                sb.append("/");
            }

            return sb.substring(0, sb.length() - 1);
        }
    }

    public static class RequestBody {

        /**
         * 内容
         */
        private Doc docs;

        public RequestBody(Doc docs) {
            this.docs = docs;
        }

        public static String buildBody(Doc doc) {
            RequestBody requestBody = new RequestBody(doc);

            return JSON.toJSONString(requestBody);
        }

    }

}
