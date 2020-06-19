# 创建索引模板
# order越大，优先级越高
# refresh_interval: 数据刷新时间 不会立即被查询到 默认1s
# codec: 压缩算法
# analysis:自定义分析器
# dynamic_template
# ------------------------------------------------------------------------------- #
## char_filter: 自定义的字符过滤器(分词前加工)
### mapping:      映射字符过滤器
#### date_detection:      日期探测
#### numeric_detection:   数字探测
### html_strip:   HTML过滤器
### pattern_replace:格式替换过滤器
# ------------------------------------------------------------------------------- #
## tokenizer:   自定义分词器
### standard、keyword、whitespace、pattern
# ------------------------------------------------------------------------------- #
## filter:      自定义过滤器(分词后加工)
### 增加、修改、删除
# ------------------------------------------------------------------------------- #
## analyzer:    自定义分析器
# mapping:
## doc:         索引下的一个类型 只匹配到该类型 "全局default"
## index:       默认true 是否被索引 false该字段不可搜索
# ------------------------------------------------------------------------------- #
## index_options: 四种不同级别的配置，控制倒排索引内容
### docs:         记录doc id
### freqs:        记录doc id和term frequencies
### positions:    记录doc id/term frequencies/term position
### offsets:      记录doc id/term frequencies/term position/character offects
# ------------------------------------------------------------------------------- #
## null_value:    null搜索
## copy_to
## dynamic_templates:   动态映射
### fielddata:    字段不需要排序或者聚合分析，则该字段就设置为不可用。这样可以节省内存
### analyzer:     定义的是该字段的分析器，默认的分析器是 standard 标准分析器
### index:        字段的索引方式,默认倒排索引
### omit_norms:   分析过程中，是否考虑加权
### type:         字段的数据类型
### fields:       嵌入字段
### ignore_above: 字段是索引时忽略长度超过定义值的字段
### doc_values:   doc_values 应用于不分析字段，存储于磁盘中 DocValue 是预先构建的,稳定性大幅提升,解决了 对大内存的垃圾回收导致的不稳定问题，典型的列式存储
## ----------------------------------------------------------------------------- ##
## properties:    
## properties:    自定义字段映射
## ------------------------------------------------------------------------------ ##
#   aliases:      别名
##   零停机时间实现重新索引方式
### POST /_aliases
### {
###    "actions": [
###        { "remove": { "index": "my_index_v1", "alias": "my_index" }},
###        { "add":    { "index": "my_index_v2", "alias": "my_index" }}
###    ]
### }
# --------------------------------------------------------------------------------- #
# 字段的数据类型
## 简单类型
### Text/Keyword
### Date
### Integer/Floating
### Boolean
### Ipv4&Ipv6
## 复杂类型 - 对象和嵌套类型
### 对象类型/嵌套类型
## 特殊类型
### geo_point&geo_shape/percolator
# --------------------------------------------------------------------------------- #

GET _template
GET _template/testmapping
GET _template/testmapping?filter_path=*.version
# 验证模板是否存在
HEAD _template/testmapping
PUT _template/testmapping
{
  "settings": {
    "number_of_shards": 2, 
    "number_of_replicas": 1,
    "refresh_interval": "5s",
    "codec": "best_compression",
    "analysis": {
      "char_filter": {
        "&_to_and": {
          "type": "mapping",
          "mappings": ["&=> and "]
        }
      }
    }
  }, 
  "aliases": {
    "test": {}
  }, 
  "order": 0, 
  "version": 1, 
  "index_patterns": [
    "test*"  
  ], 
  "mappings": {
      "doc": {
          "properties": {
            "age": {
              "type": "integer"
            },
            "userName": {
              "type": "text",
              "fields": {
                "keyword": {
                  "type": "keyword",
                  "ignore_above": 256
                }
              }
            },
            "hobbit": {
              "type": "text",
              "fields": {
                "keyword": {
                  "type": "keyword",
                  "ignore_above": 256
                }
              }
            },
            "gmtModify": {
              "type": "date",
              "format": "yyyy-MM-dd HH:mm:ss||yyyy||yyyy-MM||yyyy-MM-dd||epoch_millis"
            },
            "noNeedAgg": {
              "type": "keyword",
              "doc_values": false
            },
            "noNeedScore": {
              "type": "keyword",
              "norms": false
            }
      }
      }
  }
}

