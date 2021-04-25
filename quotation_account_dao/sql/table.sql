
## 行情账号信息 ##
set names utf8;
DROP TABLE IF EXISTS t_quotation_account;
CREATE TABLE t_quotation_account(
	Faccount_id BIGINT UNSIGNED NOT NULL COMMENT '内部唯一数字id',
	Faccount_name VARCHAR(32) NOT NULL COMMENT '登录用户名',
	Faccount_pwd VARCHAR(32) NOT NULL COMMENT '登录密码',
	Fnick_name VARCHAR(64) NOT NULL DEFAULT "" COMMENT '别名',
	Fquotation_platform SMALLINT NOT NULL DEFAULT 0 COMMENT '行情接入技术平台',
	Fquotation_platform_env SMALLINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '技术平台环境, 实盘/模拟盘',
	Fbroker_id INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '券商id',
	Fbroker_access_id INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '券商接入id',
	Faccount_properties text NOT NULL COMMENT '账户的扩展属性',
	Faccount_state SMALLINT NOT NULL DEFAULT 0 COMMENT '账号本身状态',
	Faccount_access_state SMALLINT NOT NULL DEFAULT 0 COMMENT '账号接入状态',
	Finvalid_reason VARCHAR(128) NOT NULL DEFAULT "" COMMENT '账号不可用原因',
	Finvalid_error_code INT NOT NULL DEFAULT 0 COMMENT '账号不可用原因,内部定义的错误码',
	Fapi_ret_code INT NOT NULL DEFAULT 0 COMMENT '接入api返回的错误码',
	Fmax_register_count INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大订阅数',
	Fdeploy_set SMALLINT NOT NULL DEFAULT 0 COMMENT '部署区域',
	Fcreate_timestamp INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间点',
	Flast_modify_timestamp INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后修改时间点',
	PRIMARY KEY(Faccount_id)
	) CHARSET=utf8mb4, ENGINE=InnoDb;


## 商品活跃合约规则 ##
set names utf8;
DROP TABLE IF EXISTS t_contract_active_rule;
CREATE TABLE t_contract_active_rule(
	Fsled_commodity_id INT UNSIGNED NOT NULL COMMENT '雪橇商品id',
	Factive_month_map VARCHAR(1024) NOT NULL DEFAULT '' COMMENT 'key:月份（1-12）value：是否活跃',
	Fcontract_active_type SMALLINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '0:活跃月份, 1: 固定合约号',
	Ffixed_code VARCHAR(32) NOT NULL DEFAULT '' COMMENT '固定合约号',
	Fcreate_timestamp INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间点',
	Flast_modify_timestamp INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后修改时间点',
	PRIMARY KEY(Fsled_commodity_id,Fcontract_active_type,Ffixed_code)
	) CHARSET=utf8mb4, ENGINE=InnoDb;

set names utf8;
ALTER TABLE t_contract_active_rule ADD COLUMN Fcontract_active_type SMALLINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '0:活跃月份, 1: 固定合约号';
ALTER TABLE t_contract_active_rule ADD COLUMN Ffixed_code VARCHAR(32) NOT NULL DEFAULT '' COMMENT '固定合约号';


## 合约订阅规则 ##
set names utf8;
DROP TABLE IF EXISTS t_contract_register_rule;
CREATE TABLE t_contract_register_rule(
	Fsled_commodity_id INT UNSIGNED NOT NULL COMMENT '雪橇商品id',
	Fquotation_platform_env SMALLINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '技术平台环境, 实盘/模拟盘',
	Fregister_priority INT NOT NULL DEFAULT 0 COMMENT '订阅优先级',
	Factive_show_count INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '活跃合约展示数量',
	Finactive_show_count INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '非活跃合约展示数量',
	Forder_index INT UNSIGNED NOT NULL DEFAULT 0,
	Fcontract_active_type SMALLINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '0:活跃月份, 1: 固定合约号',
	Ffixed_code VARCHAR(32) NOT NULL DEFAULT '' COMMENT '固定合约号',
	Fcreate_timestamp INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间点',
	Flast_modify_timestamp INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后修改时间点',
	PRIMARY KEY(Fsled_commodity_id,Fcontract_active_type,Ffixed_code,Fquotation_platform_env)
	) CHARSET=utf8mb4, ENGINE=InnoDb;

set names utf8;
ALTER TABLE t_contract_register_rule ADD COLUMN Fcontract_active_type SMALLINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '0:活跃月份, 1: 固定合约号';
ALTER TABLE t_contract_register_rule ADD COLUMN Ffixed_code VARCHAR(32) NOT NULL DEFAULT '' COMMENT '固定合约号';

## 行情账号支持订阅雪橇商品信息 ##
set names utf8;
DROP TABLE IF EXISTS t_quotation_account_support;
CREATE TABLE t_quotation_account_support(
	Fsupport_ability_id BIGINT UNSIGNED NOT NULL COMMENT '支持订阅能力id, 内部唯一数字id',
	Faccount_id BIGINT UNSIGNED NOT NULL COMMENT '行情账号id',
	Fsled_exchange_id INT UNSIGNED NOT NULL COMMENT '雪橇交易所id',
	Fexchange_mic VARCHAR(32) NOT NULL DEFAULT '' COMMENT '交易所代号',
	Fsled_commodity_id INT UNSIGNED NOT NULL COMMENT '雪橇商品id',
	Fsupport_type SMALLINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '交易所商品支持类型',
	Fcreate_timestamp INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间点',
	Flast_modify_timestamp INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后修改时间点',
	PRIMARY KEY(Fsupport_ability_id)
	) CHARSET=utf8mb4, ENGINE=InnoDb;

# 处理行情订阅变更的任务#
DROP TABLE IF EXISTS t_subcribe_quote_state_task;
CREATE TABLE t_subcribe_quote_state_task(
	Ftask_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
	Fcreate_timestamp BIGINT UNSIGNED NOT NULL DEFAULT 0,
	PRIMARY KEY(Ftask_id)
	) CHARSET=utf8mb4, ENGINE=InnoDb, AUTO_INCREMENT=1;