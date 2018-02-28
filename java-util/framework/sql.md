```
CREATE TABLE `module_detail_config` (
    `id` BIGINT (20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `module_name` VARCHAR (50) DEFAULT NULL COMMENT '模块名字',
    `module_config` VARCHAR (500) DEFAULT NULL COMMENT '每个模块的配置信息(使用json配置)',
    `contain_dynamic_data` TINYINT (1) DEFAULT '0' COMMENT '该模块是否包含动态数据(0不包含,1包含动态数据)',
    `module_type` TINYINT (2) NOT NULL DEFAULT '0' COMMENT '模块类型',
    `group_id` INT (10) DEFAULT NULL COMMENT '模块组id',
    `permission_code` VARCHAR (80) DEFAULT 'no_limit' COMMENT '该模块对应的操作权限Code',
    `module_sequence` INT (11) NOT NULL DEFAULT '0' COMMENT '模块顺序',
    `platform` VARCHAR (5) DEFAULT NULL COMMENT '配置平台:pc或app',
    `create_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_valid` TINYINT (1) NOT NULL DEFAULT '1' COMMENT '是否有效(0无效,1有效)',
    `drc_check_time` TIMESTAMP (3) NOT NULL DEFAULT CURRENT_TIMESTAMP (3) ON UPDATE CURRENT_TIMESTAMP (3) COMMENT '仅供DRC数据校验使用',
    PRIMARY KEY (`id`),
    UNIQUE KEY `module_name_INDEX` (`module_name`) USING BTREE,
    KEY `create_at_INDEX` (`create_at`) USING BTREE,
    KEY `update_at_INDEX` (`update_at`) USING BTREE,
    KEY `ix_drc_check_time` (`drc_check_time`)
) ENGINE = INNODB AUTO_INCREMENT = 306 DEFAULT CHARSET = utf8 COMMENT = '模块配置信息记录表'
```

```

```