# Analysis 分词
## Analyzer:  分词器
### Character Filters:  针对原始文本处理 例如去除html
### Tonkenizer:         按照规则切分单词
### Token Filters:      单词二次加工
#### standard analyzer:       默认分词器 按词切分 小写处理
#### simple analyzer:         非字母切分 小写处理
#### whitespace:              按空格进行切分
#### stop:                    相比于simple 多了stop fliter
#### keyword:                 不做分词处理
#### pattern:                 正则表达式分词
#### laungage:                国家语言
#### icu:                     亚洲词汇
#### lk:                      自定义词库，支持热更新分词字典
#### thulac:                  清华大学中文分词器
GET _analyze
{
  "analyzer": "standard",
  "text": ["123","master age xiao-zhi-zhi"]
}
GET _analyze
{
  "analyzer": "simple",
  "text": ["123","master age xiao-zhi-zhi"]
}

# 设置pipeline
PUT _ingest/pipeline/MySetPipeline
{
  "description": "My First set field pipeline",
  "processors": [
    {
      "set": {
        "field": "exteral",
        "value": "exteral"
      }
    }
  ]
}

# 字段索引优化 用于聚合与排序
PUT test/_mapping?pretty
{
  "properties": {
    "age": {
      "type": "text",
      "fielddata": true
    }
  }
}

# 获取pipeline
GET _ingest/pipeline

# 查看索引信息
GET test
GET kibana_sample_data_logs

# 索引总数
GET test/_count

# 集群信息
GET _cluster/health
# cat api
GET _cat/nodes
GET _cat/shards

# 索引删除+创建
PUT test/_doc/1
{
  
}
# 字段更新
POST test/_update/1
{
  "doc": {
    "age": 21
  }
}
# Bulk API
POST _bulk
# 批量读取
GET _mget
{
  "docs": [
    {
      "_index": "test",
      "_id": "1"
    },
    {
      "_index": "test1",
      "_id": "1"
    }
  ]
}
# 批量查询
GET test/_msearch
{}
{"query": {"match_all": {}}}
{"index": "kibana_sample_data_logs"}
{"query": {"match_all": {}}}

# 查看索引内容
# 分页
GET test/_search
{
  "query": {
    "match_all": {
    }
  }, 
  "from": 0,
  "size": 1
}

# 合并查询
## bool 将多种查询组合在一起
### must      文档 必须 匹配这些条件才能被包含进来。
### must_not  文档 必须不 匹配这些条件才能被包含进来。
### should    如果满足这些语句中的任意语句，将增加_score，否则，无任何影响。它们主要用于修正每个文档的相关性得分
### filter    必须 匹配，但它以不评分、过滤模式来进行。这些语句对评分没有贡献，只是根据过滤标准来排除或包含文档

# 提高精确度查询
## boosting
### positive  您希望运行的查询。任何返回的文档都必须匹配此查询
### negative  用来降低匹配文档的相关性得分的查询。
### negative_boost  降低评分的值

# 匹配分数查询
## constant_score
### filter    过滤条件
### boost     增长的评分值

# 根据匹配术语的顺序和接近度返回文档
## intervals
### match     匹配  query、max_gaps、ordered、analyzer、filter、use_field
### prefix    前缀  prefix、analyzer、use_field
### wildcard  正则  pattern、analyzer、use_field
### fuzzy     模糊  term、prefix_length、transpositions、fuzziness、analyzer、use_field
### all_of    整合项intervals、max_gaps、ordered、filter
### any_of    匹配任意一个intervals、filter
### filter    过滤条件  after、before、contained_by、containing、not_contained_by、not_containing、not_overlapping、overlapping、script


# 查询分类
## match_all 查询、match 查询、multi_match、
## range
### gt gte lt lte
## term 精确查询 不分析
# terms 但它允许你指定多值进行匹配。如果这个字段包含了指定值中的任何一个值，那么这个文档满足条件
## exists 查询和 missing 查询被用于查找那些指定字段中有值 (exists) 或无值 (missing) 的文档


