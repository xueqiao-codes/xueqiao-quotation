# xueqiao-quotation
雪橇稳定快速的统一行情系统，支持内外盘。不同券商的API存在着不稳定，行情转发速率不对等等问题，并且无历史行情数据。通过从多个券商获取行情数据，通过竞速后获得最稳定的行情数据源，并且在中间过程中快速异步落地至memcacheq，之后落地至HBase进行大数据检索。