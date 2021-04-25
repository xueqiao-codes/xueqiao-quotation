CREATE TABLE tplan_state (
	Fkey INT UNSIGNED NOT NULL DEFAULT 0,
	Fpreview_status SMALLINT NOT NULL DEFAULT 0,
	Fpreivew_statemsg VARCHAR(256) NOT NULL DEFAULT '',
	Flastupdate_timestampms BIGINT UNSIGNED NOT NULL DEFAULT 0,
	Fcurrent_scclass_index SMALLINT UNSIGNED NOT NULL DEFAULT 0,
	Fswitch_scclass_timestampms BIGINT UNSIGNED NOT NULL DEFAULT 0,
	Fcurrent_scitem_index SMALLINT UNSIGNED NOT NULL DEFAULT 0,
	Fswitch_scitem_timestampms BIGINT UNSIGNED NOT NULL DEFAULT 0,
	Fneed_init_scitems TINYINT NOT NULL DEFAULT 0,
	PRIMARY KEY(Fkey)
) ENGINE=InnoDB CHARACTER SET UTF8;

INSERT INTO tplan_state SET Fkey=1;

CREATE TABLE tsc_class_0 (
	Fclass_id BIGINT UNSIGNED NOT NULL DEFAULT 0,
	Fsled_commodity_id BIGINT NOT NULL DEFAULT 0,
	Fsled_commodity_type SMALLINT NOT NULL DEFAULT 0,
	Fsled_commodity_code VARCHAR(32) NOT NULL DEFAULT '',
	Fsled_exchange_mic VARCHAR(14) NOT NULL DEFAULT '',
	Fplatform_env TINYINT NOT NULL DEFAULT 0,
	Factive_months VARCHAR(64) NOT NULL DEFAULT '',
	Finactive_months VARCHAR(64) NOT NULL DEFAULT '',
	Factive_subscribe_num INT UNSIGNED NOT NULL DEFAULT 0,
	Finactive_subscribe_num INT UNSIGNED NOT NULL DEFAULT 0,
	Fsubscribe_accounts VARCHAR(2048) NOT NULL DEFAULT '',
	Fcreate_timstampms BIGINT UNSIGNED NOT NULL DEFAULT 0,
	PRIMARY KEY(Fclass_id)
) ENGINE=InnoDB CHARACTER SET UTF8;

CREATE TABLE tsc_class_1 (
	Fclass_id BIGINT UNSIGNED NOT NULL DEFAULT 0,
	Fsled_commodity_id BIGINT NOT NULL DEFAULT 0,
	Fsled_commodity_type SMALLINT NOT NULL DEFAULT 0,
	Fsled_commodity_code VARCHAR(32) NOT NULL DEFAULT '',
	Fsled_exchange_mic VARCHAR(14) NOT NULL DEFAULT '',
	Fplatform_env TINYINT NOT NULL DEFAULT 0,
	Factive_months VARCHAR(64) NOT NULL DEFAULT '',
	Finactive_months VARCHAR(64) NOT NULL DEFAULT '',
	Factive_subscribe_num INT UNSIGNED NOT NULL DEFAULT 0,
	Finactive_subscribe_num INT UNSIGNED NOT NULL DEFAULT 0,
	Fsubscribe_accounts VARCHAR(2048) NOT NULL DEFAULT '',
	Fcreate_timstampms BIGINT UNSIGNED NOT NULL DEFAULT 0,
	PRIMARY KEY(Fclass_id)
) ENGINE=InnoDB CHARACTER SET UTF8;

CREATE TABLE tsc_item_0 (
	Fitem_id BIGINT UNSIGNED NOT NULL DEFAULT 0,
	Fclass_id BIGINT UNSIGNED NOT NULL DEFAULT 0,
	Fsled_contract_id BIGINT UNSIGNED NOT NULL DEFAULT 0,
	Fsled_contract_code VARCHAR(32) NOT NULL DEFAULT '',
	Fsled_commodity_id BIGINT NOT NULL DEFAULT 0,
	Fsled_commodity_type SMALLINT NOT NULL DEFAULT 0,
	Fsled_commodity_code VARCHAR(32) NOT NULL DEFAULT '',
	Fsled_exchange_mic VARCHAR(14) NOT NULL DEFAULT '',
	Fplatform_env TINYINT NOT NULL DEFAULT 0,
	Fquotation_account_id BIGINT UNSIGNED NOT NULL DEFAULT 0,
	Fis_for_active TINYINT NOT NULL DEFAULT 0,
	Fcreate_timestampms BIGINT UNSIGNED NOT NULL DEFAULT 0,
	PRIMARY KEY(Fitem_id),
	INDEX(Fquotation_account_id, Fsled_commodity_id),
	INDEX(Fsled_commodity_id)
) ENGINE=InnoDB CHARACTER SET UTF8;

CREATE TABLE tsc_item_1 (
	Fitem_id BIGINT UNSIGNED NOT NULL DEFAULT 0,
	Fclass_id BIGINT UNSIGNED NOT NULL DEFAULT 0,
	Fsled_contract_id BIGINT UNSIGNED NOT NULL DEFAULT 0,
	Fsled_contract_code VARCHAR(32) NOT NULL DEFAULT '',
	Fsled_commodity_id BIGINT NOT NULL DEFAULT 0,
	Fsled_commodity_type SMALLINT NOT NULL DEFAULT 0,
	Fsled_commodity_code VARCHAR(32) NOT NULL DEFAULT '',
	Fsled_exchange_mic VARCHAR(14) NOT NULL DEFAULT '',
	Fplatform_env TINYINT NOT NULL DEFAULT 0,
	Fquotation_account_id BIGINT UNSIGNED NOT NULL DEFAULT 0,
	Fis_for_active TINYINT NOT NULL DEFAULT 0,
	Fcreate_timestampms BIGINT UNSIGNED NOT NULL DEFAULT 0,
	PRIMARY KEY(Fitem_id),
	INDEX(Fquotation_account_id, Fsled_commodity_id),
	INDEX(Fsled_commodity_id)
) ENGINE=InnoDB CHARACTER SET UTF8;

ALTER TABLE tsc_item_0 ADD COLUMN Fquotation_deploy_set TINYINT NOT NULL DEFAULT 127;
ALTER TABLE tsc_item_1 ADD COLUMN Fquotation_deploy_set TINYINT NOT NULL DEFAULT 127;
ALTER TABLE tsc_class_0 ADD COLUMN Factive_type TINYINT NOT NULL DEFAULT 127;
ALTER TABLE tsc_class_1 ADD COLUMN Factive_type TINYINT NOT NULL DEFAULT 127;
ALTER TABLE tsc_class_0 ADD COLUMN Ffixed_contract_code VARCHAR(32) NOT NULL DEFAULT '';
ALTER TABLE tsc_class_1 ADD COLUMN Ffixed_contract_code VARCHAR(32) NOT NULL DEFAULT '';