# 匹配查询
## match query
### query
### analyzer
### auto_generate_synonyms_phrase_query
### fuzziness
### max_expansions
### prefix_length
### transpositions
### fuzzy_rewrite
### lenient
### operator      OR (默认)  AND
### minimum_should_match
### zero_terms_query  none (Default)  all
## query_string
## simple_query_string:

# 前缀匹配查询
## Match boolean prefix query

# 匹配短语查询
## Match phrase query
### match_phrase
### slop 可以在phrase插入一个词

# 匹配短语前缀查询
## Match phrase prefix query
### query
### analyzer
### max_expansions
### slop
### zero_terms_query none (Default)、  all

# 多项匹配查询
## Multi-match query

# 源查询
## _source
### includes  包括
### excludes  非包括

# 泛查询

# 查询过程
## profile

GET test/_search
{
  "_source": {
    "includes": ["user","age"], 
    "excludes": ["exteral"]
  }, 
  "sort": [
    {
      "age.keyword": {
        "order": "desc"
      }
    }
  ], 
  "query": {
    "bool": {
     "must": [{"match": {"user": "wang"}}],
     
     "filter": [
       {"range": {
         "age": {
           "gte": 10,
           "lte": 22
         }
       }}
     ]
    }
  },
  "profile": "true"
}

# ------------------------------------------------------------------------------- #
# aggs
## 聚合框架
## Bucketing:     桶匹配              一些满足特定条件的文档集合  类似group by
### adjacency matrix:       邻接矩阵
### auto_date_histogram:    日期直方图
### terms
# ------------------------------------------------------------------------------- #
## Metric:        跟踪计算指标的聚合  一些数学运算，可以对文档字段进行统计分析  类似select
### avg:                单值指标平均
### weighted_avg:       单值权重指标平均
#### parameter: value、weight、format、value_type
### boxplot:            单值箱线图
### cardinality:        单值去重计算
### stats:              多指标聚合数值计算
#### min, max, sum, count and avg:    返回信息
### string_stats:       多指标祝福统计
### extended_stats:     扩展的多指标聚合数值计算
### geo_bounds:         经纬度的操作
### geo_centroid:       经纬度地理重心
### max:                单值提取最大的数值
### min:                单值提取最小的数值
### sum:                单值总和
### median_absolute_deviation: 绝对偏差的中值
### percentiles:        百分比
### percentiles_ranks:  范围百分比
### scripted_metric:    脚本聚合 太难了
### top_hits:
### value_count:        单值指定值的数量
# ------------------------------------------------------------------------------- #
## Matrix:         多字段集合  不支持脚本 对多个字段进行操作并提供一个结果矩阵
## Pipeline:       聚合其他的聚合   对其他聚合进行二次聚合

GET kibana_sample_data_logs/_search
{
  "aggs": {
    "avg_age": {
      "avg": {
        "field": "memory"
      }
    }
  }
}
GET kibana_sample_data_logs/_search
{
  "aggs": {
    "boxplot_age": {
      "boxplot": {
        "field": "memory"
      }
    }
  }
}
GET kibana_sample_data_logs/_search
{
  "aggs": {
    "type_count_memory": {
      "cardinality": {
        "field": "memory"
      }
    }
  }
}
GET kibana_sample_data_logs/_search
{
  "aggs": {
    "muti_records": {
      "stats": {
        "field": "memory"
      }
    }
  }
}
GET kibana_sample_data_logs/_search
{
  "aggs": {
    "extended_muti_records": {
      "extended_stats": {
        "field": "memory"
      }
    }
  }
}

# 验证查询
GET kibana_sample_data_logs/_validate/query
{
  "query": {
    "bool": {
      "filter": [
        {
          "and": {
            "filters": [
              {
                "bool": {
                  "match": {
                    "response": "503"
                  }
                }
              }
            ]
          }
        }
      ]
    }
  }
}
# 理解错误信息
GET kibana_sample_data_logs/_validate/query?explain
{
  "query": {
    "bool": {
      "filter": [
        {
          "and": {
            "filters": [
              {
                "bool": {
                  "match": {
                    "response": "503"
                  }
                }
              }
            ]
          }
        }
      ]
    }
  }
}

# 查看节点
GET _cat/nodes

# sql查询
POST /_sql?format=txt
{
  "query": """
  SELECT * FROM "test" where age = 21
  """
}